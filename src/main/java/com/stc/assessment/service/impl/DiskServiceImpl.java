package com.stc.assessment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stc.assessment.dao.entity.Files;
import com.stc.assessment.dao.entity.GroupPermission;
import com.stc.assessment.dao.entity.Item;
import com.stc.assessment.dao.entity.Permissions;
import com.stc.assessment.dao.repo.FileRepo;
import com.stc.assessment.dao.repo.GroupPermissionRepo;
import com.stc.assessment.dao.repo.ItemRepo;
import com.stc.assessment.dao.repo.PermissionsRepo;
import com.stc.assessment.model.BackendConstant;
import com.stc.assessment.model.DiskType;
import com.stc.assessment.model.ItemDto;
import com.stc.assessment.service.DiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiskServiceImpl implements DiskService {
    private final ItemRepo itemRepo;
    private final GroupPermissionRepo groupPermissionRepo;
    private final PermissionsRepo permissionsRepo;
    private final FileRepo fileRepo;
    private final ObjectMapper objectMappe;
    @Override
    public ItemDto addSpace(ItemDto itemDto) {
            itemDto.setGroupId(groupPermissionRepo.findByGroupName(BackendConstant.ADMIN).getId());
            itemDto.setType(DiskType.SPACE);
            Item item = itemRepo.save(toEntity(itemDto));
            return toDto(item);
    }

    @Override
    public ItemDto addFolder(String itemStr, int spaceId, String userEmail) {
        ItemDto itemDto;
        try {
            itemDto = objectMappe.readValue(itemStr, ItemDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Permissions permissions = permissionsRepo.findByUserEmail(userEmail);
       Item item = itemRepo.findById(spaceId).orElse(new Item());
       if(Objects.equals(permissions.getPermissionLevel(), BackendConstant.EDIT)
            && permissions.getGroupPermission().getId().equals(item.getGroupPermission().getId())){
           itemDto.setType(DiskType.FOLDER);
           return toDto(itemRepo.save(toEntity(itemDto)));
       }
        return null;
    }

    @Override
    public ItemDto addFile(String itemStr, int folderId,
                           String userEmail, MultipartFile file) {
        ItemDto itemDto;
        try {
            itemDto = objectMappe.readValue(itemStr, ItemDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Permissions permissions = permissionsRepo.findByUserEmail(userEmail);
        Item item = itemRepo.findById(folderId).orElse(new Item());
        if(Objects.equals(permissions.getPermissionLevel(), BackendConstant.EDIT)
                && permissions.getGroupPermission().getId().equals(item.getGroupPermission().getId())){
            itemDto.setType(DiskType.FILE);
            Item item1 = itemRepo.save(toEntity(itemDto));
            Files files = new Files();
            files.setItem(item1);
            try {
                files.setBinary_data(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fileRepo.save(files);
            return toDto(item1);
        }
        return null;
    }


    private ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setName(item.getName());
        itemDto.setId(item.getId());
        itemDto.setGroupId(item.getGroupPermission().getId());
        itemDto.setType(item.getType());
        return itemDto;
    }

    private Item toEntity(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setType(itemDto.getType());
        GroupPermission groupPermission = new GroupPermission();
        groupPermission.setId(itemDto.getGroupId());
        item.setGroupPermission(groupPermission);
        return item;
    }
}
