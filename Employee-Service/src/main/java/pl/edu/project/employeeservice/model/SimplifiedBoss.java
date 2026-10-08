package pl.edu.project.employeeservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "simplifiedboss")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedBoss {
    @Id
    @Column(name = "simplifiedboss_id")
    private UUID id;

    @OneToMany(mappedBy = "simplifiedBoss",cascade = CascadeType.REMOVE)
    private List<Employee> employees;
}
