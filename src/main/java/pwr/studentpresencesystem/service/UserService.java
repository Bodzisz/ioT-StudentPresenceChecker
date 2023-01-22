package pwr.studentpresencesystem.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.studentpresencesystem.entity.User;
import pwr.studentpresencesystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> findUserByCardNumber(String cardNumber) throws EntityNotFoundException {
        return userRepository.findByCardNumber(cardNumber);
    }

    public Optional<User> findUserByLogin(final String login) {
        return userRepository.findByLogin(login);
    }
}
