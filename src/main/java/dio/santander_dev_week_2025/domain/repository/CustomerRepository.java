package dio.santander_dev_week_2025.domain.repository;

import dio.santander_dev_week_2025.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {

}
