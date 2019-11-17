package codari.simplewebapp.servlet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import codari.simplewebapp.beans.UserAccount;
import codari.simplewebapp.utils.MyUtils;
import codari.simplewebapp.utils.QueryUtils;

// static 메서드나 생성자는 mockito로는 mocking이 불가능하므로 
// 위의 상황에서 테스트를 하기 위해서는 powermock이 필요하다.

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {
	
	@Mock
	HttpServletRequest httpServletRequest;
	
	@Mock
	HttpServletResponse httpServletResponse;
	
	@Mock
	RequestDispatcher requestDispatcher;
	
	@Mock
	ServletContext servletContext;
	
	@Mock
	Connection connection;
	
	@Mock
	HttpSession httpSession;
	
	@Mock
	UserAccount userAccount;
	
	@Mock
	PreparedStatement preparedStatement;
	
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
		
		// arrange
		LoginServlet loginServlet= new LoginServlet();
		
		// act
		
		// assert
		assertNotNull(loginServlet);
	}
	
	@Test
	public void doGet_메서드_테스트() throws ServletException, IOException {
		
		// arrange
		when(loginServlet.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp")).thenReturn(requestDispatcher);
		
		// act
		loginServlet.doGet(httpServletRequest, httpServletResponse);
		
		// assert
		verify(requestDispatcher, times(1)).forward(httpServletRequest, httpServletResponse);
	}
	
	// static 메서드가 있어 mockito로 불가, powermock 사용필요함
	/*@Test
	public void doPost_rememberMe_Y_일때_테스트() throws ServletException, IOException, SQLException {
		
		// arrange
		when(httpServletRequest.getParameter("userName")).thenReturn("yu");
		when(httpServletRequest.getParameter("password")).thenReturn("yu123");
		when(httpServletRequest.getParameter("rememberMe")).thenReturn("Y");
		when(httpServletRequest.getContextPath()).thenReturn("SimpleWebApp");
		when(httpServletRequest.getSession()).thenReturn(httpSession);
		when(MyUtils.getStoredConnection(httpServletRequest)).thenReturn(connection);
		when(QueryUtils.findUser(connection, httpServletRequest.getParameter("userName"), httpServletRequest.getParameter("password"))).thenReturn(userAccount);
		
		// act
		loginServlet.doGet(httpServletRequest, httpServletResponse);
		
		// assert
		verify(requestDispatcher, times(1)).forward(httpServletRequest, httpServletResponse);
	}*/
}