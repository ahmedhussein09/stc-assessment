package com.stc.assessment.service;

import com.stc.assessment.dao.repo.ItemRepo;
import com.stc.assessment.model.ItemDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DiskService {

     ItemDto addSpace(ItemDto itemDto);
     ItemDto addFolder(String itemDto, int spaceId, String userEmail );
     ItemDto addFile(String itemDto, int folderId, String userEmail, MultipartFile file);
}
