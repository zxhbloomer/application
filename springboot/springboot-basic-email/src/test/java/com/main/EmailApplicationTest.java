package com.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailApplicationTest {

	@Autowired
	private JavaMailSender mailSender; //自动注入的Bean

	@Value("${spring.mail.username}")
	private String Sender; //读取配置文件中的参数

	private String destEmail = "657603467@qq.com";

	/**
	 * 发送简单文本邮件
	 * @throws Exception
	 */
	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(Sender);
		message.setTo(destEmail); //发给谁
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
	}

	/**
	 * 发送Html邮件
	 */
	@Test
	public void sendHtmlMail() {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(Sender);
			helper.setTo(destEmail);
			helper.setSubject("标题：发送Html内容");

			StringBuffer sb = new StringBuffer();
			sb.append("<h1>大标题-h1</h1>")
					.append("<p style='color:#F00'>红色字</p>")
					.append("<p style='text-align:right'>右对齐</p>");
			helper.setText(sb.toString(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}

	/**
	 * 发送带附件的邮件
	 */
	@Test
	public void sendAttachmentsMail() {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(Sender);
			helper.setTo(destEmail);
			helper.setSubject("主题：带附件的邮件");
			helper.setText("带附件的邮件内容");
			//注意项目路径问题，自动补用项目路径
			FileSystemResource file = new FileSystemResource(new File("C:/Users/Administrator/Pictures/手持身份证.jpg"));
			//加入邮件
			helper.addAttachment("图片.jpg", file);
		} catch (Exception e){
			e.printStackTrace();
		}
		mailSender.send(message);
	}

	/**
	 * 发送带静态资源的邮件
	 */
	@Test
	public void sendInlineMail() {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(Sender);
			helper.setTo(destEmail);
			helper.setSubject("主题：带静态资源的邮件");
			//第二个参数指定发送的是HTML格式,同时cid:是固定的写法
			helper.setText("<html><body>带静态资源的邮件内容 图片:<img src='cid:picture' /></body></html>", true);

			FileSystemResource file = new FileSystemResource(new File("C:/Users/Administrator/Pictures/手持身份证.jpg"));
			helper.addInline("picture",file);
		} catch (Exception e){
			e.printStackTrace();
		}
		mailSender.send(message);
	}
}
