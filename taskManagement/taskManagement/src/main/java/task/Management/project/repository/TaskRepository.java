package task.Management.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.Management.project.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> findByTitleContainingIgnoreCase(String title);

    List<Task> findAllByUserId(Long userId);

    Optional<Task> findByIdAndUserId(Long id, Long userId);
}
