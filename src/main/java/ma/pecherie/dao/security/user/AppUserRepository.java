
package ma.pecherie.dao.security.user;

        import org.springframework.data.jpa.repository.JpaRepository;

        import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    //@Query("select new AppUser(id,userName,password,roles) from AppUser where userName=:name")
    AppUser findByUserName(String name);
   // @Query("select new AppUser(id,userName,password,roles) from AppUser where id=:user_id")
    AppUser findAppUserById( Long user_id);

}