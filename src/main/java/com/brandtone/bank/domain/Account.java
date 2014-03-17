package com.brandtone.bank.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.common.base.Strings;
import com.google.common.primitives.Doubles;

@Entity
public final class Account
implements Serializable {

private static final long serialVersionUID = 5076471619282704273L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, unique = true)
	private long accNumber;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private double balance;
	
	@OneToMany (mappedBy = "fromAcc")
    private Set<Transaction> transactions = new HashSet<Transaction>();
	
	// Used by JPA
	protected Account() {}
	
	private Account(final long accNumber, 
			final String name, 
			final String address, 
			final String phone, 
			final double balance) {
		
		checkNotNull(accNumber);
		checkArgument(!Strings.isNullOrEmpty(name), "name is a required parameter");
		checkArgument(!Strings.isNullOrEmpty(address), "address is a required parameter");
		checkArgument(!Strings.isNullOrEmpty(phone), "phone is a required parameter");
		checkArgument(Doubles.isFinite(balance), "invalid balance entered");
		
		this.accNumber = accNumber;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.balance = balance;
	}
	
	public static Account newInstance(final long accNumber, 
			final String name, 
			final String address, 
			final String phone, 
			final double balance) {
		return new Account(accNumber, name, address, phone, balance);
	}
	
	public void lodge(double amount) {
		this.balance = balance + amount;
	}
	
	public void withdraw(double amount) {
		checkArgument(this.balance - amount > 0, "withdraw amount too large for balance:");
		this.balance = balance - amount;
	}
	
	public long getId() { return id; }
	public long getNumber() { return accNumber; }
	public String getName() { return name; }
	public String getAddress() { return address; }
	public String getPhone() { return phone; }
	public double getBalance() { return balance; }
	
	public Set<Transaction> getTransactions() { return transactions; }
	
	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
	
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
	      
	      return 
	    		  	Objects.equals(this.accNumber, this.accNumber)
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