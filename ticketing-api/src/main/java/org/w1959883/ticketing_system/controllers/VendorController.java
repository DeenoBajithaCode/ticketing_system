package org.w1959883.ticketing_system.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w1959883.ticketing_system.dtos.VendorDto;
import org.w1959883.ticketing_system.models.Vendor;
import org.w1959883.ticketing_system.services.VendorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/vendor" )
public class VendorController
{
    private final VendorService vendorService;

    public VendorController( VendorService vendorService )
    {
        this.vendorService = vendorService;
    }

    @PostMapping("/add")
    private void addVendor( VendorDto vendorDto ){
        vendorService.addVendor(vendorDto);
    }

    @GetMapping
    public List<Vendor> getAllVendors()
    {
        return vendorService.getAllVendors();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Vendor> getVendorById( @PathVariable Integer id )
    {
        Optional<Vendor> vendor = vendorService.getVendorById( id );
        return vendor.map( ResponseEntity::ok )
                          .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Vendor> updateVendor( @PathVariable Integer id, @RequestBody VendorDto vendorDTO )
    {
        Optional<Vendor> vendor = vendorService.getVendorById( id );
        if( vendor.isPresent() )
        {
            return ResponseEntity.ok( vendorService.updateVendor( vendorDTO ) );
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deleteVendor( @PathVariable Integer id )
    {
        Optional<Vendor> vendor = vendorService.getVendorById( id );
        if( vendor.isPresent() )
        {
            vendorService.deleteVendor( id );
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
