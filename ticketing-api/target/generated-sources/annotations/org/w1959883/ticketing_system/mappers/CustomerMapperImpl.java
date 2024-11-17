package org.w1959883.ticketing_system.mappers;

import javax.annotation.processing.Generated;
import org.w1959883.ticketing_system.dtos.CustomerDto;
import org.w1959883.ticketing_system.models.Customer;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-17T15:34:22+0530",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        return customer;
    }

    @Override
    public CustomerDto toCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        return customerDto;
    }
}
