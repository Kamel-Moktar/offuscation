package ma.etb.demomss;


import com.fasterxml.jackson.databind.ObjectMapper;
import ma.etb.demomss.dao.product.Produit;
import ma.etb.demomss.dao.product.ProduitRepository;
import ma.etb.demomss.dao.security.AppRole;
import ma.etb.demomss.dao.security.AppTask;
import ma.etb.demomss.dao.security.AppTaskRepository;
import ma.etb.demomss.dao.security.AppUser;
import ma.etb.demomss.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoMssApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMssApplication.class, args);
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
        config.addAllowedOrigin("http://localhost");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }

     @Bean
    CommandLineRunner start(ProduitRepository produitRepository
    , AppTaskRepository taskRepository , AccountService accountService) {
        return args -> {
           // repositoryRestConfiguration.exposeIdsFor(Produit.class);
            produitRepository.save(new Produit(null, "huille d'olive", 700, 500,true,true));
            produitRepository.save(new Produit(null, "Pain ", 50, 1000,true,true));
            produitRepository.save(new Produit(null, "tomate conserve ", 150, 200,true,true));
            produitRepository.save(new Produit(null, "gataux bimi", 70, 500,true,true));
            produitRepository.save(new Produit(null, "huille ", 500, 700,true,true));
            produitRepository.save(new Produit(null, "olive vert", 800, 900,true,true));
            produitRepository.save(new Produit(null, "beure", 400, 20,true,true));
            produitRepository.save(new Produit(null, "farine", 50, 500,true,true));

            accountService.savUser(new AppUser(null,"admin","1111",null));
            accountService.savUser(new AppUser(null,"root","1234",null));
            accountService.savUser(new AppUser(null,"user","1111",null));

            accountService.saveRole(new AppRole(null ,"ADMIN"));
            accountService.saveRole(new AppRole(null ,"ROOT"));
            accountService.saveRole(new AppRole(null ,"USER"));
            accountService.saveRole(new AppRole(null ,"INVITE"));

            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("user","USER");




            Stream.of("T1","T2","T3","T4").forEach(tas->{taskRepository.save(new AppTask(null,tas));});

            accountService.findAllUser().forEach(System.out::println);
            accountService.findAllRole().forEach(System.out::println);
        };

    }


}


