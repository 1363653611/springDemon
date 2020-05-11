package com.zbcn.common.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 参数无效项
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParameterInvalidItem {

    /**
     * 无效字段的名称
     */
    private String fieldName;

    /**
     * 错误信息
     */
    private String message;
}
