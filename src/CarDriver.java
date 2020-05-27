import java.io.Serializable;
import java.time.LocalDate;

public class CarDriver extends ObjectPlusPlus implements Serializable {
  private static final long serialVersionUID = 00374L;

  LocalDate rentDate;

  public CarDriver(LocalDate rentDate, Team.Driver driver, Car car) {
    super();
    this.rentDate = rentDate;
    this.addLink(RoleUtils.CAR_ROLE, RoleUtils.CAR_DRIVER_ROLE, car);
    this.addLink(RoleUtils.DRIVER_ROLE, RoleUtils.CAR_DRIVER_ROLE, driver);
  }

  public LocalDate getRentDate() {
    return rentDate;
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("Data uzytkowania: " + getRentDate());
    return result.toString();
  }
}
