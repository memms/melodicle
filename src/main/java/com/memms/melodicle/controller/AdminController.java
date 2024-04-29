package com.memms.melodicle.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.memms.melodicle.domain.dto.UserDTO;
import com.memms.melodicle.domain.vo.User;
import com.memms.melodicle.exceptions.UserNotFoundException;
import com.memms.melodicle.services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Operation(summary = "Test Admin")
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("GET::ADMIN");
    }

//  NOTE: Will require the patch use by specification in RFC 6902 https://jsonpatch.com/
    @Operation(summary = "Update existing user")
    @PatchMapping(path = "/user/{uid}", consumes = "application/json-patch+json")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<UserDTO> updateUser (@PathVariable("uid") Long uid, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        UserDTO userDTO = adminService.applyPatchToUser(uid, jsonPatch);
        return ResponseEntity.ok(userDTO);
    }

    @Operation(summary = "Get all user details")
    @GetMapping(path = "/user/{uid}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<UserDTO> getUser (@PathVariable("uid") Long uid){
        UserDTO userDTO = adminService.getUserById(uid);
        return ResponseEntity.ok(userDTO);
    }

    @Operation(summary = "Delete a user by ID")
    @DeleteMapping(path = "/user/{uid}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> deleteUserById (@PathVariable("uid") Long uid) {
        adminService.deleteUserById(uid);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a user, cannot create new account.")
    @PutMapping(path = "/user/{uid}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> updateUserById (@PathVariable("uid") Long uid, @RequestBody User user) {
        adminService.updateUserById(uid, user);
        return ResponseEntity.noContent().build();
    }



}
