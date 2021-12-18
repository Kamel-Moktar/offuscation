package ma.pecherie.dao.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.pecherie.dao.customer.Customer;
import ma.pecherie.dao.product.Product;
import ma.pecherie.dao.saleproduct.SaleProduct;
import ma.pecherie.dao.security.AppUser;

import javax.persistence.*;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Date date;

    @ManyToOne
    private Customer customer;
    @ManyToOne
    private AppUser user;

    @OneToMany(mappedBy = "sale")
    private List<SaleProduct> saleProducts=new ArrayList<SaleProduct>();
}
