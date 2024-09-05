package co.instio.repo;

import co.instio.entity.SchedulerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepo extends JpaRepository<SchedulerDetails,Long> {
}
