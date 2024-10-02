package cleaningwars.com.cleaning_wars.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invitation {

    public enum InvitationStatus {
        PENDING,
        ACCEPTED,
        DECLINED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invited_user_id", referencedColumnName = "id", nullable = false)
    private User invitedUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Home home;

    private InvitationStatus status = InvitationStatus.PENDING;

}