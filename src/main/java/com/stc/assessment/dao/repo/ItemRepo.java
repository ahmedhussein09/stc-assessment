package com.stc.assessment.dao.repo;

import com.stc.assessment.dao.entity.Item;
import com.stc.assessment.service.DiskService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
    Item findByType(DiskService diskService);
}
