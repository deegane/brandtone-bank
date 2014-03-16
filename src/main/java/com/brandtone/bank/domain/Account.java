package com.brandtone.bank.domain;

import java.util.Objects;

import com.google.common.base.Strings;
import com.google.common.primitives.Doubles;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Account {

	private long id;
	private long number;
	private final String name;
	private final String address;
	private final String phone;
	private double balance;
		
	public Account(long id,
			long number, 
			String name, 
			String address, 
			String phone, 
			double balance) {
		
		checkNotNull(id);
		checkNotNull(number);
		checkArgument(!Strings.isNullOrEmpty(name), "name is a required parameter");
		checkArgument(!Strings.isNullOrEmpty(address), "address is a required parameter");
		checkArgument(!Strings.isNullOrEmpty(phone), "name is a required parameter");
		checkArgument(Doubles.isFinite(balance), "invalid balance entered");
		
		this.id = id;
		this.number = number;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.balance = balance;
	}
	
	public long getId() { return id; }
	public long getNumber() { return number; }
	public String getName() { return name; }
	public String getAddress() { return address; }
	public String getPhone() { return phone; }
	public double getBalance() { return balance; }
	
	@Override
	public String toString() {
	   return com.google.common.base.Objects.toStringHelper(this)
	       .add("id", id)
	       .add("number", number)
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
