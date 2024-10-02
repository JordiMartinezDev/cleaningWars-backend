package cleaningwars.com.cleaning_wars.services.implementations;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.Invitation;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.entities.Invitation.InvitationStatus;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import cleaningwars.com.cleaning_wars.repositories.InvitationRepository;
import cleaningwars.com.cleaning_wars.repositories.UserRepository;
import cleaningwars.com.cleaning_wars.services.interfaces.InvitationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImplementation implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Invitation inviteUserToHome(Long homeId, Long userId) {

        Home home = homeRepository.findById(homeId).orElseThrow(() -> new RuntimeException("Home not found"));
        User invitedUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Invitation invitation = new Invitation();
        invitation.setHome(home);
        invitation.setInvitedUser(invitedUser);
        invitation.setStatus(InvitationStatus.PENDING);

        return invitationRepository.save(invitation);
    }

    @Override
    public void acceptInvitation(Long invitationId, Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acceptInvitation'");
    }

    @Override
    public void declineInvitation(Long invitationId, Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'declineInvitation'");
    }

    @Override
    public List<Invitation> getInvitationsbyUserId(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInvitationsForUser'");
    }

}