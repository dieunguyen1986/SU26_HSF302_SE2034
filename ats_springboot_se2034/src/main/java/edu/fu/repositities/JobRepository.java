package edu.fu.repositities;

import edu.fu.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
    Boolean existsByTitle(String title);
}
