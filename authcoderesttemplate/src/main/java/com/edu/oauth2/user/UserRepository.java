package com.edu.oauth2.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ClientUser, Long> {

    Optional<ClientUser> findByUsername(String username);

}
