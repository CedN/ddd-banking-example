package credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import credit.Credit.Status;

class CreditServiceTest {

	public static CreditService prepareTestData() {
		CreditService creditService = new CreditService();

		creditService.newCustomer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		creditService.newCustomer("Hans", "Lilienthal", LocalDate.of(1968, 9, 11));
		creditService.newCustomer("Dieter", "Lilienthal", LocalDate.of(1969, 9, 11));
		creditService.newCustomer("Franz", "Lilienthal", LocalDate.of(1964, 9, 11));
		creditService.newCustomer("Carsten", "Lilienthal", LocalDate.of(1965, 9, 11));

		return creditService;
	}

	@Test
	void testNewCustomerAccount() {
		CreditService cs = prepareTestData();

		CreditCustomer newCustomer = cs.newCustomer("Tea", "Ginster", LocalDate.of(1950, 12, 2));
		Credit credit = new Credit(newCustomer, new Amount(10));
		CreditAccount newCreditAccount = cs.newCreditAccount(credit);
		assertTrue(cs.getAccountList().contains(newCreditAccount));
		assertEquals(newCreditAccount, cs.getAccount(newCreditAccount.getAccountnumber()));
		assertEquals(1, cs.getAccountList().size());

		assertTrue(cs.getAccountNumberList().contains(newCreditAccount.getAccountnumber()));
		assertTrue(newCustomer.getAccountList().contains(newCreditAccount));
	}

	@Test
	void testCSCreation() {
		CreditService cs = CreditServiceTest.prepareTestData();

		CreditNumber creditNumber = cs.applyForCredit(new Amount(1000), cs.getCustomerList().get(0));
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(new Amount(1000), credit.getAmountOfCredit());
		assertTrue(credit.getStatus() == Status.applied);

		CreditAccount creditAccount = cs.grantCredit(creditNumber);
		assertEquals(credit, creditAccount.getCredit());
		assertTrue(credit.getStatus() == Status.granted);
		assertTrue(credit.getAccount() == creditAccount);
		assertTrue(cs.getAccountList().contains(creditAccount));
		assertTrue(cs.getCustomerList().contains(creditAccount.getAccountowner()));
		assertEquals(1, cs.getAccountList().size());
		assertEquals(credit, creditAccount.getCredit());
		assertTrue(credit.getCustomer().getCreditList().contains(credit));

		Credit credit2 = cs.getCreditFromAccountNumber(creditAccount.getAccountnumber());
		assertEquals(credit, credit2);

	}

	@Test
	void testCreditProcess() {
		CreditService cs = prepareTestData();

		CreditNumber creditNumber = cs.applyForCredit(new Amount(1000), cs.getCustomerList().get(0));
		Credit credit = cs.getCredit(creditNumber);

		CreditAccount creditAccount = cs.grantCredit(creditNumber);
		assertEquals(credit, creditAccount.getCredit());
		assertTrue(credit.getStatus() == Status.granted);
		assertTrue(credit.getAccount() == creditAccount);
		assertEquals(new Amount(-1000), creditAccount.getBalance());
		assertEquals(1, cs.getAccountList().size());

		cs.makePaymentForCredit(creditNumber, new Amount(100));
		assertEquals(new Amount(-900), creditAccount.getBalance());
		assertEquals(new Amount(1000), credit.getAmountOfCredit());

	}

}
