package ask.api.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Base64;

@Table(name = "user")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private byte[] image;

    @Enumerated(EnumType.STRING)
    private UserType userType;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate criationDate;

    public User(UserCreate data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.isActive = false;
        this.userType = UserType.USER;
        this.criationDate = LocalDate.now();
    }

    public void updateUser(UserUpdate data) {
        if(data.name() != null) {
            this.name = data.name();
        }
        if(data.email() != null) {
            this.email = data.email();
        }
        if(data.password() != null) {
            this.password = data.password();
        }
        if(data.isActive() != null) {
            this.isActive = data.isActive();
        }
        if(data.userType() != null) {
            this.userType = data.userType();
        }
        if(data.image() != null) {
            this.image = Base64.getDecoder().decode(data.image());
        }
    }
}
