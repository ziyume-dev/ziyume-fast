package dev.heming.fast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.fast.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 资源 Mapper
 * @Author Bess Croft
 * @Date 2023/5/25 19:52
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 查询资源列表
     * @param name 资源名称
     * @param url 资源路径
     * @param typeCode 资源类型 code
     * @param routeKey 路由分配 key
     * @return 资源列表
     */
    List<Resource> selectResourceList(@Param("name") String name,
                                      @Param("url") String url,
                                      @Param("typeCode") String typeCode,
                                      @Param("routeKey") String routeKey);

}
