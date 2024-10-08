package cleaningwars.com.cleaning_wars.controllers;

import cleaningwars.com.cleaning_wars.entities.Invitation;
import cleaningwars.com.cleaning_wars.services.interfaces.InvitationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/invitation")
public class InvitationController {

    @Autowired
    @Lazy
    InvitationService invitationService;

    @PostMapping("/invite/{homeId}/{userId}")
    public ResponseEntity<String> inviteUserToHome(@PathVariable Long homeId, @PathVariable Long userId) {
        invitationService.inviteUserToHome(homeId, userId);
        return new ResponseEntity<>("Invitatation sent", HttpStatus.ACCEPTED);

    }

    @PostMapping("/{invitationId}/accept")
    public ResponseEntity<String> acceptInvitation(@PathVariable Long invitationId, @RequestParam Long userId) {
        invitationService.acceptInvitation(invitationId, userId);
        return new ResponseEntity<>("Invitation accepted", HttpStatus.ACCEPTED);
    }

    @PostMapping("/{invitationId}/decline")
    public ResponseEntity<String> declineInvitation(@PathVariable Long invitationId, @RequestParam Long userId) {
        invitationService.declineInvitation(invitationId, userId);
        return new ResponseEntity<>("Invitation declined", HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Invitation>> getUserInvitations(@PathVariable Long userId) {
        return new ResponseEntity<>(invitationService.getInvitationsbyUserId(userId), HttpStatus.OK);
    }

    @GetMapping("{homeId}/getlist")
    public ResponseEntity<List<Invitation>> getMethodName(@PathVariable Long homeId) {

        return new ResponseEntity<>(invitationService.getInvitationsByHomeId(homeId), HttpStatus.OK);
    }

}
