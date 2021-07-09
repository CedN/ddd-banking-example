package account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class AccountTest {

	@Test
	void testAccountConstruction() {

		Customer accountowner = new Customer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Account account = new Account(accountowner);
		assertNotNull(account.getAccountnumber());
		assertEquals(new Amount(0), account.getBalance());
		assertEquals(accountowner, account.getAccountowner());
	}

	@Test
	void testBalanceAccount() {
		Customer accountowner = new Customer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Account account = new Account(accountowner);
		assertEquals(new Amount(0), account.getBalance());
		account.setBalance(new Amount(100));
		assertEquals(new Amount(100), account.getBalance());

	}

}
