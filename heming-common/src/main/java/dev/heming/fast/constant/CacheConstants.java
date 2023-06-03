package dev.heming.fast.constant;

/**
 * @Description 缓存常量
 * @Author Bess Croft
 * @Date 2023/5/25 20:07
 */
public interface CacheConstants {

    /** 用户信息 */
    String USER = "user:userId:";

    /** Redis 缓存权限规则 key */
    String PERMISSION_RULES_KEY = "auth:roleResourceMap";

    /** 存储权限前缀 */
    String AUTHORITY_PREFIX = "ROLE_";

}
