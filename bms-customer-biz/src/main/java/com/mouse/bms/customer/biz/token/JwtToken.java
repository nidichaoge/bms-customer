package com.mouse.bms.customer.biz.token;

import org.apache.shiro.authc.AuthenticationToken;

import lombok.Data;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : JwtToken
 * @date : 2019/4/6 14:03
 * @description :
 */
@Data
public class JwtToken implements AuthenticationToken {

    private String token;

    @Override
    public Object getPrincipal() {
        return this.getToken();
    }

    @Override
    public Object getCredentials() {
        return this.getToken();
    }

}
