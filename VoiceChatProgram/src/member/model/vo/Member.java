package member.model.vo;

import java.sql.Date;

public class Member {
	private String name;
	private String password;
	
	public Member() {}
	
	public Member(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}
	


	
}
