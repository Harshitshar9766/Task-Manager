package task.Management.project.services;

import task.Management.project.entity.Task;

import java.util.List;

public interface TaskService {

    Task save(Task task, Long userId);

    List<Task> getAll();

    Task update(Long id, Task task);

    void delete(Long id);

    List<Task> getByTitle(String title);

    Task getById(Long id);

}
