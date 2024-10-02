package cleaningwars.com.cleaning_wars.services.interfaces;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.User;

public interface HomeService {

    Home createHome(User user);

    void updateHomeName(Long homeId, String newName);

    Home getHomeById(Long id);

    void editHomeName(Home home, Long id);

    void removeUserFromHome(Long homeId, Long userId);

    void deleteHome(Long id);

    // ------ Invitations -------
    void inviteUserToHome(Long newHomeId, Long userId, String token);

    void acceptInvitation(Long invitationId);

    void declineInvitation(Long invitationId);
}
