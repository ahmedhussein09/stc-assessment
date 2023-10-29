package com.stc.assessment.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stc.assessment.model.DiskType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "TYPE")
    private DiskType type;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "PERMISSION_GROUP_ID", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private GroupPermission groupPermission;
}
