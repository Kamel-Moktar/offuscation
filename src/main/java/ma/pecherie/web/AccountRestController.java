package ma.pecherie.web;


import ma.pecherie.dao.security.AppRole;
import ma.pecherie.dao.security.user.AppUser;
import ma.pecherie.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

/*
* Le controleur de API REST de utilisateur qui correspond au collaborateur en frontend
*  */
@RestController
public class AccountRestController {
    @Autowired
    AccountService accountService;
    /*
    * pour la modification de collaborateur
    * */
    @PutMapping(value="ma.pecherie/updateUser")
    public AppUser updateUser(@RequestBody  AppUser user){
        return accountService.updateUser(user);
    }

    /*
    Pour le changement de mot de passe
     */
    @PutMapping(value="ma.pecherie/update/{id}")
    public AppUser update(@RequestBody  String psw,@PathVariable Long id){
        return accountService.updatePassWord(psw,id);
    }

    /*
    * pour l'ajour d'un collaborateur
    * */
    @PostMapping("ma.pecherie/addUser")
    public AppUser register(@RequestBody  AppUser user){
       //AppUser appUser=accountService.findUserByName(user.getUserName());
       //if(!(appUser==null)) throw new RuntimeException("User name already exist !");
        ArrayList<AppRole> userRoles=new ArrayList<>(user.getRoles());
        AppUser appUser=accountService.savUser(new AppUser(
                null,
                user.getUserName(),
                user.getPassword(),
                new ArrayList<AppRole>())
        );
        userRoles.forEach(r-> accountService.addRoleToUser(appUser.getUserName(),r.getRoleName()));

        return accountService.findUserByName(appUser.getUserName());

    }

    /*
    * por la recuperation de collaborateur
    * */
   @GetMapping("ma.pecherie/getAllUsers")
    public Collection<AppUser> getAllUser(){
        return accountService.findAllUser();
    }

    /*
    * por la supprission de utilisateur
    * */
    @DeleteMapping(value="ma.pecherie/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        AppUser appUser=accountService.findUserById(id);
        accountService.deleteUser(appUser);
    }

   /*
   pour la recuperation de rolles
    */
    @GetMapping("ma.pecherie/getroles")
    public Collection<AppRole> getAllRoles(){
        return accountService.findAllRole();
    }
    /*
       pour la recuperation de rolles
        */
    @GetMapping("ma.pecherie/getUserByName/{name}")
    public AppUser getUserByName(@PathVariable String name){
        return accountService.findUserByName(name);
    }


    @GetMapping("ma.pecherie/getCurrentUser")
    public AppUser getCurrentUser(){
        return accountService.getCurrentUser();
    }
}
