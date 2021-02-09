package com.jd.ssetest.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jd.ssetest.JsonUtil;
import com.jd.ssetest.domain.Address;
import com.jd.ssetest.domain.Person;
import com.jd.ssetest.service.IService.IPersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(value =  PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IPersonService PersonService;

	
	private static String URL = "http://localhost:8080/";
			

	

	/*
	@Test
	public void addPersonTest() throws Exception {
		Set<Address> adder = new HashSet<>();
		adder.add(new Address("Chaitnaypuri coleny","Hyd","Telengana","500056"));
		adder.add(new Address("Chandragiry coleny","Hyd","Telengana","500017"));
		adder.add(new Address("Padmarao nagar","Hyd","Andhra Pradesh","522007"));		
		Person mockPerson = new Person("Ravi Kumar", "Kotamraju",adder);
		Object object = null; 
				try {
					object = JsonUtil.getHttpPostOrPut(JsonUtil.convertToJson(mockPerson), new HttpPost(URL+"person"));
				} catch (Exception e) {
					object = e;
				}
		
		  assertEquals(object.getClass().getCanonicalName(), "String",true);
		  
		  object = null; 
		  Person person = null;
			try {
				object = JsonUtil.getHttpResponse(new HttpGet(URL+"person/1"));
				person = JsonUtil.convertToPojo((String)object,Person.class);
			} catch (Exception e) {
				object = e;
			}
	
		assertEquals(object.getClass().getCanonicalName(), "String",true);
		
		object = null; 
		try {
			if(person!=null) {
				person.setLastName("Venkata Kotamraju");	
			}
			object = JsonUtil.getHttpPostOrPut(JsonUtil.convertToJson(person), new HttpPut(URL+"person"));
		} catch (Exception e) {
			object = e;
		}

		 object = null; 
		  Person person = null;
			try {
				object = JsonUtil.getHttpResponse(new HttpGet(URL+"person/1"));
				person = JsonUtil.convertToPojo((String)object,Person.class);
			} catch (Exception e) {
				object = e;
			}
	
		assertEquals(object.getClass().getCanonicalName(), "String",true);
	}
	
	@Test
	public void retrieveDetailsForCourse() throws Exception {

		
		
		Mockito.when(
				PersonService.retrieveCourse(Mockito.anyString(),
						Mockito.anyString())).thenReturn(mockCourse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/Persons/Person1/courses/Course1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:Course1,name:Spring,description:10Steps}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Persons","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
*/
	
	
	
	
	
	
	
	
	
	
	@Test
	public void createPerson() throws Exception {
		Set<Address> adder = new HashSet<>();
		adder.add(new Address("Chaitnaypuri coleny","Hyd","Telengana","500056"));
		adder.add(new Address("Chandragiry coleny","Hyd","Telengana","500017"));
		adder.add(new Address("Padmarao nagar","Hyd","Andhra Pradesh","522007"));		
		Person mockPerson = new Person("Ravi Kumar", "Kotamraju",adder);
		// PersonService.addCourse to respond back with mockCourse
		Mockito.when(
				PersonService.addPerson(Mockito.any(Person.class))).thenReturn(mockPerson);

		// Send course as body to /Persons/Person1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/Person")
				.accept(MediaType.APPLICATION_JSON).content(JsonUtil.convertToJson(mockPerson))
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost:8080/Person",response.getHeader(HttpHeaders.LOCATION));

	}

}

