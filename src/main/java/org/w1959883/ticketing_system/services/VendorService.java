package org.w1959883.ticketing_system.services;

import org.springframework.stereotype.Service;
import org.w1959883.ticketing_system.dtos.VendorDto;
import org.w1959883.ticketing_system.mappers.VendorMapper;
import org.w1959883.ticketing_system.models.Vendor;
import org.w1959883.ticketing_system.repositories.VendorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService
{
    private final VendorRepository vendorRepository;

    private final VendorMapper vendorMapper = VendorMapper.INSTANCE;

    public VendorService( VendorRepository vendorRepository )
    {
        this.vendorRepository = vendorRepository;
    }

    public void addVendor( VendorDto vendorDto )
    {
        Vendor vendor = vendorMapper.toVendor( vendorDto );
        vendorRepository.save( vendor );
    }

    public List<Vendor> getAllVendors()
    {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> getVendorById( Integer reservationId )
    {
        return vendorRepository.findById( reservationId );
    }

    public Vendor updateVendor( VendorDto vendorDTO )
    {
        Vendor vendor = vendorMapper.toVendor( vendorDTO );
        return vendorRepository.save( vendor );
    }

    public void deleteVendor( Integer vendorId )
    {
        vendorRepository.deleteById( vendorId );
    }

}
