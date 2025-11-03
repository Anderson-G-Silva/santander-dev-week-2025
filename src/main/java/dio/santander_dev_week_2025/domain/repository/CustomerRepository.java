package dio.santander_dev_week_2025.domain.repository;

import dio.santander_dev_week_2025.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {

    Boolean existsByAccountNumber(String accountNumber);
    Boolean existsByCpf(String cpf);
    Customer findByCpf (String cpf);
    Customer findByName (String name);
    Customer deleteByCpf(String cpf);

}
