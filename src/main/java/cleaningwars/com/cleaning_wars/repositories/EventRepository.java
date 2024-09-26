package cleaningwars.com.cleaning_wars.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cleaningwars.com.cleaning_wars.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByHomeId(Long homeId);
}
