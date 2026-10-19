package org.MY_APP.main.repository;

import org.MY_APP.main.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_returnsUser_whenExists() {
        User u = new User("a@test.com", "1234", "Alex");
        userRepository.save(u);

        User found = userRepository.findByEmail("a@test.com");

        assertNotNull(found);
        assertEquals("a@test.com", found.getEmail());
    }

    @Test
    void findByEmail_returnsNull_whenMissing() {
        User found = userRepository.findByEmail("missing@test.com");
        assertNull(found);
    }

    @Test
    void findTop10ByOrderByTotalQuizScoreDesc_returnsSortedTop10() {
        for (int i = 1; i <= 12; i++) {
            User u = new User("u" + i + "@test.com", "p", "U" + i);
            u.setTotalQuizScore(i * 10);
            userRepository.save(u);
        }

        List<User> top = userRepository.findTop10ByOrderByTotalQuizScoreDesc();

        assertEquals(10, top.size());
        assertEquals(120, top.get(0).getTotalQuizScore());
        assertEquals(30, top.get(9).getTotalQuizScore());
    }
}
