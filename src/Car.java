import java.io.Serializable;

public class Car extends ObjectPlusPlus implements Serializable  {
  private static final long serialVersionUID = 00371L;
  String registrationNumber;

  public Car(String registrationNumber) {
    super();
    setRegistrationNumber(registrationNumber);
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("NumerRejestracyny: " + getRegistrationNumber());
    return result.toString();
  }

}
