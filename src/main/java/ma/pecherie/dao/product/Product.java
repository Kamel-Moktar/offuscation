package ma.pecherie.dao.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.pecherie.dao.provisionproduct.ProvisionProduct;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "product_id")
    private Long id;
    @Column (name = "product_name", unique = true)
    private String name;
    @Column(name = "quantity")
    private double quantity;

}
