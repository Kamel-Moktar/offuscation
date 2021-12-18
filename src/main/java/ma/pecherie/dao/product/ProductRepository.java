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
    public Product findProductById(Long id);
    public Product findProductByName(String name);





}
