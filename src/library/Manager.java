package library;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sun.jndi.toolkit.ctx.StringHeadTail;
import com.sun.org.apache.bcel.internal.generic.NEW;

import oracle.net.aso.i;
import sun.print.DialogOwner;

/**
 * Servlet implementation class Manager
 */
@WebServlet("/Manager")
public class Manager extends HttpServlet {
	private Dao dao=new Dao();
	private Ma ma=new Ma();
    public Manager() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		System.out.println(action);
		
		if("login".equals(action)){
			//��֤��¼
			String a=request.getParameter("username");//��ȡ��¼��
			if(ma.login(a, request.getParameter("pswd"))){
				HttpSession session = request.getSession();
				session.setAttribute("username", a);
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("login.jsp");
			}
		}
		
		else if ("borrow".equals(action)) {
			//��ͼ��
			int a=Integer.valueOf(request.getParameter("member"));
			int b=Integer.valueOf(request.getParameter("books"));
			if (ma.borrow(a, b, 1)){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("still".equals(action)) {
			//����
			String[] id=request.getParameterValues("checkbox1");
			int count=0;
			for(int i=0;i<id.length;i++){
				if (ma.return_still(id[i])) {
					count++;
				}
			}
			if (count==id.length) {
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("member_query".equals(action)) {
			//��Ա��ѯ
			String a=request.getParameter("a");
			response.sendRedirect("member_query.jsp?c="+a);	
		}
		
		else if ("book_query".equals(action)) {
			//ͼ���ѯ
			String a=request.getParameter("a");
			response.sendRedirect("book_query.jsp?c="+a);	
		}
		
		else if ("book_borrow".equals(action)) {
			//������Ϣ��ѯ
			String a=request.getParameter("a");
			response.sendRedirect("book_borrow.jsp?c="+a);	
		}
		
		else if ("member_increase".equals(action)) {
			//���ӻ�Ա
			String a=request.getParameter("name");
			String b=request.getParameter("password");
			int d=Integer.valueOf(request.getParameter("phone"));
			int e=Integer.valueOf(request.getParameter("membertype"));
			if (ma.add_member(a,b,d,e)>0){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("book_increase".equals(action)) {
			//����ͼ��
			String a=request.getParameter("bookname");
			int b=Integer.valueOf(request.getParameter("number"));
			int c=Integer.valueOf(request.getParameter("bookstype"));
			int d=Integer.valueOf(request.getParameter("recommend"));
			if (ma.add_books(a,b,c,d)>0){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("add_administrator".equals(action)) {
			//���ӹ���Ա
			String a=request.getParameter("username");
			String b=request.getParameter("pswd");
			int c=0,d=0,e=0;
			if(request.getParameter("b_istration")!=null){ c=1; }
			if(request.getParameter("m_istration")!=null){ d=1; } 
			if(request.getParameter("a_istration")!=null){ e=1; }
			int f=ma.add_administrator(a, b, c, d, e);
			if(f==2){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("book_type".equals(action)) {
			//����ͼ������
			String a=request.getParameter("booktype");
			int b=Integer.valueOf(request.getParameter("days"));
			if (ma.book_type(a,b)>0){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("member_type".equals(action)) {
			//���ӻ�Ա����
			String a=request.getParameter("membertype");
			int b=Integer.valueOf(request.getParameter("number"));
			if (ma.member_type(a,b)>0){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		
		
		
		else if ("modify_password".equals(action)) {
			//�޸Ĺ���Ա����
			String a=request.getParameter("username");
			String b=request.getParameter("low");
			String c=request.getParameter("new");
			String d=request.getParameter("new1");
			String e=ma.username(a);
			System.out.println(a);
			System.out.println(b);
			System.out.println(c);
			System.out.println(d);
			System.out.println(e);
			if (e.equals(b) && c.equals(d) && ma.modify_password(c, a)>0){
				response.sendRedirect("success.jsp");	
			}
			else{
				response.sendRedirect("error.jsp");
			}
		}
		
		
		
		else if ("book_modify".equals(action)) {
			//�޸�ͼ��
			String[] a=request.getParameterValues("checkbox1");
			int count=0;
			for (int i=0;i<a.length;i++){
				int aa=Integer.valueOf(a[i]);
				String[] ff=request.getParameterValues(a[i]);
				int c=0,d=0,e=0;
				c=Integer.valueOf(ff[0]);
				d=Integer.valueOf(ff[1]);
				e=Integer.valueOf(ff[2]);
				int f=ma.book_modify(aa, c, d, e);
				count=count+f;
			}
			if (count==a.length){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("member_modify".equals(action)) {
			//�޸Ļ�Ա
			String[] a=request.getParameterValues("checkbox1");
			int count=0;
			for (int i=0;i<a.length;i++){
				int aa=Integer.valueOf(a[i]);
				String b=request.getParameter("name");
				String c=request.getParameter("password");
				int e=Integer.valueOf(request.getParameter("phone"));
				int g=Integer.valueOf(request.getParameter("membertype"));
				int f=ma.member_modify(aa,b,c,e,g);
				count=count+f;
			}
			if (count==a.length){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("modify_administrator".equals(action)) {
			//�޸Ĺ���Ա
			String[] a=request.getParameterValues("checkbox1");
			int count=0;
			int c=0,d=0,e=0;
			for (int i=0;i<a.length;i++){
				String[] j=request.getParameterValues(a[i]);
				String b=j[0];
				if(j[1]!=null){c=1;}
				if(j[2]!=null){d=1;}
				if(j[3]!=null){e=1;}
				int aa=Integer.valueOf(a[i]);
				int co=0;
				co=ma.modify_administrator(aa, b, c, d, e);
				count=count+co;
			}
			if ((count/2)==a.length){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		
		
		else if ("del_books".equals(action)) {
			//ɾ��ͼ��
			int count=0;
			String[] a=request.getParameterValues("checkbox1");
			for (int i=0;i<a.length;i++){
				int b=ma.del_books(Integer.valueOf(a[i]));
				count=count+b;
			}
			if (count==a.length){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("del_member".equals(action)) {
			//ɾ��ͼ��
			int count=0;
			String[] a=request.getParameterValues("checkbox1");
			for (int i=0;i<a.length;i++){
				int b=ma.del_member(Integer.valueOf(a[i]));
				count=count+b;
			}
			if (count==a.length){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}