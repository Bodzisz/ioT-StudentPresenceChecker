package pwr.studentpresencesystem;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pwr.studentpresencesystem.entity.Gender;
import pwr.studentpresencesystem.entity.Role;
import pwr.studentpresencesystem.entity.User;
import pwr.studentpresencesystem.repository.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Component
public class DataInitializer {

    private UserRepository userRepository;
    @Value("${add-sample-users-data}")
    private boolean addSampleUserData;
    private PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void initUsers() {
        if(addSampleUserData || userRepository.count() == 0) {
            userRepository.saveAll(getUsersData());
        }
    }

    private List<User> getUsersData() {
        return List.of(
                new User(0, "teacher", passwordEncoder.encode("password"), "Robert",
                        "Kubica", Gender.M, Role.TEACHER, Date.valueOf(LocalDate.of(1973, 1, 12)),
                        "teacher@pwr.edu.pl", null, null, null),
                new User(0, "user1", passwordEncoder.encode("password"), "Mariusz",
                        "Pudzianowski", Gender.M, Role.STUDENT, Date.valueOf(LocalDate.of(2000, 4, 20)),
                        "123456@student.pwr.edu.pl", "123456", "56414964923", new HashSet<>()),
                new User(0, "user2", passwordEncoder.encode("password"), "Robert",
                        "Maklowicz", Gender.M, Role.STUDENT, Date.valueOf(LocalDate.of(2001, 6, 20)),
                        "123457@student.pwr.edu.pl", "123457", "1022787718182", new HashSet<>())

        );
    }

}
