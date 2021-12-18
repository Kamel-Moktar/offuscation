package ma.pecherie.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterFormUser {
    long id;
    String userName;
    String password;
    String[] roles;
}
