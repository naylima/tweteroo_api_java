package com.tweteroo.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.models.TweetModel;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repositories.TweetRepository;
import com.tweteroo.api.repositories.UserRepository;

@Service
public class TweetService {
  
  @Autowired
  private TweetRepository tweetRepository;

  @Autowired
  private UserRepository userRepository;
  
  public Page<TweetModel> findAll(Pageable page) {
    return tweetRepository.findAll(page);
  }

  public List<TweetModel> findByUsername(String username) {
    return tweetRepository.findByUsername(username);
  }

  public String save(TweetDTO data) {
    List<UserModel> user = userRepository.findByUsername(data.username());
    String status = "Unauthorized";

    if (!user.isEmpty()) {
      tweetRepository.save(new TweetModel(data, user.get(0).getAvatar()));

      status = "OK";

      return status;
    }

    return status;
  }
}
