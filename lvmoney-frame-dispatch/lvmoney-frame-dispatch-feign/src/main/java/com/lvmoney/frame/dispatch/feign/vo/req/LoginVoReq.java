package com.lvmoney.frame.dispatch.feign.vo.req;

import lombok.Data;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
public class LoginVoReq {
    private String username;
    private String password;
}
