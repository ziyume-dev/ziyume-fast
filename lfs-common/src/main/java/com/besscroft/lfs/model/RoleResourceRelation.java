package com.besscroft.lfs.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 18:13
 */
@Entity
@Table(name = "auth_role_resource")
@Schema(title = "权限管理模块角色资源关系表")
@Data
public class RoleResourceRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 资源ID
     */
    private Long resourceId;

}
