package com.thiagoiplinsky.exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thiagoiplinsky.exercise.entity.Users;
import com.thiagoiplinsky.exercise.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
		
	public Page<Users> findPage(
			Integer page, 
			Integer elementsPerPage, 
			Integer id, 
			String firstName, 
			String lastName) {

		Pageable pageable = PageRequest.of(page - 1, elementsPerPage);
		firstName = firstName == null ? null : "%" + firstName + "%";
		lastName = lastName == null ? null : "%" + lastName + "%";

		return usersRepository.findPage(id, firstName, lastName, pageable);
	}

	public void save(Users users) {
		usersRepository.save(users);
	}

	public void deleteById(Integer id) {
		try {
			usersRepository.deleteById(id);
		} 
		catch (DataIntegrityViolationException e) {
			e.getMessage();
		}
	}

	public Users update(Integer id, Users users) {
		return usersRepository.save(users);
	}

	public Users findById(Integer id) {
		return usersRepository.findById(id).orElse(null);
	}

	/* Configuração AmazonS3 */
	
//	//Aciona a classe s3Service para upload de foto de perfil
//	public URI uploadProfilePicture(MultipartFile multipartFile) {
//		return s3Service.uploadFile(multipartFile);
//	}
}
