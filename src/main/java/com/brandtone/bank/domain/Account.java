package com.brandtone.bank.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.base.Strings;
import com.google.common.primitives.Doubles;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Entity
public final class Account
implements Serializable {

private static final long serialVersionUID = 5076471619282704273L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private long accNumber;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private double balance;

	// Used by JPA
	protected Account() {}
		
	public Account(long id,
			long accNumber, 
			String name, 
			String address, 
			String phone, 
			double balance) {
		
		checkNotNull(id);
		checkNotNull(accNumber);
		checkArgument(!Strings.isNullOrEmpty(name), "name is a required parameter");
		checkArgument(!Strings.isNullOrEmpty(address), "address is a required parameter");
		checkArgument(!Strings.isNullOrEmpty(phone), "name is a required parameter");
		checkArgument(Doubles.isFinite(balance), "invalid balance entered");
		
		this.id = id;
		this.accNumber = accNumber;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.balance = balance;
	}
	
	public void lodge(double amount) {
		this.balance = balance + amount;
	}
	
	public void withdraw(double amount) {
		this.balance = balance - amount;
	}
	
	public long getId() { return id; }
	public long getNumber() { return accNumber; }
	public String getName() { return name; }
	public String getAddress() { return address; }
	public String getPhone() { return phone; }
	public double getBalance() { return balance; }
	
	@Override
	public String toString() {
	   return com.google.common.base.Objects.toStringHelper(this)
	       .add("id", id)
	       .add("number", accNumber)
	       .add("name", name)
	       .add("address",address)
	       .add("phone", phone)
	       .add("balance",balance)
	       .toString();
	}
	
	  @Override
	   public boolean equals(Object obj) 
	   {
	      if (obj == null)
	      {
	         return false;
	      }
	      if (getClass() != obj.getClass())
	      {
	         return false;
	      }
	      
	      final Account other = (Account) obj;
	      
	      return    Objects.equals(this.id, other.id)
	    		 && Objects.equals(this.name, other.name)
	             && Objects.equals(this.address, other.address)
	             && Objects.equals(this.phone, other.phone)   
	             && Objects.equals(this.balance, other.balance);         
	   }
	  
	  @Override
	  public int hashCode() {
		  return Objects.hash(this.id,
				  this.name,
				  this.address,
				  this.phone,
				  this.balance);
	  }
}
