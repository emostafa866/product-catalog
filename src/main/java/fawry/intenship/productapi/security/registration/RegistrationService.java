package fawry.intenship.productapi.security.registration;

import fawry.intenship.productapi.security.appUser.AppUser;
import fawry.intenship.productapi.security.appUser.AppUserService;
import net.bytebuddy.matcher.ElementMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private AppUserService appUserService;

    public String register(RegistrationRequest request) {

            boolean isValid=emailValidator.test(request.getEmail());
            if(!isValid){
                throw new IllegalStateException("email is not valid");
            }
        return appUserService.SignUp(new AppUser(
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPassword()));
    }
}

