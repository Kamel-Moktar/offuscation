package ma.pecherie.dao.provision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProvisionRepository extends JpaRepository<Provision,Long> {
}
