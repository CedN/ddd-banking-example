package credit;

public class CreditAccount {
	private float balance;
	private AccountNumber accountNumber;
	private CreditCustomer accountOwner;
	private Credit credit;

	public CreditAccount(Credit credit) {
		this.accountNumber = new AccountNumber();
		this.accountOwner = credit.getCustomer();
		this.setBalance(-(credit.getAmountOfCredit()));
		this.credit = credit;
	}

	public Credit getCredit() {
		return credit;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public CreditCustomer getAccountowner() {
		return accountOwner;
	}

	public AccountNumber getAccountnumber() {
		return accountNumber;
	}

}
