package net.iqbusiness.app.registrator.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import net.iqbusiness.app.registrator.domain.UserService;
import net.iqbusiness.app.registrator.model.dto.UserDTO;
import net.iqbusiness.app.registrator.model.dto.UserSearchDTO;
import net.iqbusiness.commons.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @ApiOperation(value = "Retrieve all (top 100) records")
    @GetMapping("/")
    public ResponseEntity findAll(){
        HttpHeaders headers = new HttpHeaders();
        try {
            return new ResponseEntity(service.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("ServerError while retrieving all users", ex);
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search users per criteria")
    @PostMapping("/search")
    public ResponseEntity search(@RequestBody UserSearchDTO dto){
        HttpHeaders headers = new HttpHeaders();
        try {
            return new ResponseEntity(service.search(dto), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("ServerError while retrieving all users", ex);
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Create a new user record")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 400, message = "Failed due to validation errors (check 'errors' on response header)"),
            @ApiResponse(code = 500, message = "Technial server error occured")
    })
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserDTO dto) {
        HttpHeaders headers = new HttpHeaders();
        try {
            return new ResponseEntity(service.create(dto), HttpStatus.CREATED);
        } catch (BusinessException ex) {
            headers.addAll("errors", ex.getErrors());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("ServerError while creating new user", ex);
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity findById(@PathVariable("uuid") String uuid){
        HttpHeaders headers = new HttpHeaders();
        try {
            UserDTO dto = service.findByUuid(uuid);
            return new ResponseEntity(dto, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("ServerError while retrieving all users", ex);
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
