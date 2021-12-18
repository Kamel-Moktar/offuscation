package ma.pecherie.dao.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
     @Column(unique = true)
    private String userName;
    String password;
    @ManyToMany(fetch=FetchType.EAGER)
    Collection<AppRole> roles=new ArrayList<AppRole>();
}