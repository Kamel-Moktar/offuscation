package ma.pecherie.dao.saleproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SaleProductRepository extends JpaRepository<SaleProduct,Long> {

}
