package ma.etb.demomss.web;


import ma.etb.demomss.dao.security.AppUser;
import ma.etb.demomss.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {
    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterFormUser registerFormUser){
       if (!registerFormUser.password.equals(registerFormUser.rePassword))
           throw new RuntimeException("You must confirme yoour password");

       System.out.println("Avant");
//       AppUser appUser=accountService.findUserByName(registerFormUser.getUserName());
//        if(!(appUser==null))
//             throw new RuntimeException("User name already exist !");
        System.out.println("Après");

        AppUser appUser=accountService.savUser(new AppUser(null,registerFormUser.getUserName(),registerFormUser.getPassword(),null));
        accountService.addRoleToUser(appUser.getUserName(),"USER");
       return appUser;
    }


}
