package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;

public class MemberDao {

	public int insetMember(Connection con, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {

			Properties prop = new Properties();
			prop.load(new FileReader("query.properties"));
			String query = prop.getProperty("insertMember");

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getPassword());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int userLogin(Connection con, String ID, String Password) {
		PreparedStatement pstmt = null;
		ResultSet rs;

		try {

			Properties prop = new Properties();
			prop.load(new FileReader("query.properties"));
			String query = prop.getProperty("login");

			pstmt = con.prepareStatement(query);
			pstmt.setString(1,ID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).contentEquals(Password)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 아이디가 없음
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return -2;
	}
	public int idCheck(Connection con, String ID) {
		PreparedStatement pstmt = null;
		ResultSet rs;
		int result = 0;

		try {

			Properties prop = new Properties();
			prop.load(new FileReader("query.properties"));
			String query = prop.getProperty("login");

			pstmt = con.prepareStatement(query);
			pstmt.setString(1,ID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
