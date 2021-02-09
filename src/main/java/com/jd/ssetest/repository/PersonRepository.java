package com.jd.ssetest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jd.ssetest.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	long count();
}
