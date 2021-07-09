package account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class AccountTest {

	@Test
	void testAccountConstruction() {

		Customer accountowner = new Customer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Account account = new Account(accountowner);
		assertTrue(account.getAccountnumber().value() > 0);
		assertEquals(0, account.getBalance());
		assertEquals(accountowner, account.getAccountowner());
	}

	@Test
	void testBalanceAccount() {
		Customer accountowner = new Customer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Account account = new Account(accountowner);
		assertEquals(0, account.getBalance());
		account.setBalance(100);
		assertEquals(100, account.getBalance());

	}

}
