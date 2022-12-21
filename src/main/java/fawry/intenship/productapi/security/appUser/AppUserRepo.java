package fawry.intenship.productapi.security.appUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    public Optional<AppUser> findByEmail(String email);
}
