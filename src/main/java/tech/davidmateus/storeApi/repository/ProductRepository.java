package tech.davidmateus.storeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.davidmateus.storeApi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
