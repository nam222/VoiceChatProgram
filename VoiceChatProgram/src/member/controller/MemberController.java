package member.controller;

import member.model.service.MemberService;
import member.model.vo.Member;

public class MemberController {
	//회원 추가
	public int insertMember(Member m) {
		MemberService ms = new MemberService();
		int result = ms.insertMember(m);
		return result;
	}
	public int userLogin(String ID, String Password) {
		MemberService ms = new MemberService();
		int result = ms.userLogin(ID, Password);
		return result;
	}
	public int idCheck(String ID) {
		MemberService ms = new MemberService();
		int result = ms.idCheck(ID);
		return result;
	}
}
