package ma.etb.demomss.cofig_sec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {



        String jwt=httpServletRequest.getHeader(SecurityConstants.HEADER_STRING);
        System.out.println("JWT :"+jwt);
        if(httpServletRequest.getMethod().equals("OPTIONS")){
          httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }else{


        if(jwt==null|| !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)){
            filterChain.doFilter(httpServletRequest,httpServletResponse); return;
        }


        System.out.println(jwt);
        Claims claims= Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
                .getBody();
        String userName=claims.getSubject();

        ArrayList<Map<String,String>> roles= (ArrayList<Map<String,String>>)claims.get(SecurityConstants.ROLES_CLAIMS);

        ArrayList<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        roles.forEach(r->
            grantedAuthorities.add(new SimpleGrantedAuthority(r.get("authority")))


        );

        UsernamePasswordAuthenticationToken authenticatedUser=new UsernamePasswordAuthenticationToken(userName,null,grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        filterChain.doFilter(httpServletRequest,httpServletResponse);


    }





    }
}
