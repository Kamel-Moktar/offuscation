package ma.pecherie.dao.saleproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.pecherie.dao.product.Product;
import ma.pecherie.dao.sale.Sale;


import javax.persistence.*;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class SaleProduct {

    @Id @GeneratedValue
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    private Double requestQuantity;

}