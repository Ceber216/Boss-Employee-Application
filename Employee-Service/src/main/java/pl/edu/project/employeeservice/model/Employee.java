package pl.edu.project.employeeservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    @Id
    @Column(name = "employees_id")
    private UUID id;

    @Column(name = "employees_name")
    private String name;

    @Column(name = "employees_badge_number")
    private int badgeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="simplifiedboss_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SimplifiedBoss simplifiedBoss;
}

