package ma.etb.demomss.service;

import ma.etb.demomss.dao.security.AppRole;
import ma.etb.demomss.dao.security.AppRoleRepository;
import ma.etb.demomss.dao.security.AppUser;
import ma.etb.demomss.dao.security.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


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
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String   roleName) {
        AppUser user=userRepository.findByUserName(userName);
        AppRole role=roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByName(String userName) {
        AppUser user=userRepository.findByUserName(userName);
        if (user==null) throw new UsernameNotFoundException(userName);
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<AppUser> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<AppRole> findAllRole() {
        return roleRepository.findAll();
    }
}
