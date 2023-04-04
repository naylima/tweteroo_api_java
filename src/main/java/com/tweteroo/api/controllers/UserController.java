package com.tweteroo.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.UserDTO;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sign-up")
public class UserController {
  
  @Autowired
  private UserRepository repository;

  @PostMapping
  public void create(@RequestBody @Valid UserDTO req) {
    repository.save(new UserModel(req));
  }
}
