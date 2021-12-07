package ma.etb.demomss.service;

import ma.etb.demomss.dao.security.AppRole;
import ma.etb.demomss.dao.security.AppUser;

import java.util.LinkedList;
import java.util.List;

public interface AccountService {
    public AppUser savUser(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String  user,String  role);
    public AppUser findUserByName(String userName);

   public List<AppUser> findAllUser();
   public List<AppRole> findAllRole();
}
