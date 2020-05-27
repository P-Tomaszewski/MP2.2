import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class ObjectPlus implements Serializable
{
    private static final long serialVersionUID = 0071L;

    private static Map<Class, List<ObjectPlus>> ekstensje = new Hashtable<>();

    public ObjectPlus(){
        List<ObjectPlus> ekstensja = null;
        Class<? extends ObjectPlus> klasa = this.getClass();

        if(ekstensje.containsKey(klasa))
        {
            ekstensja = ekstensje.get(klasa);
        }
        else
        {
            ekstensja = new Vector<>();
            ekstensje.put(klasa, ekstensja);
        }
        ekstensja.add(this);
    }

    public static void saveEkstansja(ObjectOutputStream stream) throws IOException
    {
        stream.writeObject(ekstensje);
    }

    public static void readEkstansja(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        ekstensje = (Hashtable) stream.readObject();
    }


    public static <T> void deleteExtension(T name) {
        List<T> list = (List<T>) ekstensje.get(name.getClass());
        list.remove(name);
    }

    public static int extentSize(Class<? extends ObjectPlus> className) {
        int counter = 0;
        if (ekstensje.containsKey(className)) {
            counter = ekstensje.get(className).size();
        }
        System.out.println(" Extent " + className.getSimpleName() + " : " + counter);
        return counter;
    }

    public static void showEkstansja(Class klasa) throws Exception
    {
        List ekstensja = null;

        if(ekstensje.containsKey(klasa))
        {
            ekstensja = ekstensje.get(klasa);
        }
        else
        {
            throw new Exception("Unknown class " + klasa);
        }

        System.out.println("Ekstensja klasy: " + klasa.getSimpleName());

        for(Object obiekt : ekstensja)
        {
            System.out.println(obiekt);
        }
    }



    public static <T> List<T> getExtent(Class<T> type) throws ClassNotFoundException {
        if(ekstensje.containsKey(type)) {
            return (List<T>) ekstensje.get(type);
        }

        throw new ClassNotFoundException(String.format("%s. Stored extents: %s", type.toString(), ekstensje.keySet()));
    }
}