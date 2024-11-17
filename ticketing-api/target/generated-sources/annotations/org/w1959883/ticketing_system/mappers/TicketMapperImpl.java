package org.w1959883.ticketing_system.mappers;

import javax.annotation.processing.Generated;
import org.w1959883.ticketing_system.dtos.TicketDto;
import org.w1959883.ticketing_system.models.Ticket;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-17T15:34:22+0530",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class TicketMapperImpl implements TicketMapper {

    @Override
    public Ticket toTicket(TicketDto ticketDto) {
        if ( ticketDto == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        return ticket;
    }

    @Override
    public TicketDto toTicketDTO(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketDto ticketDto = new TicketDto();

        return ticketDto;
    }
}
