package study.cafe.luna.management.dto;

public class ManagementDTO { 
	//회원관리
	private String id; 
	private String name;
	private String phone;
	private String email;
	private int brstatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getBrstatus() {
		return brstatus;
	}
	public void setBrstatus(int brstatus) {
		this.brstatus = brstatus;
	}
	
	
	
}
