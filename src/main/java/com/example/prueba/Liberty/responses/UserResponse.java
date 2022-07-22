package com.example.prueba.Liberty.responses;

import com.example.prueba.Liberty.models.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponse {
    private Long id;

    private String imageURL;

    public String name;

    public String[] emails;

    public String gender;

    public String status;

    public UserResponse(User user){
        setId(user.getId()).setImageURL(user.getImageURL()).setName(user.getName()).setEmails(user.getEmails()).setGender(user.getGender()).setStatus(user.getStatus());
    }
}
