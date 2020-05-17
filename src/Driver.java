import java.io.Serializable;

public class Driver extends ObjectPlusPlus implements Serializable {
  private static final long serialVersionUID = 0037L;
  private String name;
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Driver(String name) {
    super();
    setName(name);
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("Imie: : " + getName());
    return result.toString();
  }
}
