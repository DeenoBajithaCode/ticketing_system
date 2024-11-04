package org.w1959883.ticketing_system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket
{
    @Id
    private Long ticketId;
    private String ticketName;
    private Integer vendorId;
    private Integer customerId;
    private Long price;
}
