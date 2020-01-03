package org.hexagonsi.event.controller.viewmodel;

public class ReturnResult<T> {
    protected int code;
    private String flag;
    private T data;

    public ReturnResult() {}

    public ReturnResult(int pCode) {
        this.code = pCode;
    }

    public ReturnResult(int pCode, T pData) {
        this.code = pCode;
        this.setData(pData);
    }

    public ReturnResult(int pCode, String pFlag, T pData) {
        this.code = pCode;
        this.flag = pFlag;
        this.setData(pData);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
