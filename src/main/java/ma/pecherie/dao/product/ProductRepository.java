package ma.pecherie.dao.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {



    @Query("select new Product( id,name,quantity) from Product order by name asc ")
    ArrayList<Product> finAllOrderByName();

    @Query("select new Product( id,name,quantity) from Product  where quantity>0 order by name asc ")
    ArrayList<Product> finAllInStockOrderByName();

    //@Query("select new Product( id,name,quantity) from Product  where id=:product_id ")
    public Product findProductById(Long product_id);

   //// @Query("select new Product( id,name,quantity) from Product  where name=:product_name ")
    public Product findProductByName(String product_name);





}
