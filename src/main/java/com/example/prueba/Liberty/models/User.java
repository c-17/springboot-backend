package com.example.prueba.Liberty.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Email> emails = new ArrayList<>();

    public String[] getEmails(){
        List<String> emails = new ArrayList<>();

        for(Email email : this.emails)
            emails.add(email.getEmail());

        return emails.toArray(new String[]{});
    }

    public User setEmails(String[] emails){
        this.emails.clear();

        if(emails != null) {
            for (String email : emails)
                this.emails.add(new Email(null, this, email));
        }

        return this;
    }

    @Column(name = "gender")
    private String gender;

    @Column(name = "status")
    private String status;
}
