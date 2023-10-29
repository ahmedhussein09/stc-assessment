package com.stc.assessment.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "permissions")
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "PERMISSION_LEVEL")
    private String permissionLevel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private GroupPermission groupPermission;
}
