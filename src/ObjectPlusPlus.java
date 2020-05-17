import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;


public class ObjectPlusPlus extends ObjectPlus implements Serializable {

   //informacje o wszystkich powiazaniach tego obiektu.
  protected Hashtable<String, HashMap<Object, ObjectPlusPlus>> links = new Hashtable<String, HashMap<Object, ObjectPlusPlus>>();

   //informacje o wszystkich czesciach powiazanych
  protected static HashSet<ObjectPlusPlus> allParts = new HashSet<ObjectPlusPlus>();

  public ObjectPlusPlus() {
    super();
  }

  private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
    HashMap<Object, ObjectPlusPlus> objectLink;

    // Zabezpieczenie dla tworzenia polaczenia zwrotnego
    if(counter < 1) {
      return;
    }

    // Znajdz kolekcje powiazan dla tej roli
    if(links.containsKey(roleName)) {
      // Pobierz te powiazania
      objectLink = links.get(roleName);
    }
    else {
      // Brak powiazan dla takiej roli ==> utworz
      objectLink = new HashMap<Object, ObjectPlusPlus>();
      links.put(roleName, objectLink);
    }

    // Sprawdz czy powiazanie juz istnieje?
    // Jezeli tak to zignoruj dodawanie
    if(!objectLink.containsKey(qualifier)) {
      // Dodaj powiazanie dla tego obiektu
      objectLink.put(qualifier, targetObject);

      // Dodaj powiazanie zwrotne
      targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
    }
  }


    //Tworzy nowe powiazanie do podanego obiektu (ewentualnie w ramach asocjacji kwalifikowanej).
  public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier) {
    addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
  }


   // Tworzy nowe powiazanie do podanego obiektu (jako zwykla asocjacje binarna. NIE jako kwalifikowana).
  public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
    addLink(roleName, reverseRoleName, targetObject, targetObject);
  }


   // Dodaje informacje o powiazaniu z czescia (jako kompozycja).
  public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws Exception {
    // Sprawdz czy ta czesc juz gdzies nie wystepuje
    if(allParts.contains(partObject)) {
      throw new Exception("Ta czesc jest juz powiazana z jakas caloscia!");
    }

    addLink(roleName, reverseRoleName, partObject);

    // Zapamietaj dodanie obiektu jako czesci
    allParts.add(partObject);
  }


   // Zwraca tablice zawierajaca docelowe obiekty dla podanej nazwy roli.
  public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
    HashMap<Object, ObjectPlusPlus> objectLinks;

    if(!links.containsKey(roleName)) {
      // Brak powiazan dla tej roli
      throw new Exception("Brak powiazan dla roli: " + roleName);
    }

    objectLinks = links.get(roleName);

    return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
  }


   // Informuje czy istnieja powiazania dla podanej nazwy roli
  public boolean areLinks(String roleName) {
    if(!links.containsKey(roleName)) {
      return false; //gdy brak nazwy roli lub gdy liczba powiazan dla tej roli jest 0.
    }

    HashMap<Object, ObjectPlusPlus> objectLinks = links.get(roleName);
    return objectLinks.size() > 0;
  }


   //Wyswietla powiazania (dla podanej nazwy roli) na podanym strumieniu.
  public void showLinks(String roleName, PrintStream stream) throws Exception {
    HashMap<Object, ObjectPlusPlus> objectLink;

    if(!links.containsKey(roleName)) {
      // Brak powiazan dla tej roli
      throw new Exception("No links for the role: " + roleName);
    }

    objectLink = links.get(roleName);

    Collection col = objectLink.values();

    stream.println(this.toString() + " (" + this.getClass().getSimpleName() + ") links for the '" + roleName + "' role:");

    for(Object obj : col) {
      stream.println("   " + obj);
    }
  }
    //Zwraca obiekt odnaleziony na podstawie kwalifikatora (dla podanej nazwy roli).
    //Dziala w oparciu o asocjacje kwalifikowana.

  public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
    HashMap<Object, ObjectPlusPlus> objectLinks;

    if(!links.containsKey(roleName)) {
      // Brak powiazan dla tej roli
      throw new Exception("Brak powiazan dla roli: " + roleName);
    }

    objectLinks = links.get(roleName);
    if(!objectLinks.containsKey(qualifier)) {
      // Brak powiazan dla tej roli
      throw new Exception("Brak powiazania dla kwalifikatora: " + qualifier);
    }
    return objectLinks.get(qualifier);
  }
}