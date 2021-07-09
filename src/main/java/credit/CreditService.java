package credit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import credit.Credit.Status;

public class CreditService {

	private Map<CustomerNumber, CreditCustomer> customerList = new HashMap<>();
	private Map<AccountNumber, CreditAccount> accountList = new HashMap<>();
	private Map<CreditNumber, Credit> creditList = new HashMap<>();

	public CreditService() {
	}

	public CreditCustomer newCustomer(String firstName, String familyName, LocalDate dateOfBirth) {
		CreditCustomer customer = new CreditCustomer(firstName, familyName, dateOfBirth);
		customerList.put(customer.getCustomerNumber(), customer);
		return customer;
	}

	public List<CreditCustomer> getCustomerList() {
		return new ArrayList<CreditCustomer>(customerList.values());
	}

	public CreditNumber applyForCredit(float amount, CreditCustomer customer) {
		Credit credit = new Credit(customer, amount);
		creditList.put(credit.getCreditNumber(), credit);

		return credit.getCreditNumber();
	}

	public CreditAccount newCreditAccount(Credit credit) {
		CreditAccount account = new CreditAccount(credit);
		accountList.put(account.getAccountnumber(), account);
		credit.getCustomer().getAccountList().add(account);
		return account;
	}

	public List<CreditAccount> getAccountList() {
		return new ArrayList<CreditAccount>(accountList.values());
	}

	public CreditAccount getAccount(AccountNumber accountNumber) {
		return accountList.get(accountNumber);
	}

	public Set<AccountNumber> getAccountNumberList() {
		return accountList.keySet();
	}

	public CreditAccount grantCredit(CreditNumber creditNumber) {
		Credit credit = this.getCredit(creditNumber);
		credit.setStatus(Status.granted);
		CreditAccount newCreditAccount = this.newCreditAccount(credit);
		credit.setAccount(newCreditAccount);
		return newCreditAccount;
	}

	public Credit getCredit(CreditNumber creditNumber) {
		return creditList.get(creditNumber);
	}

	public Credit getCreditFromAccountNumber(AccountNumber accountNumber) {
		CreditAccount account = (CreditAccount) this.getAccount(accountNumber);
		return creditList.get(account.getCredit().getCreditNumber());
	}

	public void makePaymentForCredit(CreditNumber creditNumber, float amount) {
		Credit credit = creditList.get(creditNumber);
		CreditAccount creditAccount = credit.getAccount();
		float balance = creditAccount.getBalance();
		balance = balance + amount;
		creditAccount.setBalance(balance);

	}

	public void makePaymentForCreditAccount(AccountNumber accountNumber, float amount) {
		CreditAccount account = this.getAccount(accountNumber);
		float balance = account.getBalance();
		balance = balance + amount;
		account.setBalance(balance);

	}

}
