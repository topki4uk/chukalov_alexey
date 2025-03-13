package com.example.demo.model.user;

import lombok.Data;

@Data
public class UserDto {
  public Long id;
  public String email;
  public String password;
  public String username;
}
