package ma.pecherie.dao.security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppTaskRepository extends JpaRepository<AppTask,Long> {

}
