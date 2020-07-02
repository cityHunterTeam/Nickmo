package member;

import java.sql.Date;

public class MemberVO {
	private String id;
	private String passwd;
	private String name;
	private String birth;
	private String email;
	private String phone;
	private String address;
	
	public MemberVO(String id, String passwd, String name, String birth, String email, String phone, String address) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	public MemberVO(String passwd, String name, String birth, String email, String phone, String address) {
		this.passwd = passwd;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public MemberVO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
}
 