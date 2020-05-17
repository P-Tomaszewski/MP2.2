import java.io.Serializable;
import java.util.Map;

public class Team extends ObjectPlusPlus implements Serializable {
  private static final long serialVersionUID = 0073L;
  private String teamName;

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public Team(String teamName) {
    super();
    setTeamName(teamName);
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("Nazwa druzyny: " + getTeamName());
    return result.toString();
  }



}
