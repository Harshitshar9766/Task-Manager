package task.Management.project.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.Management.project.entity.User;
import task.Management.project.repository.UserRepository;
import task.Management.project.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public User register(User user) {
        return userRepository.save(user);

    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
