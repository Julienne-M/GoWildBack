package fr.wcs.gowild.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.wcs.gowild.exception.RessourceNotFoundException;
import fr.wcs.gowild.model.User;
import fr.wcs.gowild.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public List<User>getAllUsers() {
		return this.userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value = "id")Long userId) {
		return this.userRepository.findById(userId).orElseThrow(() -> new RessourceNotFoundException("User","id", userId));
	}
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return this.userRepository.save(user);
	}
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value = "id")Long userId, @RequestBody User userDetails) {
		final User user = this.userRepository.findById(userId).orElseThrow(()-> new RessourceNotFoundException("User","id", userId));
		user.setName(userDetails.getName());
		user.setPassword(userDetails.getPassword());
		user.setMail(userDetails.getPassword());
		user.setMail(userDetails.getMail());

		final User updateUser = this.userRepository.save(user);
		return updateUser;
	}
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
		final User user = this.userRepository.findById(userId).orElseThrow(()-> new RessourceNotFoundException("User","id", userId));
		this.userRepository.delete(user);
		return ResponseEntity.ok().build();

	}
}
