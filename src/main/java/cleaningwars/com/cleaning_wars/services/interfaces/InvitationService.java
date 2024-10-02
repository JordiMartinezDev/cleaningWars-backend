package cleaningwars.com.cleaning_wars.services.interfaces;

import cleaningwars.com.cleaning_wars.entities.Invitation;

import java.util.List;

public interface InvitationService {

    Invitation inviteUserToHome(Long homeId, Long userId);

    Invitation acceptInvitation(Long invitationId, Long userId);

    Invitation declineInvitation(Long invitationId, Long userId);

    List<Invitation> getInvitationsbyUserId(Long userId);

    List<Invitation> getInvitationsByHomeId(Long homeId);

}
