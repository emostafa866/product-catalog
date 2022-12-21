package fawry.intenship.productapi.repository;

import fawry.intenship.productapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    public List<Product> findByCategoryId(Long id);
    public List<Product> findByCategoryName(String categoryName);

    public Product findByNameEn(String name);


  /*  @Query("select count(product_id),\n" +
            "\n" +
            "product_id,\n" +
            "product.name_en,\n" +
            "product.name_ar,\n" +
            "product.price,\n" +
            "product.quantity,\n" +
            "product.image\n" +
            "from\n" +
            " order_product \n" +
            "join product on order_product.product_id =product.id\n" +
            " group by  \n" +
            " product_id,\n" +
            "product.name_en,\n" +
            "product.name_ar,\n" +
            "product.price,\n" +
            "product.quantity,\n" +
            "product.image\n" +
            " order by count(product_id) DESC")
        public List<Product> findByPopularity();*/

}
