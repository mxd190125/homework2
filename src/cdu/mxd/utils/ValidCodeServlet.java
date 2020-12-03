package cdu.mxd.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "validCodeServlet",urlPatterns = "/validCode")
public class ValidCodeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pargram","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);

        String code=getStringRandom(4);
        HttpSession session = request.getSession();
        session.setAttribute("validCode" , code);
        System.out.println("================[验证码]："+code);

        int width=80;
        int height=25;
        BufferedImage bufferedImage = new BufferedImage(width , height , BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,width,height);
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(code) , 0 , 20);
        ImageIO.write(bufferedImage , "png" , response.getOutputStream());

    }

    /**
     * 生成随机length位数字和字母,
     * @param length
     * @return
     */
    public String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        while (length > 0){
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                char ch=(char)(random.nextInt(26) + temp);
                if (ch !='l' && ch != 'O'){
                    val += ch;
                    length--;
                }
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                String num = String.valueOf(random.nextInt(10));
                if (!(num.equals("1") || num.equals("0"))){
                    val += num;
                    length--;
                }
            }
        }
        return val;
    }

}
