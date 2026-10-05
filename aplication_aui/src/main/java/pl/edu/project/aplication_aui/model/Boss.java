package pl.edu.project.aplication_aui.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "boss", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Employee> employees = new ArrayList<>();
}
