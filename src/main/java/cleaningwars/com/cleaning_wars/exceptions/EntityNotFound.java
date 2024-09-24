package cleaningwars.com.cleaning_wars.exceptions;


public class EntityNotFound extends RuntimeException{
    
    public EntityNotFound(Long id, Class<?> entity){
        super("The " + entity.getSimpleName().toLowerCase() + "with id "+id+" doesn't exist");
    }
}
