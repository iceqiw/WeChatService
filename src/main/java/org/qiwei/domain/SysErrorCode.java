package org.qiwei.domain;

/**
 * Created by admin on 2017/7/10.
 */
public class SysErrorCode {
    private Long errorCode;
    private String errorMsg;


    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
