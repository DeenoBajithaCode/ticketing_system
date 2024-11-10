package org.w1959883.ticketing_system.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w1959883.ticketing_system.dtos.CustomerDto;
import org.w1959883.ticketing_system.models.Customer;
import org.w1959883.ticketing_system.services.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/customer" )
public class CustomerController
{
    private final CustomerService customerService;

    public CustomerController( CustomerService customerService )
    {
        this.customerService = customerService;
    }

    @PostMapping( "/add" )
    private void addCustomer( CustomerDto customerDto )
    {
        customerService.addCustomer( customerDto );
    }

    @GetMapping
    public List<Customer> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Customer> getCustomerById( @PathVariable Integer id )
    {
        Optional<Customer> customer = customerService.getCustomerById( id );
        return customer.map( ResponseEntity::ok )
                       .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Customer> updateCustomer( @PathVariable Integer id, @RequestBody CustomerDto customerDTO )
    {
        Optional<Customer> customer = customerService.getCustomerById( id );
        if( customer.isPresent() )
        {
            return ResponseEntity.ok( customerService.updateCustomer( customerDTO ) );
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deleteCustomer( @PathVariable Integer id )
    {
        Optional<Customer> customer = customerService.getCustomerById( id );
        if( customer.isPresent() )
        {
            customerService.deleteCustomer( id );
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}
