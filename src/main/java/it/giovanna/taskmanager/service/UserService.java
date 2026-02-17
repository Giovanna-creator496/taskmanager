package it.giovanna.taskmanager.service;

import it.giovanna.taskmanager.model.User;
import it.giovanna.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new
                        RuntimeException("User not found"));
    }
        public boolean usernameExists(String username){
            return userRepository.existsByUsername(username);
    }
}
