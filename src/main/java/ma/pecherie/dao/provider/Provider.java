package ma.pecherie.dao.provider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private long id;
    @Column(name = "provider_name",unique = true)
    private String name;
    @Column(name = "provider_tel")
    private String telephoneNumber;

}
