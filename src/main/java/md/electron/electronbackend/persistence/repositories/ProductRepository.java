package md.electron.electronbackend.persistence.repositories;

import md.electron.electronbackend.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
}
