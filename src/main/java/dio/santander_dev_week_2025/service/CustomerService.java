package dio.santander_dev_week_2025.service;

import dio.santander_dev_week_2025.domain.model.Customer;


public interface CustomerService {

    Customer findById (Long id);

    Customer addCustomer (Customer customer);

    Customer findByCpf (String cpf);

    Customer findByName (String name);

    void deleteById (Long id);

    Customer updateById(Long id, Customer customer);

    void deleteByCpf (String cpf);

    Customer updateByCpf (String cpf, Customer customer);

    Iterable<Customer> findAll();
}
