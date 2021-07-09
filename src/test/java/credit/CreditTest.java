package credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CreditTest {

	@Test
	void testCreditConstruction() {

		CreditCustomer customer = new CreditCustomer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Credit credit = new Credit(customer, 1000);
		assertEquals(1000, credit.getAmountOfCredit());
		assertEquals(customer, credit.getCustomer());
		assertNotNull(credit.getCreditNumber().value());
	}
}
