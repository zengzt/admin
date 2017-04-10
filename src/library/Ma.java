package library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.IntBinaryOperator;

import javax.management.Query;

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.eclipse.jdt.internal.compiler.util.Sorting;

import com.sun.media.sound.AlawCodec;
import com.sun.org.apache.bcel.internal.generic.NEW;
import sun.util.logging.resources.logging;

public class Ma {
	private static Dao dao=new Dao();
	
	public static boolean login(String a,String b) {
		//��֤��¼
		String sql="SELECT * from administrators WHERE username='"+a+"' and pswd='"+b+"'";
		boolean flag=false;
		ResultSet rs=dao.query(sql);
		try {
			if(rs.next()){
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return flag;	
	}
	
	public static ResultSet display() {
		//��ѯͼ��  ��ҳ���Ƽ�ͼ���ǰʮ��
		ResultSet rs = null;
		String sql=
			"SELECT b.id,b.number,boo.bookstype,boo.borrowingdays from books b,b_bookstype boo where b.b_bookstype=boo.id and b.recommend=1 limit 10";
		rs=dao.query(sql);
		return rs;
	}
	
	public static int add_books(String d,int a,int b,int c){
		//����ͼ��  d��ͼ������ a��������b�����ͣ�c���Ƽ����
		String sql="insert into books(number,number1,b_bookstype,recommend,bookname) VALUES("+a+","+a+","+b+","+c+",'"+d+"')";
		int e=dao.update(sql);
		dao.close();
		return e;
	}
	
	public static int add_member(String a,String b,int d,int e){
		//���ӻ�Ա  a�ǻ�Ա���� b�ǻ�Ա���룬c�ǻ�Ա���ţ�d���ֻ����룬e�ǻ�Ա����
		int v=a(e);
		String sql="insert into member(name,password,phone,membertype,borrowingquantity) VALUES('"+a+"','"+b+"',"+d+","+e+","+v+")";
		int f=dao.update(sql);
		dao.close();
		return f;
	}
	public static int a(int v){
		//�õ���Ա���͵Ŀɽ�������
		ResultSet rs=null;
		int b=0;
		String sql="select borrowingquantity from m_membertype where id="+v;
		rs=dao.query(sql);
		try {
			if(rs.next()){
				b=rs.getInt(1);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		dao.close();
		return b;
	}
	
	public static int update_books(int a,int b,int c,int d){
		//�޸�ͼ�� a��ͼ��id��b��������c�����ͣ�d���Ƽ����
		String sql="UPDATE books set number="+b+",b_bookstype="+c+",recommend="+d+" where id="+a;
		int e=dao.update(sql);
		dao.close();
		return e;
	}
	
	public static int delete_books(int a){
		//ɾ��ͼ�飬a��ͼ��id
		String sql="DELETE from books where id="+a;
		int b=dao.update(sql);
		dao.close();
		return b;
	}
	
	public static ResultSet a_privileges(String username) throws SQLException {
		//�鿴����ԱȨ��
		ResultSet rs = null;
		String sql="SELECT * from a_privileges where id=(SELECT id from administrators where username='"+username+"')";
		rs=dao.query(sql);
		return rs;
	}
	
	public static ResultSet member() {
		//���һ�Աid�����ֺͿɽ�����
		ResultSet rs = null;
		String sql="SELECT mid,name from member where borrowingquantity>0";
		rs=dao.query(sql);
		return rs;
	}
	
	public static ResultSet books(){
		//����ȫ��ͼ��
		ResultSet rs = null;
		String sql="select id,bookname from books where number>0";
		rs=dao.query(sql);
		return rs;
	}
	
	public static boolean borrow(int a,int b,int c) {
		//���� a��mid b��books_id c�ǽ���߻�
		boolean flag=false;
		String sql="insert into m_b_borrow(mid,books_id,borrow,time,b_number) values("+a+","+b+","+c+",curdate(),1)";
		String sql1="update member set borrowingquantity=borrowingquantity-1 where mid="+a;
		String sql2="update books set number=number-1 where id="+b;
		int sql3=dao.update(sql);
		int sql4=dao.update(sql1);
		int sql5=dao.update(sql2);
		if ((sql3+sql4+sql5)==3){
			flag=true;
		}
		dao.close();
		return flag;
	}
	
	public static ResultSet still(){
		//���������Ϣ
		ResultSet rs=null;
		String sql="select mb.id,m.name,b.bookname,mb.time from m_b_borrow mb,member m,books b where mb.mid=m.mid and mb.books_id=b.id and mb.borrow>0";
		rs=dao.query(sql);
		return rs;
	}
	
	public static boolean return_still(String a){
		//�޸�ͼ��״̬
		int b=Integer.valueOf(a);
		boolean flag=false;
		String sql="update m_b_borrow set borrow=0,return_time=curdate() WHERE id="+b;
		String sql1="update member set borrowingquantity=borrowingquantity+1 where mid=(select mid FROM m_b_borrow where id="+b+")";
		String sql2="update books set number=number+1 where id=(select books_id FROM m_b_borrow where id="+b+")";
		int sql3=dao.update(sql);
		int sql4=dao.update(sql1);
		int sql5=dao.update(sql2);
		if ((sql3+sql4+sql5)==3){
			flag=true;
		}
		dao.close();
		return flag;
	}
	
	public static ResultSet member_query(String a) {
		//�����Ա��Ϣ
		ResultSet rs=null;
		String s=null;
		if (a!=null || a!="") {
			s="and m.name like '%"+a+"%'";
		}
		String sql="SELECT m.mid,m.name,m.card,m.phone,(mt.borrowingquantity-m.borrowingquantity),mt.membertype,mt.borrowingquantity "
				+ "FROM member m,m_membertype mt where m.membertype=mt.id "+s;
		rs=dao.query(sql);
		return rs;
	}
	
	public static ResultSet book_query(String a) {
		//���ͼ����Ϣ
		ResultSet rs=null;
		String s="";
		if (a!=null || a!="") {
			s="and b.bookname like '%"+a+"%'";
		}
		String sql="SELECT b.id,b.bookname,bb.bookstype,b.recommend,bb.borrowingdays,b.number,b.number1 "
				+ "FROM books b,b_bookstype bb where b.b_bookstype=bb.id "+s;
		rs=dao.query(sql);
		return rs;
	}
	
	public static ResultSet book_borrow(String a){
		//ͼ����ļ�¼
		ResultSet rs=null;
		String s="";
		System.out.println(a);
		if (a!=null || a!="") {
			s=" and (b.bookname like '%"+a+"%' or m.name like '%"+a+"%')";
		}
		String sql="SELECT mb.id,m.name,b.bookname,mb.time,mb.return_time,mb.borrow "
				+ "FROM m_b_borrow mb,member m,books b where mb.mid=m.mid and mb.books_id=b.id"+s;
		System.out.println(sql);
		rs=dao.query(sql);
		return rs;
	}
	
	public static ResultSet bookstype(){
		//ͼ������  
		ResultSet rs=null;
		String sql="select id,bookstype from b_bookstype";
		rs=dao.query(sql);
		return rs;
	}
	
	public static ResultSet membertype(){
		//��Ա����  
		ResultSet rs=null;
		String sql="select id,membertype from m_membertype";
		rs=dao.query(sql);
		return rs;
	}
	
	
	public static int book_type(String a,int b){
		//����ͼ������ a������  b�ǿɽ�������
		String sql="insert into b_bookstype(bookstype,borrowingdays) values('"+a+"',"+b+")";
		int c=0;
		c=dao.update(sql);
		dao.close();
		return c;
	}
	
	public static int member_type(String a,int b){
		//���ӻ�Ա���� a������  b�ǿɽ��ı���
		String sql="insert into m_membertype(membertype,borrowingquantity) values('"+a+"',"+b+")";
		int c=0;
		c=dao.update(sql);
		dao.close();
		return c;
	}
	
	public static ResultSet get_book() {
		//��ȡͼ������
		ResultSet rs=null;
		String sql="select id,bookname,b_bookstype,number1,recommend from books";
		rs=dao.query(sql);
		return rs;
	}
	
	public static ResultSet get_member() {
		//��ȡ��Ա����
		ResultSet rs=null;
		String sql="select mid,name,password,card,phone,membertype from member";
		rs=dao.query(sql);
		return rs;
	}
	
	public static ResultSet get_administrator() {
		//��ȡ����Ա����
		ResultSet rs=null;
		String sql="select a.id,a.username,a.pswd,ap.b_istration,ap.m_istration,ap.a_istration from administrators a,a_privileges ap where a.id=ap.id";
		rs=dao.query(sql);
		return rs;
	}
	
	
	
	public static int book_modify(int a,int c,int d,int e){
		//�޸�ͼ��
		int aaa=0;
		String sql="update books set b_bookstype="+c+",number="+d+",number1="+d+",recommend="+e+" where id="+a;
		aaa=dao.update(sql);
		dao.close();
		return aaa;
	}
	
	public static int member_modify(int a,String b,String c,int e,int g){
		//�޸Ļ�Ա
		int aaa=0;
		int aa=a(g);
		String sql="update member set name='"+b+"',password='"+c+"',phone="+e+",membertype="+g+",borrowingquantity="+aa+" where mid="+a;
		aaa=dao.update(sql);
		dao.close();
		return aaa;
	}
	
	public static int modify_administrator(int a,String c,int d,int e,int f){
		//�޸Ĺ���Ա
		int aaa=0,bbb=0;
		String sql="update administrators set pswd='"+c+"' where id="+a;
		String sql1="update a_privileges set b_istration="+d+",m_istration="+e+",a_istration="+f+" where id="+a;
		aaa=dao.update(sql);
		bbb=dao.update(sql1);
		dao.close();
		return aaa+bbb;
	}
	
	public static int del_books(int a){
		//ɾ��ͼ��
		int b=0;
		String sql="DELETE from books where id="+a;
		b=dao.update(sql);
		dao.close();
		return b;	
	}
	
	public static int del_member(int a){
		//ɾ����Ա
		int b=0;
		String sql="DELETE from member where mid="+a;
		b=dao.update(sql);
		dao.close();
		return b;	
	}
	
	public static int del_administrator(int a){
		//ɾ������Ա
		int b=0;
		String sql="DELETE from administrators where id="+a;
		b=dao.update(sql);
		dao.close();
		return b;	
	}
	
	public static int add_administrator(String a,String b,int c,int d,int e){
		//����ͼ������ a������  b�ǿɽ�������
		int g=0,t=0;
		String sql="insert into administrators(username,pswd) values('"+a+"','"+b+"')";
		String sql1="INSERT into a_privileges VALUES((SELECT id from administrators where username='"+a+"'),"+c+","+d+","+e+")";
		g=dao.update(sql);
		t=dao.update(sql1);
		dao.close();
		return g+t;
	}
	
	public static String username(String a) {
		//�˺�����Ӧ�������Ƿ���ȷ
		ResultSet rs=null;
		String c=null;
		String sql="select pswd from administrators where username='"+a+"'";
		rs=dao.query(sql);
		try {
			if(rs.next()){
				c=rs.getString(1);
			}
		} catch (SQLException e) {e.printStackTrace();}
		dao.close();
		return c;
	}

	public static int modify_password(String a,String b) {
		//�޸Ĺ���Ա����
		String sql="update administrators set pswd='"+a+"' where username='"+b+"'";
		int c=0;
		c=dao.update(sql);
		dao.close();
		return c;	
	}
	
	public static void main(String[] args) {
	}
	
	
}
