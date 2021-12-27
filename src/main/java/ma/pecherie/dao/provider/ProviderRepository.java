package ma.pecherie.dao.provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProviderRepository extends JpaRepository<Provider,Long> {
   // @Query("select new Provider( id,name,telephoneNumber) from Provider where id=:provider_id ")
    Provider findProviderById(long l);
}
