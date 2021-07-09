package account;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountManagementService {
	private Map<CustomerNumber, Customer> customerList = new HashMap<>();
	private Map<AccountNumber, Account> accountList = new HashMap<>();

	public AccountManagementService() {

	}

	public Customer newCustomer(String firstName, String familyName, LocalDate dateOfBirth) {
		Customer customer = new Customer(firstName, familyName, dateOfBirth);
		customerList.put(customer.getCustomerNumber(), customer);
		return customer;
	}

	public Account newAccount(float balance, Customer customer) {
		Account account = new Account(customer);
		account.setBalance(balance);
		accountList.put(account.getAccountnumber(), account);
		customer.getAccountList().add(account);
		return account;
	}

	public List<Account> getAccountList() {
		return new ArrayList<Account>(accountList.values());
	}

	public List<Customer> getCustomerList() {
		return new ArrayList<Customer>(customerList.values());
	}

	public void transferMoney(float amount, AccountNumber debitorAccountNumber, AccountNumber creditorAccountNumber) {
		float balance = accountList.get(debitorAccountNumber).getBalance();
		balance = balance - amount;
		accountList.get(debitorAccountNumber).setBalance(balance);

		balance = accountList.get(creditorAccountNumber).getBalance();
		balance = balance + amount;
		accountList.get(creditorAccountNumber).setBalance(balance);

	}

	public Set<AccountNumber> getAccountNumberList() {
		return accountList.keySet();
	}

	public Account getAccount(AccountNumber accountNumber) {
		return accountList.get(accountNumber);
	}

	public Customer getCustomer(AccountNumber accountNumber) {
		return accountList.get(accountNumber).getAccountowner();
	}

}
