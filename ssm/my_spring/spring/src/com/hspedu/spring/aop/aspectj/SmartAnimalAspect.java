package com.hspedu.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author yangda
 * @description:  切面类 , 类似于我们以前自己写的MyProxyProvider,但是功能强大很多
 *
 * @create 2023-09-08-17:09
 */
@Order(value = 2)//表示该切面类执行的顺序, value的值越小, 优先级越高
@Aspect  //表示是一个切面类[底层切面编程的支撑(动态代理+反射+动态绑定...)]
@Component  //会注入SmartAnimalAspect到容器
public class SmartAnimalAspect {

    //定义一个切入点, 在后面使用时可以直接引用, 提高了复用性
    @Pointcut(value = "execution(public float SmartDog.getSum(float, float))")
    public void myPointCut(){

    }



    //希望将showBeginLog方法切入到SmartDog-getSum前执行-前置通知
    /**
     * 老师解读
     * 1. @Before 表示前置通知:即在我们的目标对象执行方法前执行
     * 2. value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float)
     * 指定切入到哪个类的哪个方法  形式是: 访问修饰符 返回类型 全类名.方法名(形参列表)
     * 3. showBeginLog方法可以理解成就是一个切入方法, 这个方法名是可以程序员指定  比如:showBeginLog
     * 4. JoinPoint joinPoint 在底层执行时，由AspectJ切面框架， 会给该切入方法传入 joinPoint对象
     * , 通过该方法，程序员可以获取到 相关信息
     * 5. 只有写了注解 @Before 后 才会真正启用动态代理的那套机制 ioc.getBean() 才会返回
     *    代理对象 ，如果没有任何一个注解，既没有切入任何一个方法 ， ioc.getBean()
     *    仍然是按照OOP返回原来的对象，而不返回代理对象
     * @param joinPoint
     */
    //@Before(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float))")
    // 切面类和要被切入的类(切入表达式中的类SmartDog)在同一个包 可以省略包名 直接写简单类名
    //@Before(value = "execution(public float SmartDog.getSum(float, float))")
    //@Before(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.*(..))")
    //@Before(value = "execution(* *.*(..))") // 这里也会影响到 同一个配置文件中配置的 homework 包下的类的输出信息！
    //下面这种方式使用通配符 也生效
    //@Before(value = "execution(public void com.hspedu.spring.aop.homework.*.work())")
    //这里我们使用定义好的切入点
    @Before(value = "myPointCut()")
    public void showBeginLog(JoinPoint joinPoint){
        //通过连接点对象joinPoint 可以获取方法签名
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showBeginLog()[使用的myPointCut()]-方法执行前-日志-方法名-" + signature.getName() + "-参数 "
                + Arrays.asList(joinPoint.getArgs())); //这里从AOP看，就是一个横切关注点-前置通知
    }

    //返回通知：即把showSuccessEndLog方法切入到目标对象方法正常执行完毕后的地方
    //老韩解读
    //1. 如果我们希望把目标方法执行的结果，返回给切入方法
    //2. 可以再 @AfterReturning 增加属性 , 比如 returning = "res"
    // 类似于切入点要执行的方法返回的值赋给了变量"res" 给返回值用res进行了接收 所以，
    // 下面的形参位置(JoinPoint joinPoint,,Object res)里面也要用 res接收传递的值才行
    //3. 同时在切入方法增加 Object res
    //4. 注意: returning = "res" 和 Object res 的 res名字一致
    //@AfterReturning(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float))", returning = "res")
    //使用切入点
    @AfterReturning(value = "myPointCut()", returning = "res")
    public void showSuccessEndLog(JoinPoint joinPoint, Object res) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showSuccessEndLog()[使用的myPointCut()]-方法执行正常结束-日志-方法名-" + signature.getName() + " 返回的结果是=" + res);
    }

    //异常通知：即把showExceptionLog方法切入到目标对象方法执行发生异常的的catch{}
    //@AfterThrowing(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float))", throwing = "throwable")
    //直接使用切入点表达式
    @AfterThrowing(value = "myPointCut()", throwing = "throwable")
    public void showExceptionLog(JoinPoint joinPoint, Throwable throwable) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showExceptionLog()[使用的myPointCut()]-方法执行异常-日志-方法名-" + signature.getName() + " 异常信息=" + throwable);
    }

    //最终通知：即把showFinallyEndLog方法切入到目标方法执行后(不管是否发生异常,都要执行 finally{})
    //@After(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float))")
    //直接使用切入点
    @After(value = "myPointCut()")
    public void showFinallyEndLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showFinallyEndLog()[使用的myPointCut()]-方法最终执行完毕-日志-方法名-" + signature.getName());
    }

    //新的前置通知
    //@Before(value = "execution(public void com.hspedu.spring.aop.aspectj.Phone.work()) || execution(public void com.hspedu.spring.aop.aspectj.Camera.work())")
    //public void hi(JoinPoint joinPoint) {
    //    Signature signature = joinPoint.getSignature();
    //    System.out.println("切面类的hi()-执行的目标方法-" + signature.getName());
    //}

    //切入表达式也可以指向接口的方法, 这时切入表达式会对实现了接口的类/对象生效
    //比如下面我们是对UsbInterface 切入，那么对实现类Phone 和 Camera对象都作用了
    @Before(value = "execution(public void com.hspedu.spring.aop.homework.UsbInterface.work())")
    public void hi(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的hi()-执行的目标方法-" + signature.getName());
    }

    //给Car配置一个前置通知
    //@Before(value = "execution(public void Car.run())")
    public void ok1(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok1()-执行的目标方法-" + signature.getName());

    }

    //返回通知
    //@AfterReturning(value = "execution(public void Car.run())")
    public void ok2(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok2()-执行的目标方法-" + signature.getName());

    }

    //异常通知
    //@AfterThrowing(value = "execution(public void Car.run())")
    public void ok3(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok3()-执行的目标方法-" + signature.getName());

    }

    //后置通知
    //@After(value = "execution(public void Car.run())")
    public void ok4(JoinPoint joinPoint) {


        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok4()-执行的目标方法-" + signature.getName());
        //演示一下JoinPoint常用的方法.
        joinPoint.getSignature().getName(); // 获取目标方法名
        joinPoint.getSignature().getDeclaringType().getSimpleName(); // 获取目标方法所属类的简单类名
        joinPoint.getSignature().getDeclaringTypeName(); // 获取目标方法所属类的类名
        joinPoint.getSignature().getModifiers(); // 获取目标方法声明类型(public、private、protected)
        Object[] args = joinPoint.getArgs(); // 获取传入目标方法的参数，返回一个数组
        joinPoint.getTarget(); // 获取被代理的对象
        joinPoint.getThis(); // 获取代理对象自己
    }



}
