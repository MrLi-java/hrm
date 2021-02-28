package top.lmqstudy.config;


import top.lmqstudy.util.ErrorCode;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/2/28 15:46
 */
// 自定义的运行时异常类
// 这种写法，自定义异常类，是支持扩展
public class ParamerterException extends RuntimeException {

    public ParamerterException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
