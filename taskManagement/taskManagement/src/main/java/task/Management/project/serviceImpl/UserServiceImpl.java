package task.Management.project.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import task.Management.project.entity.User;
import task.Management.project.repository.UserRepository;
import task.Management.project.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public User register(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User already exists with this email"
            );
        }
        return userRepository.save(user);
    }


    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("EMAIL FROM POSTMAN: " + email);
        System.out.println("PASSWORD FROM POSTMAN: " + password);
        System.out.println("PASSWORD FROM DB: " + user.getPassword());

        if (!user.getPassword().trim().equals(password.trim())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
