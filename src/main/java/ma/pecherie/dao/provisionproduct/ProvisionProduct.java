package ma.pecherie.dao.provisionproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.pecherie.dao.product.Product;
import ma.pecherie.dao.provision.Provision;

import javax.persistence.*;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class ProvisionProduct {
    @Id @GeneratedValue
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "provision_id")
    private Provision provision;

    private Double requestQuantity;

}