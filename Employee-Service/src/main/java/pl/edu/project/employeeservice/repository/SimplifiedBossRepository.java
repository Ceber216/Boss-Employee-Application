package pl.edu.project.employeeservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.project.employeeservice.model.SimplifiedBoss;

import java.util.UUID;

@Repository
public interface SimplifiedBossRepository extends JpaRepository <SimplifiedBoss, UUID> {

}
