package ma.pecherie.dao.saleproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.pecherie.dao.product.Product;
import ma.pecherie.dao.sale.Sale;


import javax.persistence.*;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
//@Table(name = "sale_product")
public class SaleProduct {

    @Id @GeneratedValue
    @Column(name = "sale_product_id")
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
    @Column(name = "request_quantity")
    private Double requestQuantity;

}