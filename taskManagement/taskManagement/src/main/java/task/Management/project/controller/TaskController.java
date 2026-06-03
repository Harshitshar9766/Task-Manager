package task.Management.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.Management.project.entity.Task;
import task.Management.project.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3001")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/{userId}")
    public ResponseEntity<Task> createTask(
            @RequestBody Task task,
            @PathVariable Long userId) {

        return new ResponseEntity<>(
                taskService.save(task, userId),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/user/{userId}")
    public List<Task> getAllTasks(@PathVariable Long userId) {
        return taskService.getAllByUser(userId);
    }


    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<?> getById( @PathVariable Long id, @PathVariable Long userId){
        Task task = taskService.getById(id, userId);
        if(task == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Task not found ");
        }
        return ResponseEntity.ok(task);
    }


    @GetMapping("/search/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title){

        List<Task> tasks = taskService.getByTitle(title);

        if (tasks == null || tasks.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No task found with title: " + title);
        }

        return ResponseEntity.ok(tasks);

    }


}
