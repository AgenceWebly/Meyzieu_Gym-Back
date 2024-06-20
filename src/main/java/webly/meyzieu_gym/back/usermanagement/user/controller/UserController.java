package webly.meyzieu_gym.back.usermanagement.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.usermanagement.user.dto.AddRibUrlDto;
import webly.meyzieu_gym.back.usermanagement.user.dto.UserProfileDto;
import webly.meyzieu_gym.back.usermanagement.user.dto.UserUpdateDto;
import webly.meyzieu_gym.back.usermanagement.user.service.RibService;
import webly.meyzieu_gym.back.usermanagement.user.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RibService ribService;

    public UserController(UserService userService, RibService ribService) {
        this.userService = userService;
        this.ribService = ribService;
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public UserProfileDto getUserProfile(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public UserProfileDto updateUserProfile(@PathVariable Long id, @Valid @RequestBody UserUpdateDto updatedUserDto) {
        return userService.updateUser(id, updatedUserDto);
    }

    @PostMapping("/{id}/rib")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<Void> addRibUrlToUser(@PathVariable Long id, @Valid @RequestBody AddRibUrlDto addRibUrlDto) {
        ribService.addRibUrlToUser(id, addRibUrlDto);
        return ResponseEntity.ok().build();
    }
}
