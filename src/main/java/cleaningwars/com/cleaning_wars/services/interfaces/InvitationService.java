package cleaningwars.com.cleaning_wars.services.interfaces;

import cleaningwars.com.cleaning_wars.entities.Invitation;

import java.util.List;

public interface InvitationService {

    Invitation inviteUserToHome(Long homeId, Long userId);

    void acceptInvitation(Long invitationId, Long userId);

    void declineInvitation(Long invitationId, Long userId);

    List<Invitation> getInvitationsForUser(Long userId);
}
