package co.instio.repo;
import co.instio.entity.Email;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSchedulerRepo extends JpaRepository<Email,Long> {
}
