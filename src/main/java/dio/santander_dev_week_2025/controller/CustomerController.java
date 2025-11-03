package dio.santander_dev_week_2025.controller;

import dio.santander_dev_week_2025.domain.model.Customer;
import dio.santander_dev_week_2025.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping ("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get a user by ID", description = "Retrieve a specific user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<Customer> FindByid(@PathVariable Long id){
        var customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Get a user by CPF", description = "Retrieve a specific user based on its CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<Customer> FindByCpf(@PathVariable String cpf){
        var customer = customerService.findByCpf(cpf);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get a user by name", description = "Retrieve a specific user based on its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<Customer> FindByName(@PathVariable String name){
        var customer = customerService.findByName(name);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<Iterable<Customer>> FindAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @PutMapping ("/id/{id}")
    @Operation(summary = "Update a user by ID", description = "Update the data of an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<Customer> UpdateCustomerById(@PathVariable Long id, @RequestBody Customer customer){
        var customerToUpdate = customerService.updateById(id, customer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping ("/cpf/{cpf}")
    @Operation(summary = "Update a user by CPF", description = "Update the data of an existing user based on its CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<Customer> UpdateCustomerByCpf(@PathVariable String cpf, @RequestBody Customer customer){
        var customerToUpdate = customerService.updateByCpf(cpf, customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping ("/id/{id}")
    @Operation(summary = "Delete a user by ID", description = "Delete an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<Void> DeleteCustomerById(@PathVariable Long id){
        customerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/cpf/{cpf}")
    @Operation(summary = "Delete a user by CPF", description = "Delete an existing user based on its CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<Void> DeleteCustomerByCpf(@PathVariable String cpf){
        customerService.deleteByCpf(cpf);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customerToAdd){
        var customerAdded = customerService.addCustomer(customerToAdd);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerAdded.getId())
                .toUri(); // cria a location com o path usando o id do novo cliente
        return ResponseEntity.created(location).body(customerAdded);
    }
}
