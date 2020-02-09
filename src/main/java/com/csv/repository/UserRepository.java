package com.csv.repository;


import com.csv.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findBySurname(String surname);

    @Query(nativeQuery = true)
    List<User> retrieveUsersByAge();

    @Query(nativeQuery = true)
    List<User> retrieveTheOldestUserWithPhoneNumber();
}
