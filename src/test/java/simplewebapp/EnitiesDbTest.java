package simplewebapp;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import entities.Car;
import entities.Order;
import entities.User;

public class EnitiesDbTest {


	@Test
	public void test() {
		Car carEntity = new Car();
		Order orderEntity = new Order();
		User userEntity = new User();
		
		carEntity.setId(1);
		carEntity.setMark("mazda");
		carEntity.setCarClass("sedan");
		carEntity.setModel("cx-7");
		carEntity.setCost(200);
		
		orderEntity.setCarId(1);
		orderEntity.setId(2);
		orderEntity.setUserId(3);
		orderEntity.setBill(150);
		orderEntity.setstart_date(Date.valueOf("2018-11-07"));
		orderEntity.setFinish_date(Date.valueOf("2018-11-08"));
		orderEntity.setDriver("no");
		orderEntity.setStatus("completed");
		
		userEntity.setName("John");
		userEntity.setId(1);
		userEntity.setEmail("john@mail.com");
		userEntity.setPassword("Tnp753");
		userEntity.setRole("user");
		userEntity.setStatus("confirmed");
		
		assertNotNull("should not be null becouse object was initialized", carEntity);
		assertNotNull("should not be null becouse object was initialized", orderEntity);
		assertNotNull("should not be null becouse object was initialized", userEntity);
		
		assertEquals(1, carEntity.getId());
		assertEquals("mazda", carEntity.getMark());
		assertEquals("sedan", carEntity.getCarClass());
		assertEquals("cx-7", carEntity.getModel());
		assertEquals(200, carEntity.getCost());
		
		assertEquals(1,orderEntity.getCarId());
		assertEquals(2, orderEntity.getId());
		assertEquals(3, orderEntity.getUserId());
		assertEquals(150, orderEntity.getBill());
		assertEquals(Date.valueOf("2018-11-07"), orderEntity.getstart_date());
		assertEquals(Date.valueOf("2018-11-08"), orderEntity.getFinish_date());
		assertEquals("no", orderEntity.getDriver());
		assertEquals("completed", orderEntity.getStatus());
		
		assertEquals("John", userEntity.getName());
		assertEquals(1, userEntity.getId());
		assertEquals("john@mail.com", userEntity.getEmail());
		assertEquals("Tnp753", userEntity.getPassword());
		assertEquals("user", userEntity.getRole());
		assertEquals("confirmed", userEntity.getStatus());
		
		assertEquals("Car [id=1, model=cx-7, mark=mazda, carClass=sedan, cost=200]", carEntity.toString());
		assertEquals("User [id=1, name=John, password=Tnp753, role=user, email=john@mail.com, status=confirmed]", userEntity.toString());
		assertEquals("Order [id=2, userId=3, carId=2, bill=150, status=completed, driver=no, start_date=2018-11-07, finish_date=2018-11-08]", orderEntity.toString());

		
	}

}
