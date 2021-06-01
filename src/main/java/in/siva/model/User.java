package in.siva.model;


import java.time.LocalDateTime;


public class User {

	public User(String name, String email, String password, String address, long mobileNo, long accNo, float balance) {

		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.mobileNo = mobileNo;
		this.accNo = accNo;
		this.balance = balance;
	}

	public User() {
		// Auto-generated constructor stub
	}

	private String name;
	private String email;
	private String password;
	private String address;
	private long mobileNo;
	private long accNo;
	public User(LocalDateTime createdDate) {
		super();
		this.createdDate = createdDate;
	}

	private LocalDateTime createdDate;
	private float balance;
	private boolean active;
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", address=" + address
				+ ", mobileNo=" + mobileNo + ", accNo=" + accNo + ", createdDate=" + createdDate + ", balance="
				+ balance + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}


}
