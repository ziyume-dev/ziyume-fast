package dev.heming.fast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.fast.entity.ResourceType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 资源类型 Mapper
 * @Author Bess Croft
 * @Date 2023/5/25 19:52
 */
public interface ResourceTypeMapper extends BaseMapper<ResourceType> {

    /**
     * 查询资源类型列表
     * @param name 名称
     * @param code 编码
     * @return 资源类型列表
     */
    List<ResourceType> selectResourceTypeList(@Param("name") String name,
                                              @Param("code") String code);

}
