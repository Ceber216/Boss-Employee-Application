package pl.edu.project.aplication_aui.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BossRepository extends JpaRepository<Boss, UUID> {
}
