package com.lvmoney.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.frame.cloud.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2020/12/10
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2020/12/10 14:06  
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resources implements Serializable {
    private static final long serialVersionUID = 4119172024684791785L;
    private Limits limits;
    private Requests requests;
}
