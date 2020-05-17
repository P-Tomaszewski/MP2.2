import java.io.Serializable;
import java.time.LocalDate;

public class CarDriver extends ObjectPlusPlus implements Serializable {
  private static final long serialVersionUID = 00374L;
  LocalDate dateStart;
  LocalDate dateEnd;

  public CarDriver(LocalDate dateStart, LocalDate dateEnd) {
    super();
    setDateEnd(dateEnd);
    setDateStart(dateStart);
  }

  public LocalDate getDateStart() {
    return dateStart;
  }

  public void setDateStart(LocalDate dateStart) {
    this.dateStart = dateStart;
  }

  public LocalDate getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(LocalDate dateEnd) {
    this.dateEnd = dateEnd;
  }
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append(" Data początku uzytkowania: " + getDateStart());
    result.append(" Data konca uzytkowania: " + getDateEnd());
    return result.toString();
  }


}
