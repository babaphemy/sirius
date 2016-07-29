package net.myeverlasting.newsirius.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ta_alltranx")
public class Transaction  {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String paymentstatus;
	private String txnref;
	private int amount;
	private String name;
	private String email;
	private String mobile;
	private String CardNumber;
	private String ResponseCode;
	private String responseDescription;
	private String paymentRef;
	@Temporal(TemporalType.TIMESTAMP)
	private Date tranxdate;
	private String customername;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaymentstatus() {
		return paymentstatus;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public String getTxnref() {
		return txnref;
	}
	public void setTxnref(String txnref) {
		this.txnref = txnref;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
	public String getResponseCode() {
		return ResponseCode;
	}
	public void setResponseCode(String responseCode) {
		ResponseCode = responseCode;
	}
	public String getResponseDescription() {
		return responseDescription;
	}
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
	public String getPaymentRef() {
		return paymentRef;
	}
	public void setPaymentRef(String paymentRef) {
		this.paymentRef = paymentRef;
	}
	public Date getTranxdate() {
		return tranxdate;
	}
	public void setTranxdate(Date tranxdate) {
		this.tranxdate = tranxdate;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	
	
	
	
	

}
