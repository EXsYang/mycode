package com.hspedu.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangda
 * @create 2023-10-15-13:49
 * @description:
 * 异常处理的优先级
 * 局部异常 > 全局异常 > SimpleMappingExceptionResolver > tomcat 默认机制
 *
 * 如果类上标注了@ControllerAdvice,就是一个全局异常处理类
 * advice:建议;意见;忠告;劝告
 *
 * 注解@ControllerAdvice(basePackages = "")的basePackages属性
 * 可以指定对那个包下抛出的异常进行全局处理，
 * 如果没有指定，则默认处理所有的包下抛出的异常
 *
 */
@ControllerAdvice
// @ControllerAdvice(basePackages = "")
public class MyGlobalException {


    /**
     * 老师解读
     * 1. 全局异常就不管是哪个Handler抛出的异常，都可以捕获 , @ExceptionHandler({异常类型})
     * 2. 这里老师处理的全局异常是NumberFormatException.class,ClassCastException.class
     * 3. Exception ex 接收抛出的异常对象
     * 4. 注解 @ExceptionHandler({...}) 中的这些异常都是由此方法globalException共同处理的，而不是只选择其中一个。
     * @return
     */
    @ExceptionHandler({NumberFormatException.class,ClassCastException.class,AgeException.class})
    public String globalException(Exception ex, HttpServletRequest request){
        System.out.println("全局异常处理-" + ex.getMessage());
        //如何将异常的信息带到下一个页面.
        request.setAttribute("reason", ex.getMessage());
        return "exception_mes";
    }


    // 上面的精准异常匹配如果没有匹配到，就会走这里顶级异常处理
    // 注意优先走上面的精准异常匹配 globalException ！
    // “如果抛出的异常不匹配上面定义的具体异常类型，则会被此方法（处理Throwable.class）捕获。
    // ”这是因为Throwable是所有错误和异常的超类，所以此方法可以捕获所有未被上面方法捕获的异常和错误。
    @ExceptionHandler({Throwable.class})
    public String globalException2(Exception ex, HttpServletRequest request){
        System.out.println("全局异常处理2-" + ex.getMessage());
        //如何将异常的信息带到下一个页面.
        request.setAttribute("reason2", ex.getMessage());
        return "exception_mes2";
    }

    /**
     * 解释“如果抛出的异常不匹配上面定义的具体异常类型，则会被此方法（处理Throwable.class）捕获。
     * 这是因为Throwable是所有错误和异常的超类，所以此方法可以捕获所有未被上面方法捕获的异常和错误。”，为什么会这样呢？和方法的声明顺序有关还是其他别的什么原因？？
     *
     * GPT:
     * 在Java中，`Throwable`类是所有错误和异常的最顶层父类。这意味着，Java中的所有异常和错误类型都是`Throwable`的子类。在Spring框架的全局异常处理中，当你使用`@ExceptionHandler`注解来标注方法时，你告诉Spring当发生特定类型的异常时，应该调用这个方法。
     *
     * 这里的行为和方法的声明顺序无关，而是与异常的类型匹配有关：
     *
     * 1. **类型匹配**：当一个异常被抛出时，Spring会查找用`@ExceptionHandler`注解的方法，并检查这些方法所指定的异常类型。Spring将选择与抛出的异常类型最匹配的方法。"最匹配"意味着异常类型直接相同或者最接近的父类。
     *
     * 2. **多个处理器**：如果你有多个`@ExceptionHandler`方法，每个处理不同类型的异常，Spring会选择与抛出的异常类型最为匹配的那个处理器。例如，如果一个方法标注为处理`NumberFormatException`，另一个标注为处理`Exception`，那么当抛出`NumberFormatException`时，会调用第一个方法。
     *
     * 3. **使用Throwable作为异常类型**：如果你有一个`@ExceptionHandler(Throwable.class)`，这意味着它能够捕获所有类型的异常和错误，因为所有的异常和错误都是`Throwable`的子类。如果没有更具体的异常处理器匹配抛出的异常类型，这个处理器就会被使用。这不是因为方法的声明顺序，而是因为没有其他更具体的异常类型与抛出的异常相匹配。
     *
     * 因此，当一个异常抛出时，Spring会首先尝试找到最具体的异常匹配。如果没有找到具体的匹配，它会继续查找直到找到`Throwable`的处理器。这就是为什么`@ExceptionHandler(Throwable.class)`能够作为一个“后备”来捕获所有未被其他处理器捕获的异常和错误。这种机制确保了异常总能被适当地处理，即使没有为它们配置具体的处理器。
     */

}
