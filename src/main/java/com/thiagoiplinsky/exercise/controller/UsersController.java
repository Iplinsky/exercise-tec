package com.thiagoiplinsky.exercise.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thiagoiplinsky.exercise.config.ByteUtil;
import com.thiagoiplinsky.exercise.entity.Users;
import com.thiagoiplinsky.exercise.exceptionHandler.BusinessRuleException;
import com.thiagoiplinsky.exercise.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	private Byte[] archive; 
			
	@GetMapping
	public Page<Users> findPage(
			@RequestParam("page") Integer page,
			@RequestParam("elementsPerPage") Integer elementsPerPage,
			@RequestParam(value = ("id"), required = false) Integer id,
			@RequestParam(value = ("firstName"), required = false) String firstName,
			@RequestParam(value = ("lastName"), required = false) String lastName) {
		
		return usersService.findPage(
				page,
				elementsPerPage,
				id,
				firstName,
				lastName);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Users> findById(@PathVariable Integer id) {
		Users users = usersService.findById(id);
		return users != null ? ResponseEntity.ok(users) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Users users) {
		if (archive == null) {
			throw new BusinessRuleException("Please upload your image.");
		} else {
			users.setImgLogo(archive);
		}
		users.setCreationDate(LocalDateTime.now());
		usersService.save(users);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		usersService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Users> update(@PathVariable Integer id, @Valid @RequestBody Users users) {
		if (archive == null) {
			if (users.getImgLogo() == null) {
				users.setImgLogo(ByteUtil.convertByteArray(Base64.decodeBase64(users.getImagemString())));
			}
		} else {
			users.setImgLogo(archive);
		}
		users.setChangeDate(LocalDateTime.now());
		return ResponseEntity.ok(usersService.update(id, users));
	}
	
	@PostMapping("/uploadArchive")
	public ResponseEntity<String> uploadArchive(@RequestBody MultipartFile file, HttpServletResponse response) {
		try {
			this.archive = ByteUtil.convertByteArray(file.getBytes());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Sent with success!");
	}
	
	/* Configuração AmazonS3 */
	
//	@PostMapping("/picture")
//	public ResponseEntity<Void> uploadFilePicture(@RequestParam(value="file") MultipartFile file) {
//		URI uri = usersService.uploadProfilePicture(file);
////		Criação de conteúdo retornando 201 created
//		return ResponseEntity.created(uri).build();
//	}
}
