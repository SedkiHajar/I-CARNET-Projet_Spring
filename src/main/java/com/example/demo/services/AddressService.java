package com.example.demo.services;

import com.example.demo.shared.dto.AddressDto;



import java.util.List;

public interface AddressService   {

    List<AddressDto> getAllAddresses(String email);

    AddressDto createAddress(AddressDto address, String email);

    AddressDto getAddress( String addressId);

    //AddressDto updateAddress(String addressId);

    void deleteAddress(String addressId);

    AddressDto updateAddress(String Id,AddressDto addressDto);
}
