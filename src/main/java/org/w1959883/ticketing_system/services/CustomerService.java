package org.w1959883.ticketing_system.services;

import org.springframework.stereotype.Service;
import org.w1959883.ticketing_system.dtos.CustomerDto;
import org.w1959883.ticketing_system.mappers.CustomerMapper;
import org.w1959883.ticketing_system.models.Customer;
import org.w1959883.ticketing_system.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public CustomerService( CustomerRepository customerRepository )
    {
        this.customerRepository = customerRepository;
    }

    public void addCustomer( CustomerDto customerDto )
    {
        Customer customer = customerMapper.toCustomer( customerDto );
        customerRepository.save( customer );
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById( Integer reservationId )
    {
        return customerRepository.findById( reservationId );
    }


    public Customer updateCustomer( CustomerDto customerDTO )
    {
        Customer customer = customerMapper.toCustomer( customerDTO );
        return customerRepository.save( customer );
    }

    public void deleteCustomer( Integer customerId )
    {
        customerRepository.deleteById( customerId );
    }

}
