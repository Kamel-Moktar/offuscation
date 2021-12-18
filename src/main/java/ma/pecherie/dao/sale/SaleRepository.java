package ma.pecherie.dao.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SaleRepository extends JpaRepository<Sale,Long> {

}
