package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.Gmail;
import com.SHA256;
import com.member.db.MemberBean;
import com.member.db.MemberDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;




public class MemberJoinProcessAction implements Action {
//2021.2.22 finish
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String realFolder = "";

		// WebContent �Ʒ��� �� ���� �����ϱ�
		String saveFolder = "memberupload";

		int fileSize = 5 * 1024 * 1024; // ���ε� �� ������ �ִ� ������. 5MB

		// ���� ���� ��� ����
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder= " + realFolder);
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			
			String member_id = multi.getParameter("id");
			String member_name = multi.getParameter("name");
			String member_password = multi.getParameter("pass");
			String birth = multi.getParameter("birth1") + " / " + multi.getParameter("birth2")
						+ " / " + multi.getParameter("birth3");
			String member_email = multi.getParameter("email") + "@"
					+ multi.getParameter("domain");
			
			System.out.println("member_email = " + member_email);
			
			String member_gender = multi.getParameter("gender");
			String tel1 = multi.getParameter("tel1");
			String tel2 = multi.getParameter("tel2");
			String tel3 = multi.getParameter("tel3");
			String address = multi.getParameter("address");
			String[] member_interest = multi.getParameterValues("interest");
			String member_interests = null;
			if(member_interest != null) {
				member_interests = member_interest[0];
				for(int num = 1; num<member_interest.length; num++) {
					member_interests += "," + member_interest[num];
				}
			}
			
			String member_account = multi.getParameter("account_num");
			String member_bank = multi.getParameter("account_name");
			String member_file = multi.getFilesystemName("memberfile");
			System.out.println("memberfile = " + member_file);
			
			MemberBean memberbean = new MemberBean();
			
			memberbean.setMember_id(member_id);
			memberbean.setMember_name(member_name);
			memberbean.setMember_password(member_password);
			memberbean.setMember_birth(birth);
			memberbean.setMember_email(member_email);
			memberbean.setMember_gender(member_gender);
			memberbean.setMember_tel(tel1 + "-" + tel2 + "-" +  tel3);
			memberbean.setMember_address(address);
			memberbean.setMember_interest(member_interests);
			memberbean.setMember_account(member_account);
			memberbean.setMember_bank(member_bank);
			memberbean.setMember_file(member_file);
			memberbean.setMember_emailhash(SHA256.getSHA256(member_email));
			memberbean.setMember_emailchecked("0");
			response.setContentType("text/html; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			
			MemberDAO memberdao = new MemberDAO();
			
			int result = memberdao.insert(memberbean);
			out.println("<script>");
			if(result ==1) {//������ �� ���
				out.println("alert('ȸ�� ������ �����մϴ�.');");
				
				String host = "http://localhost:8088/wisensible/";
				String from = "projectkwonhta@gmail.com";
				String to = member_email;
				String subject = "Wisensible �̸��� ���� �����Դϴ�.";
				String content = "���� ��ũ�� �����Ͽ� �̸��� ������ �����ϼ���." +
									"<a href='" + host + "chkemail.com?code=" + new SHA256().getSHA256(to) + "'> �̸��� �����ϱ� </a>";
				
				Properties p = new Properties();
				p.put("mail.smtp.user", from);
				p.put("mail.smtp.host", "smtp.googlemail.com");
				p.put("mail.smtp.port", "465");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.debug", "true");
				p.put("mail.smtp.socketFactory.port", "465");
				p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				p.put("mail.smtp.socketFactory.fallback", "false");
				
				try {
					Authenticator auth = new Gmail();
					Session ses = Session.getInstance(p, auth);
					ses.setDebug(true);
					MimeMessage msg = new MimeMessage(ses);
					msg.setSubject(subject);
					Address fromAddr = new InternetAddress(from);
					msg.setFrom(fromAddr);
					Address toAddr = new InternetAddress(to);
					msg.addRecipient(Message.RecipientType.TO, toAddr);
					msg.setContent(content, "text/html;charset=UTF-8");
					Transport.send(msg);
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				
				out.println("alert('�����Ͻ� �̸��Ϸ� ���� ������ ���۵Ǿ����ϴ�. ���� ������ �Ͻø� ��� �ý����� �̿��� �� �ֽ��ϴ�.');");
				out.println("location.href='login.com';");
			}else if(result == -1) {
				out.println("alert('���̵� �ߺ��Ǿ����ϴ�. �ٽ� �Է��ϼ���.');");
				//���ΰ�ħ �Ǿ� ������ �Է��� �����Ͱ� ��Ÿ���� �ʴ´�.
				//out.println("location.href='join.net';");
				out.println("history.back()");//��й�ȣ�� ������ �ٸ� �����ʹ� �����Ǿ� �ִ´�.
			}
			out.println("</script>");
			out.close();
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
			ActionForward forward = new ActionForward();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "������ ���� ���ε� �����Դϴ�.");
			forward.setRedirect(false);
			return forward;
		}
		
	}

}