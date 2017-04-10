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
			//验证登录
			String a=request.getParameter("username");//获取登录名
			if(ma.login(a, request.getParameter("pswd"))){
				HttpSession session = request.getSession();
				session.setAttribute("username", a);
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("login.jsp");
			}
		}
		
		else if ("borrow".equals(action)) {
			//借图书
			int a=Integer.valueOf(request.getParameter("member"));
			int b=Integer.valueOf(request.getParameter("books"));
			if (ma.borrow(a, b, 1)){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("still".equals(action)) {
			//还书
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
			//会员查询
			String a=request.getParameter("a");
			response.sendRedirect("member_query.jsp?c="+a);	
		}
		
		else if ("book_query".equals(action)) {
			//图书查询
			String a=request.getParameter("a");
			response.sendRedirect("book_query.jsp?c="+a);	
		}
		
		else if ("book_borrow".equals(action)) {
			//借阅信息查询
			String a=request.getParameter("a");
			response.sendRedirect("book_borrow.jsp?c="+a);	
		}
		
		else if ("member_increase".equals(action)) {
			//增加会员
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
			//增加图书
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
			//增加管理员
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
			//增加图书类型
			String a=request.getParameter("booktype");
			int b=Integer.valueOf(request.getParameter("days"));
			if (ma.book_type(a,b)>0){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("member_type".equals(action)) {
			//增加会员类型
			String a=request.getParameter("membertype");
			int b=Integer.valueOf(request.getParameter("number"));
			if (ma.member_type(a,b)>0){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
		
		
		
		
		else if ("modify_password".equals(action)) {
			//修改管理员密码
			String a=request.getParameter("username");
			String b=request.getParameter("low");
			String c=request.getParameter("new");
			String d=request.getParameter("new1");
			String e=ma.username(a);
			if (e.equals(b) && c.equals(d) && ma.modify_password(c, a)>0){
				response.sendRedirect("success.jsp");	
			}
			else{
				response.sendRedirect("error.jsp");
			}
		}
		
		else if ("book_modify".equals(action)) {
			//修改图书
			String[] a=request.getParameterValues("checkbox1");
			int count=0;
			for (int i=0;i<a.length;i++){
				int aa=Integer.valueOf(a[i]);
				String[] ff=request.getParameterValues(a[i]);
				int c=0,d=0,e=0;
				d=Integer.valueOf(ff[0]);
				c=Integer.valueOf(ff[1]);
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
			//修改会员
			String[] a=request.getParameterValues("checkbox1");
			int count=0;
			for (int i=0;i<a.length;i++){
				int aa=Integer.valueOf(a[i]);
				String[] cd=request.getParameterValues(a[i]);
				String b="",c="";
				int e=0,g=0;
				b=cd[0];c=cd[1];e=Integer.valueOf(cd[2]);g=Integer.valueOf(cd[3]);
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
			//修改管理员
			String[] a=request.getParameterValues("checkbox1");
			int count=0;
			String b="";
			int c=0,d=0,e=0;
			for (int i=0;i<a.length;i++){
				String ccc=a[i]+"(1)";
				String ddd=a[i]+"(2)";
				String eee=a[i]+"(3)";
				b=request.getParameter(a[i]);
				if (request.getParameter(ccc)!=null){ c=1; }
				if (request.getParameter(ddd)!=null){ d=1; }
				if (request.getParameter(eee)!=null){ e=1; }
				int co=0;
				int aa=Integer.valueOf(a[i]);
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
			//删除图书
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
			//删除会员
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
		
		else if ("del_administrator".equals(action)) {
			//删除管理员
			int count=0;
			String[] a=request.getParameterValues("checkbox1");
			for (int i=0;i<a.length;i++){
				int b=ma.del_administrator(Integer.valueOf(a[i]));
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
