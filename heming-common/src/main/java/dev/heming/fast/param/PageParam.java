package dev.heming.fast.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 分页参数
 * @Author Bess Croft
 * @Date 2023/5/29 10:06
 */
@Data
public class PageParam {

    @NotNull(message = "pageNum不能为空")
    @Min(value = 1, message = "pageNum不能小于1")
    @Schema(title = "页码", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pageNum;

    @NotNull(message = "pageSize不能为空")
    @Min(value = 10, message = "pageSize不能小于10")
    @Schema(title = "页大小", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pageSize;

}
