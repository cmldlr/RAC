
public class User {
	private String name;
	private String surname;
	private String nickName;
	private String mail;
	private String password;
	private String phoneNumber;
	private boolean admin;
	
	public User(String name, String surname, String nickName, String mail, String password, String phoneNumber, boolean admin) {
		this.name = name;
		this.surname = surname;
		this.nickName = nickName;
		this.mail = mail;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.admin = admin;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}

}
