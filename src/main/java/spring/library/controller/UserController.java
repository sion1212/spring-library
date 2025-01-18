package spring.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.UserRequest;
import spring.library.controller.response.UserResponse;
import spring.library.dto.UserDto;
import spring.library.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/members")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getUsers().stream().map(UserResponse::from).toList();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/members")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        UserDto userDto = userService.save(userRequest);
        return ResponseEntity.ok().body(UserResponse.from(userDto));
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable int memberId, @RequestBody UserRequest userRequest) {
        UserDto userDto = userService.update(memberId, userRequest);
        return ResponseEntity.ok().body(UserResponse.from(userDto));
    }

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int memberId) {
        userService.delete(memberId);
        return ResponseEntity.ok().build();
    }
}
