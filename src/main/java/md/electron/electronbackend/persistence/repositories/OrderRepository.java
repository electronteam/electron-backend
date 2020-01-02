package md.electron.electronbackend.persistence.repositories;

import md.electron.electronbackend.persistence.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{
}