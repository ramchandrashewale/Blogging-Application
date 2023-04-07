package com.blog.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 3 ,message = "user name must be have at least 3 character")
    private String name;
    @Email
    private String email;
    @NotEmpty
    @Size(min=3 , max = 10)
    private String password;
    @NotEmpty
    private String about;
}
