package com.xmr.demo.untils.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class MailSend {
    private static final String PROPERTIES_DEFAULT = "mailConfig.properties";
    // 发件人 账号和密码
    private static String MY_EMAIL_ACCOUNT;
    private static String MY_EMAIL_PASSWORD;// 密码,是你自己的设置的授权码
    private static Properties p = new Properties();

    static {
        init();
    }

    private static void init() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try{
            inputStream = MailSend.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT);
            //properties.load(inputStream);
            //inputStream.close();
            //解决中文乱码，采取reader把inputStream转换成reader用字符流来读取中文
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            properties.load(bf);
            // SMTP服务器(这里用的163 SMTP服务器)
            p.setProperty("mail.smtp.host", properties.getProperty("mailHost"));
            p.setProperty("mail.smtp.port", properties.getProperty("mailPort"));// 端口号,这个是163使用到的;QQ的应该是465或者875
            p.setProperty("mail.smtp.socketFactory.port", properties.getProperty("mailPort"));// 端口号,这个是163使用到的;QQ的应该是465或者875
            p.setProperty("mail.smtp.auth", "true");
            p.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");
            MY_EMAIL_ACCOUNT = properties.getProperty("mailUsername");
            MY_EMAIL_PASSWORD = properties.getProperty("mailPassword");
            inputStream.close();
        } catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(inputStream != null){
                    inputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void send(Integer code){
        Session session = Session.getInstance(p, new Authenticator() {
            // 设置认证账户信息
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MY_EMAIL_ACCOUNT, MY_EMAIL_PASSWORD);
            }
        });
        session.setDebug(true);
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MY_EMAIL_ACCOUNT));
            // 收件人和抄送人
            List<String> emailAddressList = new ArrayList<>();
            emailAddressList.add("1142102731@qq.com");
            //emailAddressList.add("1129978857@qq.com");
            InternetAddress[] address = new InternetAddress[emailAddressList.size()] ;
            for (int i = 0; i < emailAddressList.size(); i++){
                address[i] = new InternetAddress(emailAddressList.get(i));
            }
            //准备发送邮件
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject("错误信息");
            message.setContent("<h1>服务器报错,错误码为:"+code+",请及时处理</h1>", "text/html;charset=UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();
            Transport.send(message);
        }
        catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
