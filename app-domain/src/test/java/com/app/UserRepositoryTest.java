package com.app;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.app.entity.AppUser;
import com.app.repository.UserRepository;

import javassist.NotFoundException;


//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
@ActiveProfiles
@ContextConfiguration(locations = "{com.app.AppSpringBoot}")
class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	
	@Test
	void testFindByUsername() {
	    //given
		AppUser user = AppUser.builder()
				.username("sami").password("sami").email("sami@sami").build();
		//when
		repo.save(user);
		
		//then
		 Assertions.assertThat(user.getId()).isGreaterThan(0);
	}

}
