package account;

public class Amount {
  private final float value;

  public Amount(float value) {
    this.value = value;
  }

  public float value() {
    return value;
  }

  public Amount substract(Amount amount) {
    return new Amount(value - amount.value);
  }

  public Amount add(Amount amount) {
    return new Amount(value + amount.value);
  }

  @Override
  public boolean equals(Object obj) {
    return obj != null && obj instanceof Amount && value == ((Amount)obj).value;
  }
}
