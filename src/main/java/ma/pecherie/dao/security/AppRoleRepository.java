package ma.pecherie.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
  //@Query("select new AppRole (id,roleName) from AppRole where roleName=:name")
  AppRole findByRoleName( @Param("name") String  name );
}
