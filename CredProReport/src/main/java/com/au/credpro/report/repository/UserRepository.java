package com.au.credpro.report.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.au.credpro.report.entity.QueryList;
import com.au.credpro.report.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	 User findByUserAuId(Long userAuId);
//	 Page<User> findAll(Pageable pageable);
	 
	 Optional<User> findByUserAuIdAndIsAdmin(Long userAuId, Boolean isAdmin);
}
