package ma.pecherie.service;

import ma.pecherie.dao.security.AppRole;
import ma.pecherie.dao.security.user.AppUser;

import java.util.List;

public interface AccountService {
    public AppUser savUser(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String  user,String  role);
    public void deleteRoleFormUser(String userName, String   roleName);
    public AppUser findUserByName(String userName);
    public AppUser findUserById(Long id);
    public void deleteUser(AppUser user);
    public AppRole findRoleByName(String name);
    public List<AppUser> findAllUser();
    public List<AppRole> findAllRole();
    public AppUser updateUser(AppUser User);
    public AppUser updatePassWord(String psw,Long id);
    public String  getCurrentUserName();
    public AppUser  getCurrentUser();
}
