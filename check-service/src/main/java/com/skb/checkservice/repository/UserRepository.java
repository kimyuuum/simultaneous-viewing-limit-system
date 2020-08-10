package com.skb.checkservice.repository;

import com.skb.checkservice.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long> {

}
