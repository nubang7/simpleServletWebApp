package codari.simplewebapp.servlet;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import codari.simplewebapp.utils.QueryUtils;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {

	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	RequestDispatcher requestDispatcher;
	
	@Mock
	ServletContext servletContext;
	
	@Mock
	Connection connection;
	
	@InjectMocks
	LoginServlet loginServlet;
	
	@Before
	public void setUp() {
		initMocks(this);
		loginServlet= new LoginServlet() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};
	}
	
	@Test
	public void 생성자_테스트() {
		LoginServlet loginServlet= new LoginServlet();
		assertNotNull(loginServlet);
	}

	@Test
	public void doGet_테스트() throws ServletException, IOException {
		when(loginServlet.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp")).thenReturn(requestDispatcher);
		loginServlet.doGet(request, response);
		verify(requestDispatcher, times(1)).forward(request, response);
	}
}
