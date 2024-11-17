package com.w1959883.models;

public class Ticket
{
    private Long ticketId;
    private String ticketName;
    private Integer vendorId;
    private Integer customerId;

    public Ticket()
    {
    }

    public Long getTicketId()
    {
        return ticketId;
    }

    public void setTicketId( Long ticketId )
    {
        this.ticketId = ticketId;
    }

    public Integer getVendorId()
    {
        return vendorId;
    }

    public void setVendorId( Integer vendorId )
    {
        this.vendorId = vendorId;
    }

    public String getTicketName()
    {
        return ticketName;
    }

    public void setTicketName( String ticketName )
    {
        this.ticketName = ticketName;
    }

    public Integer getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( Integer customerId )
    {
        this.customerId = customerId;
    }
}
