package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemlab.base.wapper.RequestObject;
import com.systemlab.help_desk.entity.User;
import com.systemlab.help_desk.repository.hibernate.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/spring-context.xml",  
			    "classpath:/hibernate/hibernate-context.xml"})
public class TestUser {
	
	private User user;
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setUser() {
		user = new User();
		user.setName("Carlos");
		user.setPaternal_name("Salazar");
		user.setMaternal_name("Sanchez");
		user.setPassword("Carlos97S");
		user.setDocument_type("1");
		user.setDocument_number("42726851");
		user.setEmail("csalazar@gmail.com");
		user.setRole("1");
		user.setState("1");
		user.setUserRoleId(1);
		
		try {
			String account = userRepository.get(1, new RequestObject()).getAccount(); 
			user.setAccount(account);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void validateUserAddAccountIfExistTest() {
		try {
			int result = userRepository.add(user);
			assertEquals("Usuario creado con cuenta existente.",result,-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
