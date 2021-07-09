package credit;

public class CreditNumber {
  private static int creditNumberCounter = 0;

  private final int value;

  public CreditNumber() {
    value = ++creditNumberCounter;
  }

  public int value() {
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    return obj != null && obj instanceof CreditNumber && this.value == ((CreditNumber) obj).value;
  }

  @Override
  public int hashCode() {
    return value;
  }
  
}
