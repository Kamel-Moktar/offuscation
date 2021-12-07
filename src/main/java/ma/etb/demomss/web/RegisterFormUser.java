package ma.etb.demomss.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterFormUser {

    String userName;
    String password;
    String rePassword;

}
