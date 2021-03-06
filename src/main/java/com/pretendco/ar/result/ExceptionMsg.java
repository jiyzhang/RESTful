
package com.pretendco.ar.result;

//实现响应的枚举类
public enum ExceptionMsg {
    SUCCESS("200", "操作成功"),
    FAILED("999999", "操作失败"),
    NORECORDFOUND("000201", "没有找到对应的记录"),
    MORETHANONERECFOUND("000202", "找到多条相应记录"),
    ParamError("000001", "参数错误！"),
    FileEmpty("000400", "上传文件为空"),
    LimitPictureSize("000401", "图片大小必须小于2M"),
    LimitPictureType("000402", "图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'");

    private ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}

