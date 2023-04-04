package com.tweteroo.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.models.TweetModel;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repository.TweetRepository;
import com.tweteroo.api.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tweets")
public class TweetController {
  
  @Autowired
  private TweetRepository repository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public Page<TweetModel> listAll(@PageableDefault(page = 0, size = 5) Pageable page) {
    return repository.findAll(page);
  }

  @GetMapping("/{username}")
  public List<TweetModel> listAllByUsername(@PathVariable String username) {
    return repository.findByUsername(username);
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody @Valid TweetDTO req) {
    List<UserModel> user = userRepository.findByUsername(req.username());

    if (!user.isEmpty()) {
      repository.save(new TweetModel(req, user.get(0).getAvatar()));

      return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }
    
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body("You need to be signed up to post a tweet.");
  }
}
