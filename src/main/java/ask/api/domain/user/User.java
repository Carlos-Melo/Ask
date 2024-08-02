package ask.api.domain.user;

import ask.api.domain.user.dto.UserCreate;
import ask.api.domain.user.dto.UserUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public  String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
