package credit;

public class CreditAccount {
	private Amount balance;
	private AccountNumber accountNumber;
	private CreditCustomer accountOwner;
	private Credit credit;

	public CreditAccount(Credit credit) {
		this.accountNumber = new AccountNumber();
		this.accountOwner = credit.getCustomer();
		this.balance = new Amount(-credit.getAmountOfCredit().value());
		this.credit = credit;
	}

	public Credit getCredit() {
		return credit;
	}

	public Amount getBalance() {
		return balance;
	}

	public void setBalance(Amount balance) {
		this.balance = balance;
	}

	public CreditCustomer getAccountowner() {
		return accountOwner;
	}

	public AccountNumber getAccountnumber() {
		return accountNumber;
	}

}
