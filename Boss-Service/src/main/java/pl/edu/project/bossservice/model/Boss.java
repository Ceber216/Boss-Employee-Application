package pl.edu.project.bossservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "bosses")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Boss {
    @Id
    @Column(name = "bosses_id")
    private UUID id;

    @Column(name = "bosses_name")
    private String name;

    @Column(name = "bosses_salary")
    private int salary;

}
