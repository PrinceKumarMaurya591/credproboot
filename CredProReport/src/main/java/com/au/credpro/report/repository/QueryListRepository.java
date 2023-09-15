package com.au.credpro.report.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.au.credpro.report.entity.QueryList;


@Repository
public interface QueryListRepository extends JpaRepository<QueryList,Long> {

       QueryList findByQueryId(Long queryId); 
	Page<QueryList> findAll(Pageable pageable);
}
