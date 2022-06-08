package member.model.service;

import static common.JDBCTemplate.getConnection;

import java.awt.print.Book;
import java.sql.Connection;
import java.util.ArrayList;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import static common.JDBCTemplate.*;

public class MemberService {
	//회원 추가
	public int insertMember(Member m) {
		Connection con = getConnection();
		MemberDao md = new MemberDao();
		int result = md.insetMember(con, m);
		if(result >0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}
	//로그인	
	public int userLogin(String ID, String Password) {
		Connection con = getConnection();
		MemberDao md = new MemberDao();
		int result = md.userLogin(con, ID, Password);
		if(result >0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}
	public int idCheck(String ID) {
		Connection con = getConnection();
		MemberDao md = new MemberDao();
		int result = md.idCheck(con, ID);
		if(result >0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}
}
