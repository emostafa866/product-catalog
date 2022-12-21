package fawry.intenship.productapi.repository;

import fawry.intenship.productapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    public Category findByName(String categoryName);
}
