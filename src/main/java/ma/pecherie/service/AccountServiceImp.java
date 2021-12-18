package ma.pecherie.service;

import ma.pecherie.dao.security.AppRole;
import ma.pecherie.dao.security.AppRoleRepository;
import ma.pecherie.dao.security.AppUser;
import ma.pecherie.dao.security.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/*
le service de la gestion de utilisateurs / collaborateurs
 */

@Service
@Transactional
public class AccountServiceImp implements AccountService {
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    AppUserRepository userRepository;
    @Autowired
    AppRoleRepository roleRepository;

    @Override
    public AppUser savUser(AppUser user) {
        String PSW=encoder.encode(user.getPassword());
        user.setPassword(PSW);
        AppUser newUser= userRepository.save(user);

        return newUser;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String   roleName) {

        AppUser user=userRepository.findByUserName(userName);
        AppRole role=roleRepository.findByRoleName(roleName);
        if(user!=null && role!=null) user.getRoles().add(role);
        else System.out.println("Proclemos");
    }
    @Override
    public void deleteRoleFormUser(String userName, String   roleName) {

        AppUser user=userRepository.findByUserName(userName);
        AppRole role=roleRepository.findByRoleName(roleName);
        if(user!=null && role!=null) user.getRoles().remove(role);
        else System.out.println("Proclemos");
    }
    @Override
    public AppUser findUserByName(String userName) {
        AppUser user=userRepository.findByUserName(userName);
        if (user==null) throw new UsernameNotFoundException(userName);
        return userRepository.findByUserName(userName);
    }

    @Override
    public AppUser findUserById(Long id) {
        AppUser user=userRepository.findAppUserById(id);
        return user;
    }
    @Override
    public AppRole findRoleByName(String name){
        return roleRepository.findByRoleName(name);
    }

    @Override
    public void deleteUser(AppUser user) {
        this.userRepository.delete(user);
    }

    @Override
    public List<AppUser> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<AppRole> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public AppUser updateUser(AppUser user) {
        AppUser appUser = userRepository.findAppUserById(user.getId());
        /*MAJ du non du l'utilisateur */
        appUser.setUserName(user.getUserName());

        ArrayList<AppRole> roles=new ArrayList<AppRole>(appUser.getRoles());

        for(AppRole r:roles){ deleteRoleFormUser(appUser.getUserName(),r.getRoleName());};

        if (user.getRoles() != null)
            for(AppRole r:user.getRoles()){ addRoleToUser(appUser.getUserName(),r.getRoleName());};

        return appUser;

    }

    @Override
    public AppUser updatePassWord(String psw,Long id) {
        AppUser appUser=userRepository.findAppUserById(id);
        String password=encoder.encode(psw);
        appUser.setPassword(password);
        return appUser;
    }

    public String  getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  authentication.getName();
    }

    public AppUser getCurrentUser(){
        return userRepository.findByUserName(getCurrentUserName());
    }

}
