package com.main.dto;

import com.main.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotEmpty
  private String firstname;

  private String lastname;

  @NotEmpty
  private String phone;

}
