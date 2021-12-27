package ma.pecherie.dao.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer,Long>{
    /// @Query("select new Customer( id,name,telephoneNumber) from Customer where id=:provider_id ")
     Customer findCustomerById(long provider_id);


}
