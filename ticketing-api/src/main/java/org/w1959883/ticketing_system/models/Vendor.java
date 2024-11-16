package org.w1959883.ticketing_system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vendor
{
    @Id
    private Long vendorId;
}
