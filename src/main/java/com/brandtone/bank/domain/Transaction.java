package com.brandtone.bank.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.google.common.base.Strings;
import com.google.common.primitives.Doubles;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Transaction domain object
 * 
 * @author deegane
 *
 */

	@Entity
	public class Transaction 
		implements Serializable {
	
		private static final long serialVersionUID = -5913547234157325825L;
		
		public enum Type
		{
			LODGE, WITHDRAW, TRANSFER
		}
		
		@Id
		@GeneratedValue
		private long id;
		
		@Column(nullable = false)
		private Date transactionDate;
		
		@Column(nullable = false)
		public String transactionType;
		
		@Column(nullable = false)
		public double amount;
		
		@Column(nullable = true)
		public Account fromAccount;
		
		@Column(nullable = true)
		public Account toAccount;
		
		
		//Used by JPA
		protected Transaction() {}
		
		/**
		 * Lodge Transaction
		 * 
		 * @param transactionType
		 * @param amount
		 * @return Transaction
		 */
		public static Transaction lodgeTransactionInstance (final Account userAccount, final double amount) {
			return new Transaction(Type.LODGE.toString(), amount,userAccount,userAccount);
		}
		
		/**
		 * Withdraw Transaction
		 * 
		 * @param transactionType
		 * @param amount
		 * @return Transaction
		 */
		public static Transaction withdrawTransactionInstance (final Account userAccount, final double amount) {
			return new Transaction(Type.WITHDRAW.toString(),amount,userAccount,userAccount);
		}
		
		/**
		 * Create transfer transaction
		 * 
		 * @param transactionType
		 * @param amount
		 * @param accountFrom
		 * @param accountTo
		 * @return Transaction
		 */
		public static Transaction transferTransactonInstance( final double amount, final Account fromAccount, final Account toAccount) {
			return new Transaction(Type.TRANSFER.toString(), amount, fromAccount, toAccount );
		}
		
		private Transaction(final String transactionType,
				final double amount, final Account fromAccount, final Account toAccount) {
			
			checkArgument(!Strings.isNullOrEmpty(transactionType), "transactionType is a required parameter");
			checkArgument(Doubles.isFinite(amount),"invalid amount entered");
			checkNotNull(fromAccount);
			checkNotNull(toAccount);
			
			this.transactionType = transactionType;
			this.amount = amount;
			this.fromAccount = fromAccount;
			this.toAccount = toAccount;
			this.transactionDate = new Date();
		}
			
		public long getId() { return id; }
		public Date getTransactionDate() { return transactionDate; }
		public String getTransactionType() { return transactionType; }
		public double getAmount() { return amount; }
		public Account getAccountFrom() { return fromAccount; }
		public Account getAccountTo() {return toAccount; }
		
		
		@Override
		public String toString() {
		   return com.google.common.base.Objects.toStringHelper(this)
		       .add("id", id)
		       .add("transactionDate", transactionDate)
		       .add("transactionType", transactionType)
		       .add("amount",amount)
		       .add("fromAccount", fromAccount)
		       .add("toAccount",toAccount)
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
		     
		     final Transaction other = (Transaction) obj;
		      
		     return		Objects.equals(this.id, other.id)
		    		 && Objects.equals(this.transactionDate, other.transactionDate)
		             && Objects.equals(this.transactionType, other.transactionType)
		             && Objects.equals(this.amount, other.amount)   
		             && Objects.equals(this.fromAccount, other.fromAccount)
		             && Objects.equals(this.toAccount, other.toAccount); 
		   }
		  
		  @Override
		  public int hashCode() {
			  return Objects.hash(this.id,
					  this.transactionDate,
					  this.transactionType,
					  this.amount,
					  this.fromAccount,
					  this.toAccount);
		  }	  
}
