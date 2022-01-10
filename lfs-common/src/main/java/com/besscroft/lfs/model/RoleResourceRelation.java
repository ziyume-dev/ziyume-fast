package com.besscroft.lfs.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 18:13
 */
@Entity
@Table(name = "auth_role_resource_relation")
@ApiModel(value = "权限管理模块角色资源关系表")
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
