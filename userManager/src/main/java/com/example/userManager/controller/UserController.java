package com.example.userManager.controller;

import java.util.List;

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
	public void deleteUser(@PathVariable Integer id) throws NullPointerException{
	    if (userRepository.existsById(id)) {
	    	userRepository.deleteById(id);
	    }
		else{
			throw new NullPointerException("There is no user with id: " + id);
		}
	}
	
	@GetMapping("/edit/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userRepository.findById(id).get());
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
