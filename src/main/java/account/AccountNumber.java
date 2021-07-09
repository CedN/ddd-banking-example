package account;

public class AccountNumber {
  private static int accountNumberCounter = 0;

  private final int value;

  public AccountNumber() {
    value = ++accountNumberCounter;
  }

  public int value() {
    return value;
  }
  
  @Override
  public boolean equals(Object obj) {
    return obj != null && obj instanceof AccountNumber && this.value == ((AccountNumber) obj).value;
  }

  @Override
  public int hashCode() {
    return value;
  }
}
