package com.jd.ssetest.service.IService;

import java.util.List;

import com.jd.ssetest.domain.Person;

public interface IPersonService {
	public Person addPerson(Person p);	
	Person getPersonById (Long id);
	public void deletePerson(Long id);
	public List<Person> getAllPersons();
	public Long getPersonCount();
}
