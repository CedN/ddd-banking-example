package account;

public class Account {
	private float balance;
	private AccountNumber accountNumber;
	private Customer accountOwner;

	public Account(Customer accountOwner) {
		super();
		this.balance = 0;
		this.accountNumber = new AccountNumber();
		this.accountOwner = accountOwner;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public AccountNumber getAccountnumber() {
		return accountNumber;
	}

	public Customer getAccountowner() {
		return accountOwner;
	}
	
}
