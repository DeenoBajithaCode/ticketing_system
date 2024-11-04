package org.w1959883.ticketing_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.w1959883.ticketing_system.models.Customer;
import org.w1959883.ticketing_system.models.Vendor;

public interface CustomerRepository extends JpaRepository<Customer,Integer>
{
}
