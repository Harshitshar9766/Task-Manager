package task.Management.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.Management.project.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> findByTitleContainingIgnoreCase(String title);

}
