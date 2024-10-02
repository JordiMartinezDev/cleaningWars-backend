package cleaningwars.com.cleaning_wars.services.implementations;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.Invitation;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.entities.Invitation.InvitationStatus;
import cleaningwars.com.cleaning_wars.exceptions.EntityNotFound;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import cleaningwars.com.cleaning_wars.repositories.InvitationRepository;
import cleaningwars.com.cleaning_wars.repositories.UserRepository;
import cleaningwars.com.cleaning_wars.services.interfaces.HomeService;
import cleaningwars.com.cleaning_wars.services.interfaces.InvitationService;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImplementation implements InvitationService {

    @Autowired
    @Lazy
    InvitationRepository invitationRepository;

    @Autowired
    @Lazy
    HomeRepository homeRepository;

    @Autowired
    @Lazy
    UserRepository userRepository;

    @Autowired
    @Lazy
    UserService userService;

    @Autowired
    @Lazy
    HomeService homeService;

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
    @Transactional
    public Invitation acceptInvitation(Long invitationId, Long userId) {

        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new EntityNotFound(invitationId, Invitation.class));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFound(userId, User.class));

        Home oldHome = user.getHome();

        // Adding the user to the new home
        homeService.addUser(invitation.getHome().getId(), user);

        // Remove user from the old home
        if (oldHome != null) {
            homeService.removeUserFromHome(oldHome.getId(), userId);

            if (oldHome.getUsers().isEmpty()) {

                // Delete the old home if there are no users left on it
                homeService.deleteHome(oldHome.getId());
            }
        }

        invitation.setStatus(InvitationStatus.ACCEPTED);
        return invitationRepository.save(invitation);
    }

    @Override
    public Invitation declineInvitation(Long invitationId, Long userId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new EntityNotFound(invitationId, Invitation.class));

        if (!invitation.getInvitedUser().getId().equals(userId)) {
            throw new IllegalArgumentException("This invitation does not belong to the user.");
        }

        invitation.setStatus(InvitationStatus.DECLINED);

        return invitationRepository.save(invitation);
    }

    @Override
    public List<Invitation> getInvitationsbyUserId(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInvitationsForUser'");
    }

    @Override
    public List<Invitation> getInvitationsByHomeId(Long homeId) {
        Home home = homeRepository.findById(homeId)
                .orElseThrow(() -> new RuntimeException("Home not found"));

        return invitationRepository.findByHome(home);
    }

}