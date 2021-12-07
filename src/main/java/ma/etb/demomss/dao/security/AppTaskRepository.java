package ma.etb.demomss.dao.security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AppTaskRepository extends JpaRepository<AppTask,Long> {

}
