package com.w1959883.models;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Configuration
{
    private Integer totalNumberOfTickets;
    private Double ticketReleaseRate;
    private Double customerRetrievalRate;
    private Integer maximumTicketCapacity;

    public Configuration()
    {
    }

    public Configuration( Integer totalNumberOfTickets, Double ticketReleaseRate, Double customerRetrievalRate, Integer maximumTicketCapacity )
    {
        this.totalNumberOfTickets = totalNumberOfTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maximumTicketCapacity = maximumTicketCapacity;
    }

    public Integer getTotalNumberOfTickets()
    {
        return totalNumberOfTickets;
    }

    public void setTotalNumberOfTickets( Integer totalNumberOfTickets )
    {
        this.totalNumberOfTickets = totalNumberOfTickets;
    }

    public Double getTicketReleaseRate()
    {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate( Double ticketReleaseRate )
    {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public Double getCustomerRetrievalRate()
    {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate( Double customerRetrievalRate )
    {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public Integer getMaximumTicketCapacity()
    {
        return maximumTicketCapacity;
    }

    public void setMaximumTicketCapacity( Integer maximumTicketCapacity )
    {
        this.maximumTicketCapacity = maximumTicketCapacity;
    }
}
