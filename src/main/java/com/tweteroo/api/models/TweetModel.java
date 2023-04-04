package com.tweteroo.api.models;

import com.tweteroo.api.dto.TweetDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TweetModel {

  public TweetModel(TweetDTO data, String avatar) {
    this.username = data.username();
    this.avatar = avatar;
    this.text = data.text();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 20, nullable = false)
  private String username;

  @Column(nullable = false)
  private String avatar;

  @Column(length = 280, nullable = false)
  private String text;  
}
