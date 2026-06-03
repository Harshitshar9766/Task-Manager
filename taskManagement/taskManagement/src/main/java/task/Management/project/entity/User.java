package task.Management.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;

}
