package com.stc.assessment.dao.repo;

import com.stc.assessment.dao.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepo extends JpaRepository<Permissions, Integer> {
    Permissions findByUserEmail(String userEmail);
}
