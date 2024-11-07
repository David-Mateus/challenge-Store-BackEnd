package tech.davidmateus.storeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.davidmateus.storeApi.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
