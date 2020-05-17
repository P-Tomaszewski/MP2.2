import java.io.Serializable;

public class Race extends ObjectPlusPlus implements Serializable {
  private static final long serialVersionUID = 0073L;
  private String raceName;

  public void setRaceName(String raceName) {
    this.raceName = raceName;
  }

  public String getRaceName() {
    return raceName;
  }

  public Race(String raceName) {
    super();
    setRaceName(raceName);
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("Nazwa wyscigu: : " + getRaceName());
    return result.toString();
  }
}
