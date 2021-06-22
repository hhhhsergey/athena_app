package com.hhhh.athena.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hhhh.athena.AthenaApplication;
import com.hhhh.athena.model.Address;
import com.hhhh.athena.model.Customer;
import com.hhhh.athena.service.CustomerService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=AthenaApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@ActiveProfiles("application.properties")
@TestPropertySource("classpath:applicationtest.properties") //а может и эта сгодиться (из летскода)
//@Sql(value = {"classpath:sqlBeforeTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:sqlAfterTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class MainControllerTest {
	
	@Autowired
	private MainController mainController;
	
	@BeforeAll
	@Sql(value = {"classpath:sqlAfterTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public static void clear() {
		System.out.println("---base is clear---");
		
	}
	
//	@BeforeEach
//	public void t1() {
//		System.out.println("---before---");
//	}
	
	@AfterEach
	public void t2() {
		System.out.println("---After---");
	}

	@Test
	@Sql(value = {"classpath:sqlBeforeTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testfindall() throws Exception {
		
		System.out.println("---testFindAll");
		Customer customer1=new Customer();
		customer1.setId(1);
		customer1.setFirst_name("jack");
		customer1.setMiddle_name("captain");
		customer1.setLast_name("sparrow");
		customer1.setSex("male");
		Address actualAddress1=new Address();
		actualAddress1.setId(1);
		actualAddress1.setContry("ncontry");
		actualAddress1.setRegion("nmodified");
		actualAddress1.setCity("ncity");
		actualAddress1.setStreet("nstreet");
		actualAddress1.setHouse("blackPearlShip");
		actualAddress1.setFlat("nflat");
		actualAddress1.setCreated(null);
		actualAddress1.setModified(null);
		Address registredAddress1=new Address();
		registredAddress1.setId(2);
		registredAddress1.setContry("ncontry");
		registredAddress1.setRegion("nmodified");
		registredAddress1.setCity("ncity");
		registredAddress1.setStreet("nstreet");
		registredAddress1.setHouse("blackPearlShip");
		registredAddress1.setFlat("nflat");
		registredAddress1.setCreated(null);
		registredAddress1.setModified(null);
		customer1.setActual_address(actualAddress1);
		customer1.setRegistred_address(registredAddress1);

		
		Customer customer=new Customer();
		customer.setId(2);
		customer.setFirst_name("Edward");
		customer.setMiddle_name("Blackbeard");
		customer.setLast_name("Teach");
		customer.setSex("male");
		Address actualAddress=new Address();
		actualAddress.setId(3);
		actualAddress.setContry("zcontry");
		actualAddress.setRegion("zmodified");
		actualAddress.setCity("zcity");
		actualAddress.setFlat("zflat");
		actualAddress.setStreet("zstreet");
		actualAddress.setCreated(null);
		actualAddress.setModified(null);
		actualAddress.setHouse("QueenAnnesRevengeShip");
		Address registredAddress=new Address();
		registredAddress.setId(4);
		registredAddress.setContry("zcontry");
		registredAddress.setRegion("zmodified");
		registredAddress.setCity("zcity");
		registredAddress.setFlat("zflat");
		registredAddress.setStreet("zstreet");
		registredAddress.setHouse("QueenAnnesRevengeShip");
		registredAddress.setCreated(null);
		registredAddress.setModified(null);
		customer.setActual_address(actualAddress);
		customer.setRegistred_address(registredAddress);
		
		List<Customer>testlistexpected=new ArrayList<Customer>();
		testlistexpected.add(customer1);
		testlistexpected.add(customer);
		
		List<Customer>findAllList=mainController.findall();
		assertThat(testlistexpected,hasItems(customer1,customer));
		assertEquals(2, findAllList.size());
	}

	@Test
	@Sql(value = {"classpath:sqlAfterTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	void testCreate() throws JsonMappingException, JsonProcessingException {
		System.out.println("---testCreate");
		Customer expected=new Customer();
		expected.setId(1);
		expected.setFirst_name("jack");
		expected.setMiddle_name("captain");
		expected.setLast_name("sparrow");
		expected.setSex("male");
		Address actualAddress1=new Address();
		actualAddress1.setId(1);
		actualAddress1.setContry("ncontry");
		actualAddress1.setRegion("nmodified");
		actualAddress1.setCity("ncity");
		actualAddress1.setStreet("nstreet");
		actualAddress1.setHouse("blackPearlShip");
		actualAddress1.setFlat("nflat");
		actualAddress1.setCreated(null);
		actualAddress1.setModified(null);
		Address registredAddress1=new Address();
		registredAddress1.setId(2);
		registredAddress1.setContry("ncontry");
		registredAddress1.setRegion("nmodified");
		registredAddress1.setCity("ncity");
		registredAddress1.setStreet("nstreet");
		registredAddress1.setHouse("blackPearlShip");
		registredAddress1.setFlat("nflat");
		registredAddress1.setCreated(null);
		registredAddress1.setModified(null);
		expected.setActual_address(actualAddress1);
		expected.setRegistred_address(registredAddress1);
		
		
		String json="{\"first_name\":\"jack\",\"last_name\":\"sparrow\",\"middle_name\":\"captain\",\"sex\":\"male\",\"registred_address\":{\"contry\":\"ncontry\",\"region\":\"nmodified\",\"city\":\"ncity\",\"street\":\"nstreet\",\"house\":\"blackPearlShip\",\"flat\":\"nflat\"},\"actual_address\":{\"contry\":\"ncontry\",\"region\":\"nmodified\",\"city\":\"ncity\",\"street\":\"nstreet\",\"house\":\"blackPearlShip\",\"flat\":\"nflat\"}}";
		mainController.create(json);
//		System.out.println("try findall");
//		System.out.println("show findall "+mainController.findall());
		Customer actual=null;
		List<Customer>templist=mainController.findall();
		actual=templist.get(0);
		assertEquals(expected.getFirst_name(), actual.getFirst_name());
		assertEquals(expected.getMiddle_name(), actual.getMiddle_name());
		assertEquals(expected.getLast_name(), actual.getLast_name());
		assertEquals(expected.getSex(), actual.getSex());
		assertEquals(expected.getRegistred_address().getContry(), actual.getRegistred_address().getContry());
		assertEquals(expected.getRegistred_address().getRegion(), actual.getRegistred_address().getRegion());
		assertEquals(expected.getRegistred_address().getCity(), actual.getRegistred_address().getCity());
		assertEquals(expected.getRegistred_address().getStreet(), actual.getRegistred_address().getStreet());
		assertEquals(expected.getRegistred_address().getHouse(), actual.getRegistred_address().getHouse());
		assertEquals(expected.getRegistred_address().getFlat(), actual.getRegistred_address().getFlat());
		assertEquals(expected.getActual_address().getContry(), actual.getActual_address().getContry());
		assertEquals(expected.getActual_address().getRegion(), actual.getActual_address().getRegion());
		assertEquals(expected.getActual_address().getCity(), actual.getActual_address().getCity());
		assertEquals(expected.getActual_address().getStreet(), actual.getActual_address().getStreet());
		assertEquals(expected.getActual_address().getHouse(), actual.getActual_address().getHouse());
		assertEquals(expected.getActual_address().getFlat(), actual.getActual_address().getFlat());
	}

	@Test
	@Sql(value = {"classpath:sqlAfterTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(value = {"classpath:sqlAfterTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	void testUpdate() throws JsonMappingException, JsonProcessingException {
		System.out.println("---testUpdate");
		
		Customer expected=new Customer();
		expected.setId(1);
		expected.setFirst_name("jack");
		expected.setMiddle_name("captain");
		expected.setLast_name("sparrow");
		expected.setSex("male");
		Address actualAddress1=new Address();
		actualAddress1.setId(1);
		actualAddress1.setContry("Updatencontry");
		actualAddress1.setRegion("Updatenmodified");
		actualAddress1.setCity("Updatencity");
		actualAddress1.setStreet("Updatenstreet");
		actualAddress1.setHouse("UpdateblackPearlShip");
		actualAddress1.setFlat("Updatenflat");
		actualAddress1.setCreated(null);
		actualAddress1.setModified(null);
		Address registredAddress1=new Address();
		registredAddress1.setId(2);
		registredAddress1.setContry("ncontry");
		registredAddress1.setRegion("nmodified");
		registredAddress1.setCity("ncity");
		registredAddress1.setStreet("nstreet");
		registredAddress1.setHouse("blackPearlShip");
		registredAddress1.setFlat("nflat");
		registredAddress1.setCreated(null);
		registredAddress1.setModified(null);
		expected.setActual_address(actualAddress1);
		expected.setRegistred_address(registredAddress1);

		//create in db
		String json="{\"first_name\":\"jack\",\"last_name\":\"sparrow\",\"middle_name\":\"captain\",\"sex\":\"male\",\"registred_address\":{\"contry\":\"ncontry\",\"region\":\"nmodified\",\"city\":\"ncity\",\"street\":\"nstreet\",\"house\":\"blackPearlShip\",\"flat\":\"nflat\"},\"actual_address\":{\"contry\":\"ncontry\",\"region\":\"nmodified\",\"city\":\"ncity\",\"street\":\"nstreet\",\"house\":\"blackPearlShip\",\"flat\":\"nflat\"}}";
		mainController.create(json);
		System.out.println("try findall");
		System.out.println("show findall "+mainController.findall());
		
		//update
//		System.out.println(" * 1 ");
		String jsonUpdate="{\"id\":\"1\",\"first_name\":\"jack\",\"last_name\":\"sparrow\",\"middle_name\":\"captain\",\"sex\":\"male\",\"registred_address\":{\"contry\":\"ncontry\",\"region\":\"nmodified\",\"city\":\"ncity\",\"street\":\"nstreet\",\"house\":\"blackPearlShip\",\"flat\":\"nflat\"},\"actual_address\":{\"contry\":\"Updatencontry\",\"region\":\"Updatenmodified\",\"city\":\"Updatencity\",\"street\":\"Updatenstreet\",\"house\":\"UpdateblackPearlShip\",\"flat\":\"Updatenflat\"}}";
//		System.out.println(" * 2 ");
		mainController.update(jsonUpdate);
//		System.out.println("try findall");
//		System.out.println("show findall "+mainController.findall());
//		System.out.println(" * 3 ");
		Customer actual=null;
//		System.out.println(" * 4 ");
		List<Customer>templist=mainController.findall();
//		System.out.println(" * 5 ");
//		System.out.println("in list "+templist.size());
//		System.out.println(" * 6 ");
		actual=templist.get(0);
//		System.out.println(" * 7 ");
		assertEquals(expected.getFirst_name(), actual.getFirst_name());
		assertEquals(expected.getMiddle_name(), actual.getMiddle_name());
		assertEquals(expected.getLast_name(), actual.getLast_name());
		assertEquals(expected.getSex(), actual.getSex());
		assertEquals(expected.getRegistred_address().getContry(), actual.getRegistred_address().getContry());
		assertEquals(expected.getRegistred_address().getRegion(), actual.getRegistred_address().getRegion());
		assertEquals(expected.getRegistred_address().getCity(), actual.getRegistred_address().getCity());
		assertEquals(expected.getRegistred_address().getStreet(), actual.getRegistred_address().getStreet());
		assertEquals(expected.getRegistred_address().getHouse(), actual.getRegistred_address().getHouse());
		assertEquals(expected.getRegistred_address().getFlat(), actual.getRegistred_address().getFlat());
		assertEquals(expected.getActual_address().getContry(), actual.getActual_address().getContry());
		assertEquals(expected.getActual_address().getRegion(), actual.getActual_address().getRegion());
		assertEquals(expected.getActual_address().getCity(), actual.getActual_address().getCity());
		assertEquals(expected.getActual_address().getStreet(), actual.getActual_address().getStreet());
		assertEquals(expected.getActual_address().getHouse(), actual.getActual_address().getHouse());
		assertEquals(expected.getActual_address().getFlat(), actual.getActual_address().getFlat());
//		System.out.println(" * 8 last ");
		
		
		
	}

	@Test
	@Sql(value = {"classpath:sqlBeforeTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(value = {"classpath:sqlAfterTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	void testFind() {
		System.out.println("---testFind");
		Customer expected=new Customer();
		expected.setId(1);
		expected.setFirst_name("jack");
		expected.setMiddle_name("captain");
		expected.setLast_name("sparrow");
		expected.setSex("male");
		Address actualAddress1=new Address();
		actualAddress1.setId(1);
		actualAddress1.setContry("ncontry");
		actualAddress1.setRegion("nmodified");
		actualAddress1.setCity("ncity");
		actualAddress1.setStreet("nstreet");
		actualAddress1.setHouse("blackPearlShip");
		actualAddress1.setFlat("nflat");
		actualAddress1.setCreated(null);
		actualAddress1.setModified(null);
		Address registredAddress1=new Address();
		registredAddress1.setId(2);
		registredAddress1.setContry("ncontry");
		registredAddress1.setRegion("nmodified");
		registredAddress1.setCity("ncity");
		registredAddress1.setStreet("nstreet");
		registredAddress1.setHouse("blackPearlShip");
		registredAddress1.setFlat("nflat");
		registredAddress1.setCreated(null);
		registredAddress1.setModified(null);
		expected.setActual_address(actualAddress1);
		expected.setRegistred_address(registredAddress1);
		Customer result=mainController.find(1);
//		Customer resultNull=mainController.find(3);

		assertNotNull(result);
//		assertNull(resultNull);
		assertEquals(expected,result);
		assertThat(result).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	@Sql(value = {"classpath:sqlBeforeTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(value = {"classpath:sqlAfterTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	void findByFirstNameAndLastname() {
		System.out.println("---testFind");
		Customer expected=new Customer();
		expected.setId(1);
		expected.setFirst_name("jack");
		expected.setMiddle_name("captain");
		expected.setLast_name("sparrow");
		expected.setSex("male");
		Address actualAddress1=new Address();
		actualAddress1.setId(1);
		actualAddress1.setContry("ncontry");
		actualAddress1.setRegion("nmodified");
		actualAddress1.setCity("ncity");
		actualAddress1.setStreet("nstreet");
		actualAddress1.setHouse("blackPearlShip");
		actualAddress1.setFlat("nflat");
		actualAddress1.setCreated(null);
		actualAddress1.setModified(null);
		Address registredAddress1=new Address();
		registredAddress1.setId(2);
		registredAddress1.setContry("ncontry");
		registredAddress1.setRegion("nmodified");
		registredAddress1.setCity("ncity");
		registredAddress1.setStreet("nstreet");
		registredAddress1.setHouse("blackPearlShip");
		registredAddress1.setFlat("nflat");
		registredAddress1.setCreated(null);
		registredAddress1.setModified(null);
		expected.setActual_address(actualAddress1);
		expected.setRegistred_address(registredAddress1);
		
		Customer result=mainController.findByFirstNameAndLastname("jack", "sparrow");
		
		assertNotNull(result);
//		assertNull(resultNull);
		assertEquals(expected,result);
		assertThat(result).usingRecursiveComparison().isEqualTo(expected);
	}
	
}
