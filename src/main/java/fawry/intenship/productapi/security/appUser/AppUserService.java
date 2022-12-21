package fawry.intenship.productapi.security.appUser;// package fawry.intenship.productapi.security.appUser;

import fawry.intenship.productapi.errors.RecordNotFoundException;
import fawry.intenship.productapi.security.registration.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepo roleRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user= appUserRepo.findByEmail(email)
                .orElseThrow(()->new RecordNotFoundException("user not found"));

        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        user.getRole().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new User(user.getUsername(), user.getPassword(),authorities);

    }

    public String SignUp(AppUser user){

        boolean isExist= appUserRepo.findByEmail(user.getEmail()).isPresent();

        if(isExist){
            throw new IllegalStateException("USER ALREADY EXIST");
        }

        String encodePass=bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePass);
        user.setRole(new ArrayList<>());
        user.getRole().add(roleRepo.findByName("ROLE_USER"));
        appUserRepo.save(user);

        return "user created";
    }


    public Role createRole(Role role){

         return roleRepo.save(role);
    }


    public void addRoleToUser(RoleToUserForm form){

        AppUser user = appUserRepo.findByEmail(form.getUserName()).orElseThrow(null);
        Role role = roleRepo.findByName(form.getRoleName());
       user.getRole().add(role);
        appUserRepo.save(user);
    }
}
