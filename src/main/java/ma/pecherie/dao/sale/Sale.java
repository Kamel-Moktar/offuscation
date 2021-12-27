package ma.pecherie.dao.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.pecherie.dao.customer.Customer;
import ma.pecherie.dao.saleproduct.SaleProduct;
import ma.pecherie.dao.security.user.AppUser;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "sale_id")
    private Long id;
    @Column(name = "sale_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
    @OneToMany(mappedBy = "sale")
    private List<SaleProduct> saleProducts=new ArrayList<SaleProduct>();
}
