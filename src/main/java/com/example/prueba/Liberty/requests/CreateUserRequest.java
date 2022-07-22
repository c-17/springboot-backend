package com.example.prueba.Liberty.requests;

import com.example.prueba.Liberty.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CreateUserRequest {
    @NotNull(message = "the image field must not be null")
    public MultipartFile image;

    @JsonIgnore
    public String imageURL;

    @NotEmpty(message = "the name field must not be empty")
    public String name;

    public String[] emails;

    @NotEmpty(message = "the gender field must not be empty")
    public String gender;

    @NotEmpty(message = "the status fields must be 'activo' or 'inactivo'")
    @Pattern(regexp = "^activo|inactivo$", message="the status fields must be 'activo' or 'inactivo'")
    public String status;

    public User toUser(){
        return new User().setImageURL(getImageURL()).setName(getName()).setEmails(getEmails()).setGender(getGender()).setStatus(getStatus());
    }
}
