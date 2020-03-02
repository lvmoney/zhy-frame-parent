package com.zhy.frame.cache.lock.vo.resp;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/9
 * Copyright 四川******科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionRespVo implements Serializable {
    private static final long serialVersionUID = -2366911448272008882L;
    /**
     * 开始
     */
    private long start;
    /**
     * 结束
     */
    private long end;

    /**
     * 对应值
     */
    private List<String> num;


}
