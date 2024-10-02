package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.services.interfaces.HomeService;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    @Lazy
    HomeService homeService;
    @Autowired
    @Lazy
    UserService userService;

    @GetMapping("/{homeId}")
    public ResponseEntity<Home> getMethodName(@PathVariable Long homeId) {
        return new ResponseEntity<>(homeService.getHomeById(homeId), HttpStatus.OK);
    }

    @PostMapping("/{homeId}/updatename")
    public ResponseEntity<String> updateHomeName(@PathVariable Long homeId, @RequestBody String newName) {

        homeService.updateHomeName(homeId, newName);
        return ResponseEntity.ok("Updated Successfully");
    }

    public ResponseEntity<String> inviteUserToHome(@PathVariable Long newHomeId, @PathVariable Long userId,
            @RequestHeader("Authorization") String token) {
        homeService.inviteUserToHome(newHomeId, userId, token);
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