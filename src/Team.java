import java.io.Serializable;

public class Team extends ObjectPlusPlus implements Serializable {
  private static final long serialVersionUID = 0073L;
  private String teamName;

  public String getTeamName() {
    return teamName;
  }

  public Team(String teamName) {
    super();
    this.teamName = teamName;
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("Nazwa druzyny: " + getTeamName());
    return result.toString();
  }

  public Driver createDriver(String driverName, String driverLastName) throws Exception{
    Driver driver = new Driver(driverName, driverLastName);
    this.addPart(RoleUtils.DRIVER_ROLE, RoleUtils.TEAM_ROLE, driver);
    return driver;
  }

  public class Driver extends ObjectPlusPlus {
    public String name;
    public String lastName;

    public String getLastName() {
      return lastName;
    }
    public String getName() {
      return name;
    }

    private Driver(String name, String lastName) {
      super();
      this.name = name;
      this.lastName= lastName;
    }

    public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("Imie i nazwisko: " + getName() + " " + getLastName());
      return result.toString();
    }
  }




}
