package task.Management.project.services;

import task.Management.project.entity.User;

public interface UserService {
    User register(User user);
    User login(String email, String password);

}
