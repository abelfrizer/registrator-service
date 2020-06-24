package net.iqbusiness.app.registrator.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import net.iqbusiness.app.registrator.domain.UserService;
import net.iqbusiness.app.registrator.model.dto.UserDTO;
import net.iqbusiness.commons.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @ApiOperation(value = "Create a new user record")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 400, message = "Failed due to validation errors (check 'errors' on response header)"),
            @ApiResponse(code = 500, message = "Technial server error occured")
    })
    @PostMapping("/")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UserDTO newDTO = service.create(dto);
            return new ResponseEntity(newDTO, HttpStatus.CREATED);
        } catch (BusinessException ex) {
            headers.addAll("errors", ex.getErrors());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("ServerError while creating new user", ex);
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
