import java.io.*;
import java.time.LocalDate;

public class Main {
  /*
  Asocjacje:
 - „Zwykła” +
 - Z atrybutem +
 - Kwalifikowana +
 - Kompozycja +
(w każdym przypadku: liczności 1-* lub
*-* oraz automatyczne tworzenie poł. zwrotnego)
   */

  /*
  Do poprawy role na stałe
  Relacja z atrybutem, powiazanie tworzone w konstruktorze klasy posredniej.

   */
  public static void main(String[] args) throws Exception {

    if(new File("Ekstensja").isFile()) {
      try {
        FileInputStream fileInput = new FileInputStream("Ekstensja");
        ObjectInputStream streamInput = new ObjectInputStream(fileInput);
        //odczyt z "daneFirmowe"
        ObjectPlus.readEkstansja(streamInput);
        streamInput.close();
        fileInput.close();
      } catch(IOException i) {
        i.printStackTrace();
        return;
      } catch(ClassNotFoundException c) {
        System.out.println("Nie znaleziono klasy.");
        c.printStackTrace();
        return;
      }
    }


    Car car = new Car("WSF1234");
    Car car1 = new Car("POI0937");
    Car car2 = new Car("KLS1233");

    Race race = new Race("Wyscig testowy 1");
    Race race1 = new Race("Wyscig testowy 2");

    Team team = new Team("Szybcy");
    Team team1 = new Team("SUPER Szybcy");
    Team team2 = new Team("Wolni");

    Team.Driver driver = team.createDriver("Janusz", "Nowak"); //Kompozycja
    Team.Driver driver1 = team.createDriver("Piotr", "Pawelec");
    Team.Driver driver2 = team.createDriver("Pawel", "Kapusta");
    //      team.addPart(Team.Driver.class.getName(), Team.class.getName(), driver); // wyjatek poniewaz dodawana czesc (Kierowca) nalezy do innego team'u

    CarDriver carDriver = new CarDriver(LocalDate.now(),driver, car ); //Asocjacja z zatrybutem
    CarDriver carDriver1 = new CarDriver(LocalDate.now().minusDays(2), driver, car);
    CarDriver carDriver2 = new CarDriver(LocalDate.now().minusDays(1), driver1, car1);

    race.addLink(RoleUtils.DRIVER_ROLE, RoleUtils.RACE_ROLE, driver);  //Zwykła
    race.addLink(RoleUtils.DRIVER_ROLE, RoleUtils.RACE_ROLE, driver1);
    race1.addLink(RoleUtils.DRIVER_ROLE, RoleUtils.RACE_ROLE, driver);
    race1.addLink(RoleUtils.DRIVER_ROLE, RoleUtils.RACE_ROLE, driver1);
    race1.addLink(RoleUtils.DRIVER_ROLE, RoleUtils.RACE_ROLE, driver2);

    race.addLink(RoleUtils.CAR_ROLE, RoleUtils.RACE_ROLE, car, car.getId());// Kwalifikowana
    race.addLink(RoleUtils.CAR_ROLE, RoleUtils.RACE_ROLE, car1, car1.getId());

    System.out.println("-----PowiazanieZwrotne");
    race1.showLinks(RoleUtils.DRIVER_ROLE, System.out); //powiazania zwrotne
    System.out.println("-----");
    driver.showLinks(RoleUtils.RACE_ROLE, System.out);


    System.out.println("-----Kwalifikowana");
    System.out.println(race.getLinkedObject(RoleUtils.CAR_ROLE, car.getId())); //Kwalifikowana
    System.out.println(race.getLinkedObject(RoleUtils.CAR_ROLE, car1.getId()));

    System.out.println("-----ZAtrybutem");
    carDriver.showLinks(RoleUtils.DRIVER_ROLE, System.out); //Relacje w asocjacji z atrybutem.
    carDriver.showLinks(RoleUtils.CAR_ROLE, System.out);
    car.showLinks(RoleUtils.CAR_DRIVER_ROLE, System.out);


    System.out.println("----Kompozycja");
    team.showLinks(RoleUtils.DRIVER_ROLE, System.out);
    driver.showLinks(RoleUtils.TEAM_ROLE, System.out);


    try {
      // do pliku "DaneOfertSprzetuUslug"
      FileOutputStream fileOutput = new FileOutputStream("Ekstensja");
      ObjectOutputStream StreamOutput = new ObjectOutputStream(fileOutput);
      ObjectPlus.saveEkstansja(StreamOutput);
      StreamOutput.close();
      fileOutput.close();
    } catch(IOException i) {
      i.printStackTrace();
    }

  }
}
