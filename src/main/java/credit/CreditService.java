package credit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import credit.Credit.Status;

public class CreditService {

	private Map<Integer, CreditCustomer> customerList = new HashMap<>();
	private int customerNumberCounter = 0;
	private Map<Integer, CreditAccount> accountList = new HashMap<>();
	private int accountNumberCounter = 0;

	private int creditNumberCounter = 0;
	private Map<Integer, Credit> creditList = new HashMap<>();

	public CreditService() {
	}

	public CreditCustomer newCustomer(String firstName, String familyName, LocalDate dateOfBirth) {
		CreditCustomer customer = new CreditCustomer(firstName, familyName, dateOfBirth, customerNumberCounter++);
		customerList.put(customer.getCustomerNumber(), customer);
		return customer;
	}

	public List<CreditCustomer> getCustomerList() {
		return new ArrayList<CreditCustomer>(customerList.values());
	}

	public int applyForCredit(float amount, CreditCustomer customer) {
		int creditNumber = creditNumberCounter++;
		Credit credit = new Credit(creditNumber, customer, amount);
		creditList.put(creditNumber, credit);

		return creditNumber;
	}

	public CreditAccount newCreditAccount(Credit credit) {
		CreditAccount account = new CreditAccount(accountNumberCounter++, credit);
		accountList.put(account.getAccountnumber(), account);
		credit.getCustomer().getAccountList().add(account);
		return account;
	}

	public List<CreditAccount> getAccountList() {
		return new ArrayList<CreditAccount>(accountList.values());
	}

	public CreditAccount getAccount(int accountNumber) {
		return accountList.get(accountNumber);
	}

	public Set<Integer> getAccountNumberList() {
		return accountList.keySet();
	}

	public CreditAccount grantCredit(int creditNumber) {
		Credit credit = this.getCredit(creditNumber);
		credit.setStatus(Status.granted);
		CreditAccount newCreditAccount = this.newCreditAccount(credit);
		credit.setAccount(newCreditAccount);
		return newCreditAccount;
	}

	public Credit getCredit(int creditNumber) {
		return creditList.get(creditNumber);
	}

	public Credit getCreditFromAccountNumber(int accountNumber) {
		CreditAccount account = (CreditAccount) this.getAccount(accountNumber);
		return creditList.get(account.getCredit().getCreditNumber());
	}

	public void makePaymentForCredit(int creditNumber, float amount) {
		Credit credit = creditList.get(creditNumber);
		CreditAccount creditAccount = credit.getAccount();
		float balance = creditAccount.getBalance();
		balance = balance + amount;
		creditAccount.setBalance(balance);

	}

	public void makePaymentForCreditAccount(int accountNumber, float amount) {
		CreditAccount account = this.getAccount(accountNumber);
		float balance = account.getBalance();
		balance = balance + amount;
		account.setBalance(balance);

	}

}
