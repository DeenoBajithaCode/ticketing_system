package org.w1959883.ticketing_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.w1959883.ticketing_system.dtos.VendorDto;
import org.w1959883.ticketing_system.models.Vendor;

@Mapper
public interface VendorMapper
{
    VendorMapper INSTANCE = Mappers.getMapper( VendorMapper.class );

    @Mapping( target = "vendorId", ignore = true )
    Vendor toVendor( VendorDto vendorDto );

    VendorDto toVendorDTO( Vendor vendor );
}
