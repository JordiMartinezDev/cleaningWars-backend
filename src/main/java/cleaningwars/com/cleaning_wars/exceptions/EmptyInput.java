package cleaningwars.com.cleaning_wars.exceptions;

public class EmptyInput extends RuntimeException{
    

    public EmptyInput(String inputField){
        super("The field :" + inputField + "is empty or missing");
    }

}
