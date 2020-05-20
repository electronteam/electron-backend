package md.electron.electronbackend.persistence.repositories;

import md.electron.electronbackend.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    Product getProductByCode(String code);

    @Query("SELECT p.imageURL FROM Product p WHERE p.code = :code")
    String getProductImageURL(@Param("code") String code);
}
