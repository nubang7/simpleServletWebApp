package codari.simplewebapp.servlet;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import codari.simplewebapp.beans.UserAccount;
import codari.simplewebapp.utils.MyUtils;
import codari.simplewebapp.utils.QueryUtils;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({QueryUtils.class, MyUtils.class})
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
	
	@Mock
	UserAccount userAccount;
	
	@Mock
	HttpSession httpSession;
	
	@InjectMocks
	LoginServlet loginServlet= new LoginServlet();
	
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
	
	@Test
	public void doPost_rememberMe_Y_일때_테스트() throws ServletException, IOException, SQLException {
		PowerMockito.mockStatic(QueryUtils.class);
		PowerMockito.mockStatic(MyUtils.class);
		
		when(request.getParameter("userName")).thenReturn("yu");
		when(request.getParameter("password")).thenReturn("yu123");
		when(request.getParameter("rememberMe")).thenReturn("Y");
		when(request.getContextPath()).thenReturn("SimpleWebApp");
		when(request.getSession()).thenReturn(httpSession);
		when(MyUtils.getStoredConnection(request)).thenReturn(connection);
		when(QueryUtils.findUser(connection, request.getParameter("userName"), request.getParameter("password"))).thenReturn(userAccount);
		loginServlet.doPost(request, response);
		verify(response, times(1)).sendRedirect("SimpleWebApp/userInfo");
	}
	
	@Test
	public void doPost_userName_NULL_일때_테스트() throws ServletException, IOException, SQLException {
		PowerMockito.mockStatic(QueryUtils.class);
		PowerMockito.mockStatic(MyUtils.class);
		when(loginServlet.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp")).thenReturn(requestDispatcher);
		when(request.getParameter("userName")).thenReturn(null);
		when(request.getParameter("password")).thenReturn("yu123");
		when(request.getParameter("rememberMe")).thenReturn("Y");
		when(request.getContextPath()).thenReturn("SimpleWebApp");
		when(request.getSession()).thenReturn(httpSession);
		when(MyUtils.getStoredConnection(request)).thenReturn(connection);
		when(QueryUtils.findUser(connection, request.getParameter("userName"), request.getParameter("password"))).thenReturn(userAccount);
		loginServlet.doPost(request, response);
		verify(requestDispatcher, times(1)).forward(request, response);
	}
	
	@Test
	public void privateField() {
		
		// arrange
		Whitebox.<String>setInternalState(loginServlet, "privateField", 3);
	    int result = Whitebox.<Integer>getInternalState(loginServlet, "privateField");
	    
	    // act
	    
	    // assert
	    Assert.assertEquals(3, result);
	}
	
	@Test
	public void privateMethod() throws Exception {
		
		// arrange
		PowerMockito.mockStatic(MyUtils.class);
		PowerMockito.doNothing().when(MyUtils.class, "storeLoginedUser", httpSession, userAccount);
	    // act
		boolean result = Whitebox.<Boolean>invokeMethod(loginServlet, "privateMethod", request, response);
	    
	    // assert
	    Assert.assertEquals(true, result);
	}
}