package pl.edu.project.aplication_aui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.project.aplication_aui.model.Boss;

import java.util.UUID;

@Repository
public interface BossRepository extends JpaRepository<Boss, UUID> {
}
