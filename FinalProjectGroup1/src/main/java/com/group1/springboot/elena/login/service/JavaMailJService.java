package com.group1.springboot.elena.login.service;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.group1.springboot.elena.login.dao.LoginDao;
import com.group1.springboot.elena.login.function.RandomString;
import com.group1.springboot.elena.login.model.CustomerBean;


@Service
@EnableTransactionManagement
public class JavaMailJService {
	
	@Autowired
	LoginDao CUJDao;

	//改這邊的發信人，必須同時在信箱的安全性做設定
	private String userName = "polo8989789@gmail.com";
	private String password = "efjwxqxadxrosfoq";
 
	@Transactional
	public void sendMailForget(String email) {
		String subject = "On The Way，會員密碼忘記";
		String customer = email;
		CustomerBean forgottonbean = (CustomerBean)CUJDao.getCustomerByEmail(email);
		
		//////取得暫時密碼////////
		String name = forgottonbean.getCustomerName();
		RandomString rngenerator = new RandomString();
		String temppwd = rngenerator.generateTempPassword(6);
		String content = "親愛的使用者"+name+"您好，<br><br>我們收到您收到的密碼忘記請求。<br>"
				+ "我們已經將您的密碼設為:"+temppwd+"，請您盡速使用此密碼登入並更新新密碼。<br>"
				+ "如果你沒有申請請忽略此郵件。<br>"
				+ "您可以點及此連結前往網站首頁: <a href= http://localhost:8080/FinalProjectGroup1>點選這裡</a>";
	
				
		
		
		//////更新成暫時密碼//////
		
		forgottonbean.setPassword(temppwd);
		CUJDao.updateCustomer(forgottonbean);
		
		
		/////////////////////////以下為寄送郵件的參數設定/////////////////////////////
		Properties prop = new Properties();
		//設定連線方式為smtp
		prop.setProperty("mail.transport.protocol","smtp");
//		host : smtp.gmail.com
		prop.setProperty("mail.host","smtp.gmail.com");
//		host port 465 
		prop.put("mail.smtp.port", "465");
//		寄件者帳號需要驗證：是
		prop.put("mail.smtp.auth", "true");		
//		需要安全資料傳輸層 (SSL)：是
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//      安全資料傳輸層 (SSL) 通訊埠：465
		prop.put("mail.smtp.socketFactory.port", "465");
		
		//------------帳密驗證
		//------------Session javamail默認設定屬性
		
//		使用java mail 的session帶入上面建立的prop來幫我們做事，因為是用gmail所以要用有authenticator的
		Session session = Session.getDefaultInstance(prop, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(userName,password);
			}
		});
		
		//------------Message 基本資料
		MimeMessage message = new MimeMessage(session);
		try {
			//寄件者，匿名類別寫法
			message.setSender(new InternetAddress(userName));
//			一般寫法
//			InternetAddress addressvase = new InternetAddress(userName);
//			message.setSender(addressvase);
			
			//收件者 前面帶recipient type (直接接收者、CC跟BCC)，後面帶信箱帳號
			message.setRecipient(RecipientType.TO, new InternetAddress(customer));
			//標題
			message.setSubject(subject);
			//內文 前面是內文，後面是內文的類型
			message.setContent(content, "text/html;charset=UTF-8");
			
			//--------------------transport將訊息送出
			Transport transport = session.getTransport(); 
			transport.send(message);
			transport.close();
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void sendMailRegistration(CustomerBean newbean) {
		String subject = "娛你同行，會員帳號認證信";
		String customer = newbean.getEmail();
		String name = newbean.getCustomerName();
		Integer originalId = newbean.getCustomerID();
		//////建立加密URL辨識碼////////
		Integer code = (originalId*157+67)*263-499 ;
		
		String content = "<h3>確認您的電子郵件地址</h3><br><br><br>"
				+"親愛的使用者"+name+"您好，<br><br>很高興你加入我們On The Way的一員。<br>"
				+ "為了驗證您的身分，請您點擊下方連結完成註冊驗證。<br>"
				+ "註冊連結: <a href= http://localhost:8080/proj/Jesper/mail/mailconfirmation/"+code+">點選這裡完成註冊</a>。<br>"
				+ "如果你沒有申請請忽略此郵件。<br>";
		
		
		/////////////////////////以下為寄送郵件的參數設定/////////////////////////////
		Properties prop = new Properties();
		//設定連線方式為smtp
		prop.setProperty("mail.transport.protocol","smtp");
//		host : smtp.gmail.com
		prop.setProperty("mail.host","smtp.gmail.com");
//		host port 465 
		prop.put("mail.smtp.port", "465");
//		寄件者帳號需要驗證：是
		prop.put("mail.smtp.auth", "true");		
//		需要安全資料傳輸層 (SSL)：是
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//      安全資料傳輸層 (SSL) 通訊埠：465
		prop.put("mail.smtp.socketFactory.port", "465");
		
		//------------帳密驗證
		//------------Session javamail默認設定屬性
		
//		使用java mail 的session帶入上面建立的prop來幫我們做事，因為是用gmail所以要用有authenticator的
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(userName,password);
			}
		});
		
		//------------Message 基本資料
		MimeMessage message = new MimeMessage(session);
		try {
			//寄件者，匿名類別寫法
			message.setSender(new InternetAddress(userName));
//			一般寫法
//			InternetAddress addressvase = new InternetAddress(userName);
//			message.setSender(addressvase);
			
			//收件者 前面帶recipient type (直接接收者、CC跟BCC)，後面帶信箱帳號
			message.setRecipient(RecipientType.TO, new InternetAddress(customer));
			//標題
			message.setSubject(subject);
			//內文 前面是內文，後面是內文的類型
			message.setContent(content, "text/html;charset=UTF-8");
			
			//--------------------transport將訊息送出
			Transport transport = session.getTransport(); 
			transport.send(message);
			transport.close();
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void receiveMailRegistration() {
		
	}
	
}
