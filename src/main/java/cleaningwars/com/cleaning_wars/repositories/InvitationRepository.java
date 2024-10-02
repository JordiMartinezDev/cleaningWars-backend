package cleaningwars.com.cleaning_wars.repositories;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.Invitation;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {

    List<Invitation> findByInvitedUserId(Long userId);

    List<Invitation> findByHome(Home home);
}
