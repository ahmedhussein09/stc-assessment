package com.stc.assessment.dao.repo;

import com.stc.assessment.dao.entity.GroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPermissionRepo extends JpaRepository<GroupPermission, Integer> {
    GroupPermission findByGroupName(String admin);
}
