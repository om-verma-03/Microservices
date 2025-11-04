package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

//    @GetMapping("/api/users")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.OK);
    }

//    @GetMapping("/api/user/{id}")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.fetchUser(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

//    @PostMapping("/api/users")
    @RequestMapping(value = "/" , method = RequestMethod.POST)
    public String saveUser(@RequestBody User user) {
        userService.addUsers(user);
        return "User Added";
    }

//    @PutMapping("/api/users/{id}")
    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody User updatedUser) {
        boolean updated = userService.updateUser(id, updatedUser);
        if(updated){
            return ResponseEntity.ok("User Updated Successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
