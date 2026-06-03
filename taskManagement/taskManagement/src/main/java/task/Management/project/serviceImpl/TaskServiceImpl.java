package task.Management.project.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.Management.project.entity.Task;
import task.Management.project.entity.User;
import task.Management.project.repository.TaskRepository;
import task.Management.project.repository.UserRepository;
import task.Management.project.services.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Task save(Task task, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        task.setUser(user);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllByUser(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }


    @Override
    public Task update(Long id, Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setCompleted(task.isCompleted());

        return taskRepository.save(existingTask);
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Task getById(Long id,Long userId) {
        return taskRepository.findByIdAndUserId(id, userId).orElse(null);
    }
}
