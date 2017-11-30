package com.deloitte.hk.common.enums;

import java.util.ArrayList;
import java.util.List;

public enum ResponseEnum {
	SUCC("0000", "成功"),
	NO_AUTH("0001", "未授权"),
	INVALID_TOKEN("0002", "无效 token"),
	PWD_ERROR("0003", "用户名或密码错误"),
	SYS_ERROR("0004", "系统错误"),
	PARAM_ERROR("0005", "参数错误"),
	PARAM_NULL("0006", "参数为空"),
	PIC_NULL("0007", "图片内容为空"),
	STATUS_ERROR("0008", "状态错误"),
	LOCK_FAILER("0009", "锁定失败, 已被使用"),
	SERVICE_ERROR("1000", "服务为空"),
	OBJ_NOTEXIST("1001", "对象不存在"),
	OBJ_USED("1002", "对象已经在使用"),
	ODI_FAIL("1003", "ODI调用失败"),
	Hyperion_FAIL("1004", "Hyperion创建项目失败"),
	BIZ_TIP("9999", "用户提示"),
    ;

    /** 枚举值 */
    private final String code;

    /** 枚举描述 */
    private final String message;


    /**
     * 构造一个<code>SeqTypeEnum</code>枚举对象
     *
     * @param code
     * @param message
     * @param step
     */
    private ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Returns the code.
     */
    public String code() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String message() {
        return message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return SeqTypeEnum
     */
    public static ResponseEnum getByCode(String code) {
        for (ResponseEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<SeqTypeEnum>
     */
    public List<ResponseEnum> getAllEnum() {
        List<ResponseEnum> list = new ArrayList<ResponseEnum>();
        for (ResponseEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     *
     * @return List<String>
     */
    public List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (ResponseEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }
}
