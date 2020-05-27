import java.io.Serializable;

public class Car extends ObjectPlusPlus implements Serializable  {
  private static final long serialVersionUID = 00371L;
  private static int counter = 1;
  private int id;
  String registrationNumber;

  public Car(String registrationNumber) {
    super();
    this.id = counter++;
    this.registrationNumber = registrationNumber;
  }

  public int getId() {
    return id;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("NumerRejestracyny: " + getRegistrationNumber());
    return result.toString();
  }

}
