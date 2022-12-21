package fawry.intenship.productapi.security.appUser;

import fawry.intenship.productapi.security.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResourceController {


    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/role")
    public Role createRole(@RequestBody Role role){
     return appUserService.createRole(role);
    }

    @PostMapping("/role/addtouser")
     public void addRoleToUser(@RequestBody RoleToUserForm form ){

        appUserService.addRoleToUser(form);
     }

}

