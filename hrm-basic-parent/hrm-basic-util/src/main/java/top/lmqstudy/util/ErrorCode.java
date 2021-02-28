package top.lmqstudy.util;

/**
 * 定义一个枚举类来统一管理系统中所有的常量（错误异常）
 * <p>
 * 语法：
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/2/28 15:28
 */
public enum ErrorCode {
    //不可为空的错误码
    /*
        枚举类中，枚举中的每个方法支持自定义。
            CODE_100_NULL_COMPANYNAME(xxx,xxx)：这个处定义方法，可以理解为这个枚举类的
                    构造函数

     */
    CODE_100_NULL_COMPANYNAME(100, "公司名不可为空"),
    CODE_101_FORMAT_INVALID_PHONE(101, "无效的手机格式"),
    CODE_102_EXIST_COMPANYNAME(102, "公司名已经被注册"),
    CODE_103_EXIST_EMPLOYEE_USERNAME(103, "用户名不能重复"),

    CODE_104_EXIST_EMPLOYEE_USERNAME(104,"员工登录帐号不能为空");

    //错误码
    private int code;
    //错误信息
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
