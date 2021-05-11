package in.siva.model;

public class User {

	public User(String name, String email, String password, String address, long mobileNo, long accNo, double balance,
			boolean blockedAcc) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.mobileNo = mobileNo;
		this.accNo = accNo;
		
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String name;
	public String email;
	public String password;
	public String address;
	public long mobileNo;
	public long accNo;
	

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
				+ ", mobileNo=" + mobileNo + ", accNo=" + accNo +  ", getName()=" + getName() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword()
				+ ", getAddress()=" + getAddress() + ", getMobileNo()=" + getMobileNo() + ", getAccNo()=" + getAccNo()
				+ ",  getClass()=" + getClass()+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
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

	
}
