package com.lvmoney.frame.dispatch.feign.vo.resp;

import lombok.Data;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
public class LoginVoResp {
    private String orgid;
    private String token;
    private String userId;
    private String username;
}