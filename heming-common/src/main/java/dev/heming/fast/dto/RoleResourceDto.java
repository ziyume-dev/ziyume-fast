package dev.heming.fast.dto;

import lombok.Data;

/**
 * @Description 角色资源关系对象
 * @Author Bess Croft
 * @Date 2023/5/26 16:23
 */
@Data
public class RoleResourceDto {

    private Long id;

    /** 角色 id */
    private Long roleId;

    /** 资源 id */
    private Long resourceId;

}
