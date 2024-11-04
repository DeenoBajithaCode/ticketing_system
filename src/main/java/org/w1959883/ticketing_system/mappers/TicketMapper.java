package org.w1959883.ticketing_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.w1959883.ticketing_system.dtos.TicketDto;
import org.w1959883.ticketing_system.models.Ticket;

@Mapper
public interface TicketMapper
{
    TicketMapper INSTANCE = Mappers.getMapper( TicketMapper.class );

    @Mapping( target = "vendorId", ignore = true )
    Ticket toTicket( TicketDto vendorDto );

    TicketDto toTicketDTO( Ticket vendor );
}
