package dio.santander_dev_week_2025.service.impl;

import dio.santander_dev_week_2025.domain.model.Address;
import dio.santander_dev_week_2025.domain.model.Customer;
import dio.santander_dev_week_2025.domain.repository.AddressRepository;
import dio.santander_dev_week_2025.domain.repository.CustomerRepository;
import dio.santander_dev_week_2025.service.CustomerService;
import dio.santander_dev_week_2025.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private AddressRepository addressRepository;

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsByAccountNumber(customer.getAccount().getNumber())) {
            throw new IllegalArgumentException("This account already exists.");
        }
        if (customerRepository.existsByCpf(customer.getCpf())){
            throw  new IllegalArgumentException("This customer already exists.");
        }

        return SaveCustomer(customer);
    }

    @Override
    public Customer findByCpf(String cpf) {
        if (customerRepository.findByCpf(cpf) == null){
            throw new NoSuchElementException("This customer does not exists.");
        }
        return customerRepository.findByCpf(cpf);
    }

    @Override
    public Customer findByName(String name) {
        if (customerRepository.findByName(name) == null){
            throw new NoSuchElementException("This customer does not exists.");
        }
        return customerRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        var customerToDelete = customerRepository.findById(id);
        if (customerToDelete.isEmpty()){
            throw new NoSuchElementException("This customer does not exists.");}
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateById(Long id, Customer customer) {
        Optional<Customer> customerToUpdate = customerRepository.findById(id);
        if (customerToUpdate.isEmpty()){
            throw new NoSuchElementException("This customer does not exists.");}
        if (id != customer.getId()){
            throw new IllegalArgumentException("ID must be equals");
        }
        if (!customerToUpdate.get().getCpf().equalsIgnoreCase(customer.getCpf())){
            throw new IllegalArgumentException("CPF must be equals");
        }
        // mantém a conta corrente original gerada na aplicação
        customer.getAccount().setNumber(customerToUpdate.get().getAccount().getNumber());
        // mantém a numero cartão original gerado na aplicação
        customer.getCard().setNumber(customerToUpdate.get().getCard().getNumber());
        return SaveCustomer(customer);
    }

    @Override
    public void deleteByCpf(String cpf) {
        var customerToDelete = customerRepository.findByCpf(cpf);
        if (customerToDelete == null){
            throw new NoSuchElementException("This customer does not exists.");}
        var id = customerToDelete.getId();
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateByCpf(String cpf, Customer customer) {
        var customerToUpdate = customerRepository.findByCpf(cpf);
        if (customerToUpdate == null){
            throw new NoSuchElementException("This customer does not exists.");}
        if (customerToUpdate.getId() != customer.getId()){
            throw new IllegalArgumentException("ID must be equals");
        }
        if(!customer.getCpf().equalsIgnoreCase(cpf)){
            throw new IllegalArgumentException("CPF must be equals");
        }
        // mantém a conta corrente original gerada na aplicação
        customer.getAccount().setNumber(customerToUpdate.getAccount().getNumber());
        // mantém a numero cartão original gerado na aplicação
        customer.getCard().setNumber(customerToUpdate.getCard().getNumber());
        return SaveCustomer(customer);
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }


    private Customer SaveCustomer(Customer customer) {
        String cep = customer.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = viaCepService.checkCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        customer.setAddress(address);
        customerRepository.save(customer);
        return customer;

    }

}
