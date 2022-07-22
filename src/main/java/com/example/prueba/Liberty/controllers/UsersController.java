package com.example.prueba.Liberty.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.prueba.Liberty.models.User;
import com.example.prueba.Liberty.requests.UpdateUserRequest;
import com.example.prueba.Liberty.requests.CreateUserRequest;
import com.example.prueba.Liberty.responses.UserResponse;
import com.example.prueba.Liberty.services.FileManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.prueba.Liberty.services.UsersService;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController implements ServletContextAware {
    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg");

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Autowired
    private UsersService usersService;

    @Autowired
    private FileManagementService fileManagementService;

    @Operation(
            summary = "Get all users",
            description = "Get all users using page and size as params",
            tags = "users",
            parameters = {
                    @Parameter(name = "page", schema = @Schema(type = "integer", defaultValue = "0"), in = ParameterIn.QUERY, description = "Current page to display", example = "0"),
                    @Parameter(name = "size", schema = @Schema(type = "integer", defaultValue = "10"), in = ParameterIn.QUERY, description = "No. users to display", example = "5")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieve users array")
            }
    )
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(HttpServletRequest request){
        int page = 0, size = 10;

        if(request.getParameterMap().containsKey("page"))
            page = NumberUtils.isParsable(request.getParameter("page"))?Integer.parseInt(request.getParameter("page")):0;

        if(request.getParameterMap().containsKey("size"))
            size = NumberUtils.isParsable(request.getParameter("size"))?Integer.parseInt(request.getParameter("size")):10;

        List<UserResponse> usersResponse =  usersService.getUsersPaginated(page, size).stream().map(user -> new UserResponse(user)).collect(Collectors.toList());

        return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Get a user",
            tags = "users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get User by id.")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> get(@PathVariable("id") long userId){
        return new ResponseEntity<UserResponse>(new UserResponse(usersService.getUser(userId)), HttpStatus.OK);
    }

    @Operation(
            summary = "Create a new user",
            tags = "users",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User has been created.")
            }
    )
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<UserResponse> create(@Valid @RequestBody @ModelAttribute CreateUserRequest createUserRequest, BindingResult bindingResult) throws IllegalStateException, IOException {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage(), null);

        if(!contentTypes.contains(createUserRequest.getImage().getContentType()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the file extension is incompatible must be '.png' or '.jpg'", null);

        createUserRequest.setImageURL(fileManagementService.uploadFile(createUserRequest.getImage()));

        return new ResponseEntity<UserResponse>(new UserResponse(usersService.createUser(createUserRequest.toUser())), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update a user",
            tags = "users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User has been updated.")
            }
    )
    @PutMapping(value = "{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<UserResponse> update(@Valid @PathVariable("id") long userId, @RequestBody @ModelAttribute UpdateUserRequest updateUserRequest, BindingResult bindingResult) throws IllegalStateException, IOException {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage(), null);

        if(updateUserRequest.getImage() != null){
            if(!contentTypes.contains(updateUserRequest.getImage().getContentType()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the file extension is incompatible must be '.png' or '.jpg'", null);

            User user = usersService.getUser(userId);

            fileManagementService.deleteFile(new File(user.getImageURL()).getName());

            updateUserRequest.setImageURL(fileManagementService.uploadFile(updateUserRequest.getImage()));
        }

        return new ResponseEntity<UserResponse>(new UserResponse(usersService.updateUser(userId, updateUserRequest.toUser())), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a user",
            tags = "users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User has been deleted.")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long userId)throws IllegalStateException, IOException{
        User user = usersService.getUser(userId);

        fileManagementService.deleteFile(new File(user.getImageURL()).getName());

        usersService.deleteUser(userId);

        return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);
    }
}
