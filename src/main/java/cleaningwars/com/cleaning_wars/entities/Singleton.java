package cleaningwars.com.cleaning_wars.entities;

public class Singleton {
    

    private static final Singleton instance = new Singleton();

    private Singleton (){

    }

    public static Singleton getInstance(){

        return instance;
    }
}
