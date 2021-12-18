package ma.pecherie.dao.provisionproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;

@RepositoryRestResource
public interface ProvisionProductRepository extends JpaRepository<ProvisionProduct,Long> {

}
