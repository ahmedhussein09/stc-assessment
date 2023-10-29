package com.stc.assessment.model;

import lombok.Data;

@Data
public class ItemDto {
    private int id;
    private DiskType type;
    private String name;
    private int groupId;
}
