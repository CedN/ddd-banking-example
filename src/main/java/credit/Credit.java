package credit;

public class Credit {
	private float amountOfCredit;
	private CreditNumber creditNumber;
	private Status status;
	private CreditCustomer customer;
	private CreditAccount account;

	public enum Status {
		applied, refused, granted, delayed, payed
	};

	public Credit(CreditCustomer customer, float amountOfCredit) {
		super();
		this.amountOfCredit = amountOfCredit;
		this.creditNumber = new CreditNumber();
		this.customer = customer;
		this.status = Status.applied;
	}

	public float getAmountOfCredit() {
		return amountOfCredit;
	}

	public void setAmountOfCredit(float amountOfCredit) {
		this.amountOfCredit = amountOfCredit;
	}

	public CreditCustomer getCustomer() {
		return customer;
	}

	public CreditNumber getCreditNumber() {
		return creditNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public CreditAccount getAccount() {
		return account;
	}

	public void setAccount(CreditAccount account) {
		this.account = account;
	}

}
