package com.lvmoney.frame.blockchain.webase.wallet.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.wallet.api.ao
 * 版本信息: 版本1.0
 * 日期:2021/6/28
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/28 11:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveAo extends Wallet {
    private static final long serialVersionUID = -197343902549858841L;
    /**
     * 授权公钥地址
     */
    private String apender;
    /**
     * 积分数量
     */
    private String value;
}
