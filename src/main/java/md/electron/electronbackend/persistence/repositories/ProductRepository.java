package md.electron.electronbackend.persistence.repositories;

import md.electron.electronbackend.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{
}
