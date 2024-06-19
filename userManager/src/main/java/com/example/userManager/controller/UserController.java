package com.example.userManager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userManager.model.User;
import com.example.userManager.repository.UserRepository;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok(users);
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> createUser(@RequestBody User user) {
	    User newUser = userRepository.save(user);
	    return ResponseEntity.ok(newUser);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable Integer id) throws Exception {
	    if (!userRepository.existsById(id)) {
	    	throw new Exception();
	    }
	    userRepository.deleteById(id);
	}
	
	@GetMapping("/edit/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok(user.get());
    }
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<User> EditUser(@PathVariable Integer id, @RequestBody User userDetails) {
		User user = userRepository.getReferenceById(id);
		user.setName(userDetails.getName());
		user.setSurname(userDetails.getSurname());
		user.setEmail(userDetails.getEmail());
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}

}
