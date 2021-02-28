package top.lmqstudy.config;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.lmqstudy.util.AjaxResult;

/**
 * 定义一个全局异常---可预知的异常。
 *   通俗点讲：就是程序中各种数据校验失败后，既要让事务回滚，又返回200状态的消息给前端
 * @author Administrator
 * @version 1.0
 * @date 2021/2/28 15:21
 */
@RestControllerAdvice
public class GloableExecption {

    /**
     * 这个方法作用：拦截后台程序出现如下 【运行时异常】将会执行的方法
     * @return
     */
    @ExceptionHandler(ParamerterException.class)
    public AjaxResult runtimeException(Exception e){
        return AjaxResult.me().setSuccess(false).setMessage(e.getMessage());
    }

}
