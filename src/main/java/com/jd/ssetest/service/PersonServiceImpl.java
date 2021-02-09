package com.jd.ssetest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.ssetest.domain.Person;
import com.jd.ssetest.repository.PersonRepository;
import com.jd.ssetest.service.IService.IPersonService;


@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Person addPerson(Person p) {
		return personRepository.save(p);		
	}

	

	@Override
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
		
	}

	@Override
	public List<Person> getAllPersons() {

		return personRepository.findAll();
	}

	@Override
	public Long getPersonCount() {
		// TODO Auto-generated method stub
		return personRepository.count();
	}



	@Override
	public Person getPersonById(Long id) {
		return personRepository.getOne(id);
	}

}
