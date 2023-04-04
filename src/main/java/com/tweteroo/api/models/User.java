package com.tweteroo.api.models;

import com.tweteroo.api.dto.UserDTO;

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
public class User {

  public User(UserDTO data) {
    this.username = data.username();
    this.avatar = data.avatar();
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 20, nullable = false)
  private String username;

  @Column(nullable = false)
  private String avatar;
}
