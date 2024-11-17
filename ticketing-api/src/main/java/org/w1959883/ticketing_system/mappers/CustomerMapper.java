package org.w1959883.ticketing_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.w1959883.ticketing_system.dtos.CustomerDto;
import org.w1959883.ticketing_system.models.Customer;

@Mapper
public interface CustomerMapper
{
    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

//    @Mapping( target = "", ignore = true )
    Customer toCustomer( CustomerDto customerDto );

    CustomerDto toCustomerDTO( Customer customer );
}
