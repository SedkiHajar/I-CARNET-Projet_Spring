package com.example.demo.controllers;

import com.example.demo.requests.AddressRequest;
import com.example.demo.requests.UserRequest;
import com.example.demo.responses.AddressResponse;
import com.example.demo.responses.UserResponse;
import com.example.demo.services.AddressService;
import com.example.demo.shared.dto.AddressDto;
import com.example.demo.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAddresses(Principal principal){

        List<AddressDto> addresses=addressService.getAllAddresses(principal.getName());
        Type listType = new TypeToken<List<AddressResponse>>() {}.getType();
        List<AddressResponse> addressesResponse = new ModelMapper().map(addresses, listType);

        return new ResponseEntity<List<AddressResponse>>(addressesResponse, HttpStatus.OK);
    }

    @PostMapping(consumes ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<AddressResponse> StoreAddress(@RequestBody AddressRequest addressRequest, Principal principal){

        ModelMapper modelMapper = new ModelMapper();
        AddressDto addressDto = modelMapper.map(addressRequest, AddressDto.class);

        AddressDto createAddress =addressService.createAddress(addressDto,principal.getName());

        AddressResponse newAddress=modelMapper.map(createAddress,AddressResponse.class);
        return new ResponseEntity<AddressResponse>(newAddress, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getOneAddress(@PathVariable(name="id") String addressId){
        AddressDto addressDto=addressService.getAddress(addressId);
        ModelMapper modelMapper = new ModelMapper();
        AddressResponse addressResponse = modelMapper.map(addressDto, AddressResponse.class);
        return new ResponseEntity<AddressResponse>(addressResponse, HttpStatus.OK);
    }
    @PutMapping(path="/{id}",
            consumes ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateAddress(@PathVariable(name="id") String addressId,@RequestBody AddressRequest addressRequest){
        ModelMapper modelMapper = new ModelMapper();
        AddressDto addressDto = modelMapper.map(addressRequest, AddressDto.class);

        AddressDto updateAddress =addressService.updateAddress(addressId,addressDto);
        AddressResponse addressResponse = modelMapper.map(updateAddress, AddressResponse.class);

        return new ResponseEntity<>("update addresses",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddresse(@PathVariable(name="id") String addressId){
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
