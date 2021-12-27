package ma.pecherie.dao.provision;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.pecherie.dao.provider.Provider;
import ma.pecherie.dao.provisionproduct.ProvisionProduct;
import ma.pecherie.dao.security.user.AppUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name="provision")
public class Provision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provision_id")
    private Long id;
    @Column(name = "provision_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="provider_id")
    public Provider provider;

    @ManyToOne
    @JoinColumn(name="user_id")
    public AppUser user;
    @OneToMany(mappedBy = "provision")
    public List<ProvisionProduct> provisionProducts=new ArrayList<ProvisionProduct>();



}
