package org.w1959883.ticketing_system.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w1959883.ticketing_system.dtos.TicketDto;
import org.w1959883.ticketing_system.models.Ticket;
import org.w1959883.ticketing_system.services.TicketService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api" )
public class TicketController
{
    private final TicketService ticketService;

    public TicketController( TicketService ticketService )
    {
        this.ticketService = ticketService;
    }

    @PostMapping("/add")
    private void addTicket( TicketDto ticketDto ){
        ticketService.addTicket(ticketDto);
    }

    @GetMapping
    public List<Ticket> getAllTickets()
    {
        return ticketService.getAllTickets();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Ticket> getTicketById( @PathVariable Integer id )
    {
        Optional<Ticket> ticket = ticketService.getTicketById( id );
        return ticket.map( ResponseEntity::ok )
                          .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Ticket> updateTicket( @PathVariable Integer id, @RequestBody TicketDto ticketDTO )
    {
        Optional<Ticket> ticket = ticketService.getTicketById( id );
        if( ticket.isPresent() )
        {
            return ResponseEntity.ok( ticketService.updateTicket( ticketDTO ) );
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deleteTicket( @PathVariable Integer id )
    {
        Optional<Ticket> ticket = ticketService.getTicketById( id );
        if( ticket.isPresent() )
        {
            ticketService.deleteTicket( id );
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
