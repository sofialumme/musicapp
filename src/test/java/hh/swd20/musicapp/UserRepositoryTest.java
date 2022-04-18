package hh.swd20.musicapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.musicapp.domain.User;
import hh.swd20.musicapp.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void createNewUser() {
		User user = new User("testuser", "$2a$10$DHvFHEGEDAJ8I92tr4Pmwu4SPh2yW2q3IUFl2cVckwNBVpcdC8SYW", "USER");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		assertThat(urepository.findById((long) 1)).isNotEmpty();
		urepository.deleteById((long) 1);
		assertThat(urepository.findById((long) 1)).isEmpty();
	}
	
	@Test
	public void findUserByUsername() {
		User user = urepository.findByUsername("musicapp_admin");
		assertThat(user.getRole()).isEqualTo("ADMIN");
	}

}
