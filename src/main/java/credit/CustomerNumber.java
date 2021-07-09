package credit;

public class CustomerNumber {
  private static int customerNumberCounter = 0;

  private final int value;

  public CustomerNumber() {
    value = ++customerNumberCounter;
  }

  public int value() {
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    return obj != null && obj instanceof CustomerNumber && this.value == ((CustomerNumber) obj).value;
  }

  @Override
  public int hashCode() {
    return value;
  }
}
