package cleaningwars.com.cleaning_wars.entities;

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
    private Long invitedUserId;

    @ManyToOne
    @JoinColumn(name = "home_id", referencedColumnName = "id", nullable = false)
    private Home home;

    private InvitationStatus status = InvitationStatus.PENDING;

}