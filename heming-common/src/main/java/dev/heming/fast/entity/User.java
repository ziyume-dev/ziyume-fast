package dev.heming.fast.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 用户实体
 * @Author Bess Croft
 * @Date 2023/5/25 17:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("heming_user")
@Schema(title = "用户实体")
public class User extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "id", type = "Long")
    private Long id;

    /** 账号(用户名) */
    @TableField(value = "username")
    @Schema(title = "账号(用户名)", type = "String")
    private String username;

    /** 密码 */
    @TableField(value = "password", select = false)
    @Schema(title = "密码", type = "String")
    private String password;

    /** 头像(地址) */
    @TableField(value = "avatar")
    @Schema(title = "头像(地址)", type = "String")
    private String avatar;

    /** 邮箱 */
    @TableField(value = "email")
    @Schema(title = "邮箱", type = "String")
    private String email;

    /** 昵称 */
    @TableField(value = "name")
    @Schema(title = "昵称", type = "String")
    private String name;

    /** 手机 */
    @TableField(value = "telephone")
    @Schema(title = "手机", type = "String")
    private String telephone;

    /** 备注 */
    @TableField(value = "remark")
    @Schema(title = "备注", type = "String")
    private String remark;

    /** 排序 */
    @TableField(value = "sort")
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

    /** 帐号启用状态：0->禁用；1->启用 */
    @TableField(value = "status")
    @Schema(title = "帐号启用状态：0->禁用；1->启用", type = "Integer")
    private Integer status;

}
