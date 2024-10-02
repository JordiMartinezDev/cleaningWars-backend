package cleaningwars.com.cleaning_wars.repositories;

import org.springframework.data.repository.CrudRepository;

import cleaningwars.com.cleaning_wars.entities.Invitation;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {

}
