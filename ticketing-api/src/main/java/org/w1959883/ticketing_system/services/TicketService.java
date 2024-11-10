package org.w1959883.ticketing_system.services;

import org.springframework.stereotype.Service;
import org.w1959883.ticketing_system.dtos.TicketDto;
import org.w1959883.ticketing_system.mappers.TicketMapper;
import org.w1959883.ticketing_system.models.Ticket;
import org.w1959883.ticketing_system.repositories.TicketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService
{
    private final TicketRepository ticketRepository;

    private final TicketMapper ticketMapper = TicketMapper.INSTANCE;

    public TicketService( TicketRepository ticketRepository )
    {
        this.ticketRepository = ticketRepository;
    }

    public void addTicket( TicketDto ticketDto )
    {
        Ticket ticket = ticketMapper.toTicket( ticketDto );
        ticketRepository.save( ticket );
    }

    public List<Ticket> getAllTickets()
    {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById( Integer reservationId )
    {
        return ticketRepository.findById( reservationId );
    }

    public Ticket updateTicket( TicketDto ticketDTO )
    {
        Ticket ticket = ticketMapper.toTicket( ticketDTO );
        return ticketRepository.save( ticket );
    }

    public void deleteTicket( Integer ticketId )
    {
        ticketRepository.deleteById( ticketId );
    }

}
