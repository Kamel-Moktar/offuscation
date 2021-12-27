package ma.pecherie.dao.security.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.pecherie.dao.security.AppRole;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import static javax.persistence.GenerationType.IDENTITY;

@Entity

@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name",unique = true)
    private String userName;
    @Column(name = "password")
    String password;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Collection<AppRole> roles=new ArrayList<AppRole>();

}