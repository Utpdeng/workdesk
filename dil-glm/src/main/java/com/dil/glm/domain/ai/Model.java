package com.dil.glm.domain.ai;

public enum Model {

    GLM_4_FLASH("glm-4-flash", "适用简单任务，速度最快，具有128k上下文");

    private final String code;
    private final String info;

    Model(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

}
