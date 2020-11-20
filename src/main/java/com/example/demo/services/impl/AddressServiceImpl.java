package com.example.demo.services.impl;

import com.example.demo.entities.AddressEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.AddressService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.AddressDto;
import com.example.demo.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils util;

    @Override
    public List<AddressDto> getAllAddresses(String email) {

        UserEntity currentUser=userRepository.findByEmail(email);

        List<AddressEntity> addresses =currentUser.getAdmin()== true ? (List<AddressEntity> )addressRepository.findAll()  :addressRepository.findByUser(currentUser);
        Type listType = new TypeToken<List<AddressDto>>() {}.getType();
        List<AddressDto> addressesDto = new ModelMapper().map(addresses, listType);
        return addressesDto;
    }

    @Override
    public AddressDto createAddress(AddressDto address,String email){
        UserEntity currentUser=userRepository.findByEmail(email);
        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto =modelMapper.map(currentUser,UserDto.class);
        address.setAddressId(util.generateStringId(30));
        address.setUser(userDto);

        AddressEntity addressEntity=modelMapper.map(address,AddressEntity.class);
        AddressEntity newAddress=addressRepository.save(addressEntity);
        AddressDto addressDto=modelMapper.map(newAddress,AddressDto.class);
        return addressDto;
    }


    @Override
    public AddressDto getAddress(String addressId){
        AddressEntity addressEntity=addressRepository.findByAddressId(addressId);
        ModelMapper modelMapper=new ModelMapper();
        AddressDto AddressDto =modelMapper.map(addressEntity,AddressDto.class);
        return AddressDto;
    }

    @Override
    public void deleteAddress(String addressId){
        AddressEntity addressEntity =addressRepository.findByAddressId(addressId);
        if(addressEntity==null) throw new RuntimeException("Address not found");
        addressRepository.delete(addressEntity);
    }

    @Override
    public AddressDto updateAddress(String addressId,AddressDto addressDto) {

        AddressEntity addressEntity= addressRepository.findByAddressId(addressId);
        if(addressEntity==null) throw new RuntimeException("Address not found");


        addressEntity.setCity(addressDto.getCity());
        addressEntity.setCountry(addressDto.getCountry());
        addressEntity.setPostal(addressDto.getPostal());
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setType(addressDto.getType());


        AddressEntity addressUpdated= addressRepository.save(addressEntity);
        AddressDto address= new AddressDto();
        BeanUtils.copyProperties(addressUpdated,address);
        return address;

    }



}
