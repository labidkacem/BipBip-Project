
package BipBip_Project.Service;



import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import BipBip_Project.Model.User;
import BipBip_Project.Repository.UserRepository;

@Component
public class UserService implements UserDetailsService{

    @Inject
    UserRepository userRepository;


    public final PasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = userRepository.findByEmail(username);
        if (user != null) {
        
        //System.out.println("test print");
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities); }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void setComputingDerivedPassword(User u, String rawPassword) {
        String codedPassword = encoder.encode(rawPassword);
        u.setPassword(codedPassword);
    }


}
