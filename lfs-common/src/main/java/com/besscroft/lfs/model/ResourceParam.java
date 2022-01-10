package com.besscroft.lfs.model;

import com.besscroft.lfs.entity.AuthResource;
import lombok.Data;

import java.util.List;

/**
 * @Description 资源树封装
 * @Author Bess Croft
 * @Time 2021/12/12 16:55
 */
@Data
public class ResourceParam {

    /** 资源类别id */
    private Long id;

    /** 资源类别名称 */
    private String name;

    /** 可选状态 */
    private boolean disabled;

    private List<AuthResource> children;

}
