import java.io.*;
import java.time.LocalDate;

public class Main {
  /*
  Asocjacje:
 - „Zwykła”
 - Z atrybutem
 - Kwalifikowana +
 - Kompozycja +
(w każdym przypadku: liczności 1-* lub
*-* oraz automatyczne tworzenie poł. zwrotnego)
   */
  public static void main(String[] args) throws Exception {

    if (new File("Ekstensja").isFile()) {
      try {
        FileInputStream fileInput = new FileInputStream("Ekstensja");
        ObjectInputStream streamInput = new ObjectInputStream(fileInput);
        //odczyt z "daneFirmowe"
        ObjectPlus.readEkstansja(streamInput);
        streamInput.close();
        fileInput.close();
      } catch (IOException i) {
        i.printStackTrace();
        return;
      } catch (ClassNotFoundException c) {
        System.out.println("Nie znaleziono klasy.");
        c.printStackTrace();
        return;
      }
    }

    Driver driver = new Driver("Kuba");
    Driver driver1 = new Driver("Piotr");
    Driver driver2 = new Driver("Pawel");
    Driver driver3 = new Driver("Janusz");

    Car car = new Car("WSF1234");
    Car car1 = new Car("POI0937");
    Car car2 = new Car("KLS1233");

    CarDriver carDriver = new CarDriver(LocalDate.now(), LocalDate.now().plusMonths(2));
    CarDriver carDriver1 = new CarDriver(LocalDate.now(), LocalDate.now().plusMonths(3));
    CarDriver carDriver2 = new CarDriver(LocalDate.now(), LocalDate.now().plusMonths(1));

    Race race = new Race("Wyscig o pietruszke");
    Race race1 = new Race("Eliminacje");

    Team team = new Team("Poczatkujacy");
    Team team1 = new Team("SrednioZaawansowani");
    Team team2 = new Team("Zaawansowani");

    driver.addLink("Zajmuje","Kierowca", carDriver); //Asocjacja z zatrybutem
    car.addLink("Jest zajmowany", "Samochod", carDriver);

    driver2.addLink("Zajmuje", "Kierowca" , carDriver1); //Asocjacja z zatrybutem
    car2.addLink("Jest zajmowany", "Samochod", carDriver1);

    race.addLink("Kierowcy", "Wyscig", driver);  //Zwykła
    race.addLink("Kierowcy", "Wyscig", driver1, "P"); // Kwalifikowana

    race1.addLink("Kierowcy", "Wyscig", driver);
    race1.addLink("Kierowcy", "Wyscig", driver1);
    race1.addLink("Kierowcy", "Wyscig", driver2);

    System.out.println("-----");
    race1.showLinks("Kierowcy", System.out); //powiazania zwrotne
    System.out.println("-----");
    driver.showLinks("Wyscig", System.out);

    team.addPart("czesc", "calosc", driver); //Kompozycja
    team.addPart("czesc", "calosc", driver1);

  //  team.addPart("czesc", "calosc", driver); // wyjatek poniewaz dodawana czesc (Kierowca) nalezy do innego team'u

    System.out.println("-----");
    System.out.println(race.getLinkedObject("Kierowcy", "P")); //Kwalifikowana

    System.out.println("-----");

    carDriver.showLinks("Kierowca", System.out); //Relacje w asocjacji z atrybutem.
    carDriver.showLinks("Samochod", System.out);

    System.out.println("-----");
    carDriver1.showLinks("Kierowca", System.out);
    carDriver1.showLinks("Samochod", System.out);






    try {
      // do pliku "DaneOfertSprzetuUslug"
      FileOutputStream fileOutput = new FileOutputStream("Ekstensja");
      ObjectOutputStream StreamOutput = new ObjectOutputStream(fileOutput);
      ObjectPlus.saveEkstansja(StreamOutput);
      StreamOutput.close();
      fileOutput.close();
    } catch (IOException i) {
      i.printStackTrace();
    }

  }
}
