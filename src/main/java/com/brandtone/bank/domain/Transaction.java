package com.brandtone.bank.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.common.base.Strings;
import com.google.common.primitives.Doubles;
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
		private String transactionType;
		
		@Column(nullable = false)
		private double amount;
			
		@Column(nullable = true)
		private long fromAccount;
		
		@Column(nullable = true)
		private long toAccount;
		
		@Column(nullable = false)
		private Date transactionDate;
		
		@ManyToOne
		@JoinColumn(name="account_id")
	    private Account fromAcc;
		 	
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
			return new Transaction(Type.LODGE.toString(),userAccount, amount,userAccount.getId(),userAccount.getId());
		}
		
		/**
		 * Withdraw Transaction
		 * 
		 * @param transactionType
		 * @param amount
		 * @return Transaction
		 */
		public static Transaction withdrawTransactionInstance (final Account userAccount, final double amount) {
			return new Transaction(Type.WITHDRAW.toString(),userAccount,amount,userAccount.getId(),userAccount.getId());
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
			return new Transaction(Type.TRANSFER.toString(),fromAccount, amount, fromAccount.getId(), toAccount.getId() );
		}
		
		private Transaction(final String transactionType,final Account fromAcc,
				final double amount, final long fromAccount, final long toAccount) {
			
			checkArgument(!Strings.isNullOrEmpty(transactionType), "transactionType is a required parameter");
			checkArgument(Doubles.isFinite(amount),"invalid amount entered");
			checkNotNull(fromAccount);
			checkNotNull(toAccount);
			
			this.transactionType = transactionType;
			this.fromAcc = fromAcc;
			this.amount = amount;
			this.fromAccount = fromAccount;
			this.toAccount = toAccount;
			this.transactionDate = new Date();
			
		}
			
		public long getId() { return id; }
		public Date getTransactionDate() { return transactionDate; }
		public String getTransactionType() { return transactionType; }
		public double getAmount() { return amount; }
		public long getAccountFrom() { return fromAccount; }
		public long getAccountTo() {return toAccount; }
		public Account getFromAcc() { return fromAcc; }
		
		public void setFromAcc(Account fromAcc) {
			this.fromAcc = fromAcc;
		}
		
		
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
