package edu.fu.repositities;

import edu.fu.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor {
    Boolean existsByTitle(String title);

    @Query("FROM Job j WHERE j.status = 'PUBLISHED'")
    Page<Job> findPublishedJobByPage(Pageable pageable);

}
