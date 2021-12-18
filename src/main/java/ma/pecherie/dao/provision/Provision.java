package ma.pecherie.dao.provision;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.pecherie.dao.product.Product;
import ma.pecherie.dao.provider.Provider;
import ma.pecherie.dao.provisionproduct.ProvisionProduct;
import ma.pecherie.dao.security.AppUser;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @ManyToOne
    public Provider provider;
    @ManyToOne
    public AppUser user;
    @OneToMany(mappedBy = "provision")
    public List<ProvisionProduct> provisionProducts=new ArrayList<ProvisionProduct>();



}
