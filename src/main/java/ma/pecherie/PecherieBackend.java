package ma.pecherie;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.pecherie.dao.product.Product;
import ma.pecherie.dao.product.ProductRepository;
import ma.pecherie.dao.security.AppRole;
import ma.pecherie.dao.security.AppTask;
import ma.pecherie.dao.security.AppTaskRepository;
import ma.pecherie.dao.security.AppUser;
import ma.pecherie.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.stream.Stream;

@SpringBootApplication
public class PecherieBackend {

    public static void main(String[] args) {
        SpringApplication.run(PecherieBackend.class, args);
    }


     @Bean
     BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
     }
     @Bean
     ObjectMapper getObjectMapper(){
        return new ObjectMapper();
     }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        //ObjectMapper objectMapper = getObjectMapper();
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return bean;
    }

     @Bean
    CommandLineRunner start(ProductRepository produitRepository
    , AppTaskRepository taskRepository , AccountService accountService) {
        return args -> {
           // repositoryRestConfiguration.exposeIdsFor(Produit.class);
            produitRepository.save(new Product(null, "Tacaud", 0));
            produitRepository.save(new Product(null, "Divers raies", 0));
            produitRepository.save(new Product(null, "Petite rousette", 0));
            produitRepository.save(new Product(null, "Congre", 0));
            produitRepository.save(new Product(null, "Sole", 0));
            produitRepository.save(new Product(null, "Calmar", 0));
            produitRepository.save(new Product(null, "Eglefin", 0));
            produitRepository.save(new Product(null, "Germon", 0));
            produitRepository.save(new Product(null, "Lieu noir", 0));
            produitRepository.save(new Product(null, "Buccin", 0));
            produitRepository.save(new Product(null, "Maquereau", 0));
            produitRepository.save(new Product(null, "Merlan", 0));
            produitRepository.save(new Product(null, "Seiche", 0));
            produitRepository.save(new Product(null, "Baudroie", 0));
            produitRepository.save(new Product(null, "Merlu", 0));
            produitRepository.save(new Product(null, "Sardine", 0));
            produitRepository.save(new Product(null, "Coquille St-Jacques", 0));
            produitRepository.save(new Product(null, "Emissole", 0));
            produitRepository.save(new Product(null, "Grondin rouge", 0));
            produitRepository.save(new Product(null, "Cardine", 0));
            produitRepository.save(new Product(null, "Rouget barbe", 0));

            accountService.savUser(new AppUser(null,"admin","1111",null));
            accountService.savUser(new AppUser(null,"root","1234",null));
            accountService.savUser(new AppUser(null,"user","1111",null));

            accountService.saveRole(new AppRole(null ,"ADMINISTRATEUR"));
            accountService.saveRole(new AppRole(null ,"VENDEUR"));
            accountService.saveRole(new AppRole(null ,"ACHTEUR"));


            accountService.addRoleToUser("admin","ADMINISTRATEUR");
            accountService.addRoleToUser("user","VENDEUR");
            accountService.addRoleToUser("root","ACHTEUR");

            Stream.of("T1","T2","T3","T4").forEach(tas->{taskRepository.save(new AppTask(null,tas));});

            accountService.findAllUser().forEach(System.out::println);
            accountService.findAllRole().forEach(System.out::println);
        };

    }


}


