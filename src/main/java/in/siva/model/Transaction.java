package in.siva.model;

import java.time.LocalDateTime;


public class Transaction {


public Transaction() {
	super();
	
}
private float amount;
private String transactionType;
private String comments = "TRANSACTION THROUGH UPI";
private LocalDateTime transactionDateTime;
private User user;

public User getUser() {
	return user;
}


public void setUser(User user) {
	this.user = user;
}


public Transaction(float amount, String transactionType, String comments, LocalDateTime transactionDateTime) {
	super();
	
	this.amount = amount;
	this.transactionType = transactionType;
	this.comments = comments;
	this.transactionDateTime = transactionDateTime;
}



public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}
public String getTransactiontype() {
	return transactionType;
}
public void setTransactiontype(String transactionType) {
	this.transactionType = transactionType;
}
public String getComments() {
	return comments;
}
public void setComments(String comments) {
	this.comments = comments;
}
public LocalDateTime getTransactionDate() {
	return transactionDateTime;
}
public void setTransactionDate(LocalDateTime transactionDateTime) {
	this.transactionDateTime = transactionDateTime;
}

@Override
public String toString() {
	return "transaction [amount=" + amount + ", transactionType=" + transactionType
			+ ", comments=" + comments + ", transactionDate=" + transactionDateTime + "]";
}
}