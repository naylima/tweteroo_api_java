package com.tweteroo.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweteroo.api.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
  List<UserModel> findByUsername(String username);
}
