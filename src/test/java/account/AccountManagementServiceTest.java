package account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class AccountManagementServiceTest {

	public static AccountManagementService prepareTestData() {
		AccountManagementService ams = new AccountManagementService();
		Customer customer = ams.newCustomer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		ams.newAccount(1000, customer);
		ams.newAccount(5000, customer);
		ams.newAccount(2000, customer);

		customer = ams.newCustomer("Hans", "Lilienthal", LocalDate.of(1968, 9, 11));
		ams.newAccount(2000, customer);
		ams.newAccount(5000, customer);

		customer = ams.newCustomer("Dieter", "Lilienthal", LocalDate.of(1969, 9, 11));
		ams.newAccount(3000, customer);
		ams.newAccount(5000, customer);

		customer = ams.newCustomer("Franz", "Lilienthal", LocalDate.of(1964, 9, 11));
		ams.newAccount(4000, customer);
		ams.newAccount(5000, customer);

		customer = ams.newCustomer("Carsten", "Lilienthal", LocalDate.of(1965, 9, 11));
		ams.newAccount(5000, customer);

		return ams;
	}

	@Test
	void testAMSCreation() {
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData();
		assertNotNull(ams.getAccountList());
		assertNotNull(ams.getCustomerList());
		assertEquals(5, ams.getCustomerList().size());
		assertEquals(10, ams.getAccountList().size());
		int counter = 0;
		for (Customer customer : ams.getCustomerList()) {
			counter = counter + customer.getAccountList().size();
		}
		assertEquals(10, counter);
	}

	@Test
	void testAMSNewCustomerNewAccount() {
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData();

		Customer newCustomer = ams.newCustomer("Tea", "Ginster", LocalDate.of(1950, 12, 2));
		assertTrue(ams.getCustomerList().contains(newCustomer));

		Account newAccount = ams.newAccount(2000, newCustomer);
		assertTrue(ams.getAccountList().contains(newAccount));
		assertEquals(newAccount, ams.getAccount(newAccount.getAccountnumber()));
		assertEquals(newCustomer, ams.getAccount(newAccount.getAccountnumber()).getAccountowner());
		assertTrue(newCustomer.getAccountList().contains(newAccount));
		assertEquals(11, ams.getAccountList().size());

		assertTrue(ams.getAccountNumberList().contains(newAccount.getAccountnumber()));

	}

	@Test
	void testAMSTransferMoney() {
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData();

		Set<AccountNumber> accountNumbers = ams.getAccountNumberList();
		Iterator<AccountNumber> iterator = accountNumbers.iterator();
		AccountNumber debitorAccountNumber = iterator.next();
		AccountNumber creditorAccountNumber = iterator.next();
		float debitorSaldo = ams.getAccount(debitorAccountNumber).getBalance();
		float creditorSaldo = ams.getAccount(creditorAccountNumber).getBalance();
		ams.transferMoney(100, debitorAccountNumber, creditorAccountNumber);
		assertEquals(debitorSaldo - 100, ams.getAccount(debitorAccountNumber).getBalance());
		assertEquals(creditorSaldo + 100, ams.getAccount(creditorAccountNumber).getBalance());

	}

}
