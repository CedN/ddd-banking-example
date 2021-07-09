package credit;

public class AccountNumber {
  private static int accountNumberCounter = 0;

  private final int value;

  public AccountNumber() {
    value = ++accountNumberCounter;
  }

  public int value() {
    return value;
  }
  
}
