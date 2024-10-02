package cleaningwars.com.cleaning_wars.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users") // users is a reserved keyword in SQL. Therefore, we can't use it as a table
                       // name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is blank")
    @NonNull
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Email is blank")
    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    // Password should be hashed using BCrypt
    @Column(nullable = false)
    @NotBlank(message = "Password is blank")
    @NonNull
    private String password;

    @ManyToOne(cascade = CascadeType.PERSIST) // Cascade persist operation to save Home automatically
    @JoinColumn(name = "home_id", referencedColumnName = "id")
    @JsonIgnore
    private Home home;

    @Column(nullable = true)
    @OneToMany(mappedBy = "invitedUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Invitation> invitations;

}
