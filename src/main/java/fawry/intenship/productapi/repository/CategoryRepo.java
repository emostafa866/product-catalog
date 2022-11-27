package fawry.intenship.productapi.repository;

import fawry.intenship.productapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {


}
