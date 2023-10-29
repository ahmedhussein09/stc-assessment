package com.stc.assessment.dao.repo;

import com.stc.assessment.dao.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<Files, Integer> {
}
