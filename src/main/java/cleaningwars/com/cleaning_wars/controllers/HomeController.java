package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cleaningwars.com.cleaning_wars.services.interfaces.HomeService;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;

    @PostMapping("{homeId}/updatename")
    public ResponseEntity<String> updateHomeName(@PathVariable Long homeId, @RequestBody String newName) {

        homeService.updateHomeName(homeId, newName);
        return ResponseEntity.ok("Updated Successfully");
    }

    @PostMapping("/{newHomeId}/invite-user/{userId}")
    public ResponseEntity<String> inviteUserToHome(@PathVariable Long newHomeId, @PathVariable Long userId) {

        homeService.inviteUserToHome(newHomeId, userId);
        return ResponseEntity.ok("User has been invited to the new home.");
    }

    @PostMapping("/invitations/{invitationId}/accept")
    public ResponseEntity<String> acceptInvitation(@PathVariable Long invitationId) {

        homeService.acceptInvitation(invitationId);
        return ResponseEntity.ok("Invitation accepted and user has joined the new home.");
    }

    @PostMapping("/invitations/{invitationId}/decline")
    public ResponseEntity<String> declineInvitation(@PathVariable Long invitationId) {

        homeService.declineInvitation(invitationId);
        return ResponseEntity.ok("Invitation declined.");
    }

}