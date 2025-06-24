package com.example.ChatApp;

import com.example.ChatApp.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Import(TestSecurityConfig.class)
@TestPropertySource(properties = {
		"jwt.secret=testSecretKeyThatIs32CharactersOrMoreForHS256AlgorithmSecurity",
		"spring.r2dbc.url=r2dbc:h2:mem:///testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL",
		"spring.r2dbc.username=sa",
		"spring.r2dbc.password=",
		"spring.flyway.enabled=false",
		"logging.level.org.springframework.security=INFO"
})
class ChatAppApplicationTests {

	@Test
	void contextLoads() {
		// This test simply ensures that the Spring application context loads without exceptions
	}
}

