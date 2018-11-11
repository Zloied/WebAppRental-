package simplewebapp;

import static org.junit.Assert.*;

import org.junit.Test;

import validation.Validation;

public class ValidationTest {

	@Test
	public void testGetValid() {
		String login = "Banzzy";
		boolean tryLg= Validation.getValid(login);
		assertTrue("should be true becouse inputed login matches the pattern",tryLg);
	}

	@Test
	public void testGetValidEmail() {
		boolean tryEmail = Validation.getValidEmail("banz@gmal.com");
		assertTrue("should be true becouse inputed email matches the pattern",tryEmail);
	}

	@Test
	public void testGetRegValid() {
		String login = "Banzzy";
		String password1 = "Asd123";
		String password2 = "Asd123";
		boolean tryFull = Validation.getRegValid(login, password1, password2);
		assertTrue("should be true becouse inputed login, password1 and password2 match the pattern", tryFull);
	}

}
