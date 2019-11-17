package codari.simplewebapp.servlet;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LoginServletTest {
	
	@Test
	public void 생성자_테스트() {
		
		// arrange
		LoginServlet loginServlet= new LoginServlet();
		
		// act
		
		// assert
		assertNotNull(loginServlet);
	}
}