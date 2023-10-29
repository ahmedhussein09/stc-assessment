package com.stc.assessment.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "group_permission")
public class GroupPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupPermission", cascade = CascadeType.REMOVE)
    private List<Permissions> permissionsList;
}
