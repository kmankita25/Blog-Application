package com.blog.application.bog_app_api.payload;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 charecters")
    private  String name;
    @Email(message = "Email address is not valid")
    private String email;
    @NotEmpty
    @Size(min = 3,max = 10,message = "Password must be min of three chars max of 10 chars")
/*    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$")*/
    private String password;
    @NotEmpty
    private String about;

}
