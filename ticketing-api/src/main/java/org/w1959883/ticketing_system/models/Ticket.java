package org.w1959883.ticketing_system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ticket extends com.w1959883.models.Ticket
{
    @Id
    private Long ticketId;
}
