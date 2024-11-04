package org.w1959883.ticketing_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.w1959883.ticketing_system.models.Ticket;
import org.w1959883.ticketing_system.models.Vendor;

public interface TicketRepository extends JpaRepository<Ticket,Integer>
{
}
