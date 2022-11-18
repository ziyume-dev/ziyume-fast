package com.besscroft.lfs.system.repository;

import com.besscroft.lfs.entity.AuthResourceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/10 16:07
 */
public interface ResourceCategoryRepository extends JpaRepository<AuthResourceCategory, Long>, JpaSpecificationExecutor<AuthResourceCategory> {

    /**
     * 批量逻辑删除资源类别
     * @param ids 资源类别id集合
     */
    @Modifying
    @Query(value = """
        UPDATE 
            auth_resource_category 
        SET 
            del = 0 
        WHERE 
            id IN :ids
    """, nativeQuery = true)
    void deleteAllById(List<Long> ids);

}
