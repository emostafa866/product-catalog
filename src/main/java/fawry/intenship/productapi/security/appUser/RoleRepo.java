package fawry.intenship.productapi.security.appUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepo extends JpaRepository<Role,Long> {
     public Role findByName(String name);

}
