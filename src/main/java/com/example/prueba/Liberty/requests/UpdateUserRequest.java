package com.example.prueba.Liberty.requests;

import com.example.prueba.Liberty.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
public class UpdateUserRequest {
    public MultipartFile image;

    @JsonIgnore
    public String imageURL;

    public String name;

    public String[] emails;

    public String gender;

    @Pattern(regexp = "^activo|inactivo$", message="the status fields must be 'activo' or 'inactivo'")
    public String status;

    public User toUser(){
        return new User().setImageURL(getImageURL()).setName(getName()).setEmails(getEmails()).setGender(getGender()).setStatus(getStatus());
    }
}
