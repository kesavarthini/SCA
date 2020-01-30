package com.publicissapient.lloyds.orderservice.model;

import java.io.Serializable;

public class UserTokenResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public UserTokenResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
    public String getToken() {
        return this.jwttoken;
    }
}
