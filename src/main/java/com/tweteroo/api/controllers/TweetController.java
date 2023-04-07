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
import com.tweteroo.api.services.TweetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tweets")
public class TweetController {
  
  @Autowired
  private TweetService service;

  @GetMapping
  public Page<TweetModel> listAll(
    @PageableDefault(page = 0, size = 5) 
    Pageable page
    ) {

    return service.findAll(page);
  }

  @GetMapping("/{username}")
  public List<TweetModel> listAllByUsername(
    @PathVariable 
    String username
    ) {
      
    return service.findByUsername(username);
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody @Valid TweetDTO req) {

    String status = service.save(req);

    if ("OK".equals(status)) {
      return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }      
   
    return ResponseEntity
      .status(HttpStatus.UNAUTHORIZED)
      .body("You need to be signed up to post a tweet.");    
  }
}
