package com.publicissapient.lloyds.orderservice.doaminmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.publicissapient.lloyds.orderservice.domainmodel.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
	UserEntity findByName(String name);
}
