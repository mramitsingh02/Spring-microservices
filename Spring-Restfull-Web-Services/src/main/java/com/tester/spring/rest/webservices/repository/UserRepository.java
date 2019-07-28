package com.tester.spring.rest.webservices.repository;

import com.tester.spring.rest.webservices.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
