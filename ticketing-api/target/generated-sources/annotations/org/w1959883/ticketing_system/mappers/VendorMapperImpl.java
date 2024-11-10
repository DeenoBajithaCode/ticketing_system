package org.w1959883.ticketing_system.mappers;

import javax.annotation.processing.Generated;
import org.w1959883.ticketing_system.dtos.VendorDto;
import org.w1959883.ticketing_system.models.Vendor;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-05T00:22:52+0530",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class VendorMapperImpl implements VendorMapper {

    @Override
    public Vendor toVendor(VendorDto vendorDto) {
        if ( vendorDto == null ) {
            return null;
        }

        Vendor vendor = new Vendor();

        vendor.setVendorName( vendorDto.getVendorName() );

        return vendor;
    }

    @Override
    public VendorDto toVendorDTO(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorDto vendorDto = new VendorDto();

        vendorDto.setVendorName( vendor.getVendorName() );

        return vendorDto;
    }
}
