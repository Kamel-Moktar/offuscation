package ma.etb.demomss.service;
import ma.etb.demomss.dao.security.AppRole;
import ma.etb.demomss.dao.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        AppUser user=accountService.findUserByName(s);
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        Collection<AppRole> roles=user.getRoles();
        roles.forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
         User dtailUser = new User(user.getUserName(),user.getPassword(),authorities);
         System.out.println(dtailUser);
        return dtailUser;
    }
}
