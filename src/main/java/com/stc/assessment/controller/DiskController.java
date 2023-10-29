package com.stc.assessment.controller;

import com.stc.assessment.model.ItemDto;
import com.stc.assessment.service.DiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class DiskController {

    private final DiskService diskServiceImpl;

    @PostMapping("space")
    public ResponseEntity<ItemDto> createSpace(@RequestBody ItemDto itemDto){
        return new ResponseEntity<>(diskServiceImpl.addSpace(itemDto), HttpStatus.CREATED);
    }

    @PostMapping("folder")
    public ResponseEntity<ItemDto> createFolder(@RequestPart("record") String itemStr,
                                                @RequestParam("spaceId") int spaceId, @RequestParam("userEmail") String userEmail){
        return new ResponseEntity<>(diskServiceImpl.addFolder(itemStr, spaceId, userEmail), HttpStatus.CREATED);
    }
    @PostMapping("folder")
    public ResponseEntity<ItemDto> createFile(@RequestPart("record") String itemStr,
                                              @RequestParam("folderId") int folderId,
                                              @RequestParam("userEmail") String userEmail,
                                              @RequestParam("file") MultipartFile file ){
        return new ResponseEntity<>(diskServiceImpl.addFile(itemStr, folderId, userEmail, file), HttpStatus.CREATED);
    }

}
