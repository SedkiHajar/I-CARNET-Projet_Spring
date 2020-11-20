package com.example.demo.repositories;

import com.example.demo.entities.AddressEntity;
import com.example.demo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressRepository extends CrudRepository<AddressEntity,Long> {

    List<AddressEntity> findByUser(UserEntity currentUser);

    AddressEntity findByAddressId(String AddressId);
}
