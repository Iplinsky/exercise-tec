package com.thiagoiplinsky.exercise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thiagoiplinsky.exercise.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	@Query("FROM Users u "
			+ "WHERE  (u.id				 =			:id					or		:id					=	null) "
			+ "AND	  (u.firstName 	 like		:firstName		or 	:firstName		=	 ''   ) "
			+ "AND	  (u.lastName	 like		:lastName		or		:lastName		=	 ''   )"
	)
	Page<Users> findPage(
			@Param ("id") Integer id,
			@Param ("firstName") String firstName, 
			@Param ("lastName") String lastName, 
			Pageable pageable);
}
