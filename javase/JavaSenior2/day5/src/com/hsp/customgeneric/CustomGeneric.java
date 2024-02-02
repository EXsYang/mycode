package com.hsp.customgeneric;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangda
 * @description:自定义泛型类
 * @create 2022-11-19-11:15
 */
public class CustomGeneric {
    public static void main(String[] args) {

        String classF = Tiger.classF(String.class);

        // 明确的指定泛型可以，如下
        // Tiger<String, String, String> da = new Tiger<>("da", "", "", "");

        // 不明确的指定泛型也可以，传入的是什么类型，初始化时就是什么类型，如下
        Tiger da = new Tiger<>("da", 3, "", "");


        System.out.println("ok");


        /**
         *  这个Tiger类使用 public Tiger(String name, T t, R r, L l) {//构造器使用泛型
         *         this.name = name;
         *         this.t = t;
         *         this.r = r;
         *         this.l = l;
         *     }构造器如何实例化？
         *
         * GPT
         * 要实例化 Tiger 类，使用包含泛型的构造器 public Tiger(String name, T t, R r, L l)，你需要在创建 Tiger 类的实例时指定泛型 T, R, L 的具体类型。泛型参数允许你在实例化对象时指定要使用的类型，使得同一个类可以灵活地处理不同类型的数据。
         *
         * 这里是如何实例化这个类的一个示例：
         *
         * 假设我们有这样的泛型类型：
         *
         * T 表示 Integer
         * R 表示 String
         * L 表示 Double
         * 然后，你可以这样创建 Tiger 类的实例：
         *
         * java
         * Copy code
         * Tiger<Integer, String, Double> tiger = new Tiger<>("Leo", 100, "Tiger", 250.5);
         * 在这个示例中，"Leo" 是 name 字段的值，100 是泛型 T 对应的 Integer 类型的值，"Tiger" 是泛型 R 对应的 String 类型的值，而 250.5 是泛型 L 对应的 Double 类型的值。
         *
         * 总结来说，你需要在创建 Tiger 类的实例时为泛型参数 T, R, L 指定具体的类型，并且提供与这些类型相匹配的值作为构造函数的参数。这样，你的类就可以灵活地处理不同类型的数据，同时保持类型安全。
         *
         * User
         * 不明确的指定泛型的类型，直接传入也可以吧？
         * GPT
         * 是的，你可以在创建 Tiger 类的实例时不明确指定泛型参数的具体类型，而是直接传入相应的值。在这种情况下，Java 编译器会根据传入的参数推断出泛型参数的实际类型。
         *
         * 例如，考虑以下 Tiger 类的实例化：
         *
         * java
         * Copy code
         * Tiger tiger = new Tiger("Leo", 100, "Tiger", 250.5);
         * 在这个例子中，我们没有明确指定泛型参数 T, R, L 的类型。Java 编译器会根据传入的参数自动推断出它们的类型。在这种情况下：
         *
         * 对于 100，编译器会推断 T 为 Integer 类型。
         * 对于 "Tiger"，编译器会推断 R 为 String 类型。
         * 对于 250.5，编译器会推断 L 为 Double 类型。
         * 这种类型推断特性可以简化代码，尤其是在泛型类型明显时。然而，如果编译器无法准确推断出类型，或者你想要明确指定特定的泛型类型（例如为了代码的可读性或特定的类型强制），那么显式指定泛型类型会更合适。例如，如果你想要泛型 T 是 Number 类型而不是默认的 Integer，那么你需要显式指定它：
         *
         * java
         * Copy code
         * Tiger<Number, String, Double> tiger = new Tiger<>("Leo", 100, "Tiger", 250.5);
         */

        /**
         * Tiger<T,R,L>类中的方法 public static void show2(T t){}
         * Tiger2<T>类中的方法 public static <T> Tiger2<T> useT(T data){}
         * ，public static void show2(T t){)会报错为什么，和加了泛型的标志<T>的useT方法的主要区别是什么？为什么加了<T>后就不报错了？
         * GPT
         * 在 Java 泛型中，类级别的泛型参数和静态方法之间存在一些关键的区别，这解释了为什么在 Tiger 类中某些静态方法的声明会导致错误，而其他则不会。
         *
         * 关于 public static void show2(T t){} 的问题
         * 在 Tiger 类中，T, R, L 是类级别的泛型参数。这意味着这些泛型参数的具体类型与每个 Tiger 实例相关联。然而，静态方法和属性不属于任何实例，它们属于类本身。因此，静态方法或属性无法直接访问类级别的泛型参数，因为它们在类实例化之前就已经存在了，而这时泛型参数的类型尚未确定。
         *
         * 关于 public static <T> Tiger2<T> useT(T data){} 的区别
         * public static <T> Tiger2<T> useT(T data){} 方法声明中的 <T> 是一个方法级别的泛型参数。这意味着每次调用该方法时，都会为该方法定义一个新的、独立的泛型类型 T。这与类级别的泛型参数完全无关。因此，这种方式是允许的，因为静态方法定义了它自己的泛型参数，不依赖于类实例。
         *
         * 总结
         * public static void show2(T t){} 会报错，因为它试图使用类级别的泛型参数 T，而静态方法无法访问与类实例相关联的泛型类型。
         * public static <T> Tiger2<T> useT(T data){} 不会报错，因为这里的 T 是方法级别的泛型参数，独立于类级别的泛型参数。
         * 这种设计是 Java 泛型的一个基本特性，旨在保持类型安全，同时确保静态成员的独立性和泛型方法的灵活性。
         */
    }
}

/*
1. Tiger 后面泛型 ，所以我们把 Tiger 就称为自定义泛型类
2. T,R,M 泛型的标识符，一般是单个大写字母
3，泛型标识符可以有多个
4. 普通成员可以使用泛型(属性，方法)
5.使用泛型的数组，不能初始化
6.静态方法中不能使用类的泛型
  ,如果静态方法中要使用泛型，就需要加一个泛型的标志<T> 如下面的Tiger2类中的useT()方法


7.泛型类的类型，是在创建对象时确定的(因为创建对象时，需要指定确定类型)
8.如果在创建对象时，没有指定泛型，默认为Object
 */

/**
 * 该类用于测试 在静态方法中使用泛型
 * @param <T>
 */
class Tiger2<T>{
    T data;

    public Tiger2() {
    }
    public <T> Tiger2(T t) {

    }

    public static <T> Tiger2<T> useT(T data){
        Tiger2<T> tiger2 = new Tiger2<>(data);
        return tiger2;
    }
}

class Tiger<T,R,L>{

    private String name;

    //普通成员可以使用泛型(属性，方法)
    T t;
    R r;
    L l;
    //因为数组在new 不能确定T的类型，就无法在内存开空间 即下面这样定义不可以
    //T[] ts = new T[4];//Type parameter 'T' cannot be instantiated directly
                    //类型参数T不能直接实例化
    T[] ts; // 这样定义可以


    public Tiger(String name, T t, R r, L l) {//构造器使用泛型
        this.name = name;
        this.t = t;
        this.r = r;
        this.l = l;
    }

    public <T,R,L> void show(){

    }
    public <T> void show1(){

    }

    //因为静态是和类相关的，在类加载时，对象还没有创建
    //如果静态方法和静态属性使用了泛型，JVM 就无法完成初始化
   // static T t1;
   // public static void show2(T t){
   // }


//泛型方法
    public <E> List<E> show3(E arr[]){
        ArrayList<E> list = new ArrayList<>();
        for (E e : arr) {
            list.add(e);
        }
            return list;
    }

    //在传入时 指定了 泛型方法的返回值类型
    public static  <F> F classF(Class<F> clazz){

        System.out.println("clazz= " + clazz);

        String f = "fff";

        return (F)f;
    }



//    show3(E arr[]) 和 show4(E[] arr) 有什么区别？

    public <E> List<E> show4(E[] arr){
        ArrayList<E> list = new ArrayList<>();
        for (E e : arr) {
            list.add(e);
        }
            return list;
    }

//    Type parameter 'String' hides visible type 'java.lang.String'
//    类型参数'String'隐藏了可见类型'java.lang.String'
    public <String> List<String> show5(String[] str){
        ArrayList<String> list = new ArrayList<>();
        for (String s : str) {
            list.add(s);
        }
            return list;
    }

    public List<String> show6(String[] str){
        ArrayList<String> list = new ArrayList<>();
        for (String s : str) {
            list.add(s);
        }
            return list;
    }






    public Tiger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getT() {//返回值使用泛型
        return t;
    }

    public void setT(T t) {//方法使用泛型
        this.t = t;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public L getL() {
        return l;
    }

    public void setL(L l) {
        this.l = l;
    }

    @Override
    public String toString() {
        return "\nTiger{" +
                "name='" + name + '\'' +
                ", t=" + t +
                ", r=" + r +
                ", l=" + l +
                '}';
    }
}


/**
 * @author yangda
 * @create 2023-12-21-22:40
 * @description:
 * 1.Result<T> 自定义泛型类
 * 2.Result<T> 对象就是后端返回给前端的数据，是以json格式返回
 *
 */
class Result<T> {

    private String code; //状态码
    private String msg; //对状态的说明
    private T data; // 返回时要携带的数据,为了扩展性更好,这里使用泛型

    //无参构造器
    public Result() {
    }

    //全参构造器
    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * User
     * 下面这个构造器 public Result(T data){}中指定的是类级别的泛型吗？还是构造器级别的泛型
     * GPT
     * 在您提供的代码中，public Result(T data) 这个构造器中使用的 T 是类级别的泛型，而不是构造器级别的泛型。
     *
     * 解释一下：
     *
     * 类级别的泛型：当一个泛型参数被定义在类的声明中时，它就是类级别的泛型。在您的代码中，Result<T> 定义了一个类级别的泛型 T。这意味着 T 可以在整个类的上下文中使用，包括所有的方法和构造器中。
     *
     * 构造器级别的泛型：如果一个泛型参数是在构造器声明中定义的，那么它是构造器级别的泛型。这意味着这个泛型参数只能在该构造器的上下文中使用。
     *
     * 在您的例子中，Result 类在定义时就声明了泛型参数 T（即 Result<T>），因此在整个类的上下文中，包括所有构造器和方法中，T 都是有效的。所以，public Result(T data) 构造器中的 T 是引用类级别定义的泛型参数，而不是一个新定义的、仅限于该构造器的泛型参数。
     *
     * 自己的理解：T data 在构造器的形参位置使用到了 类Result<T> 中定义的T,而不是在声明时定义的
     * @param data
     */
    public Result(T data){
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //编写方法-返回需要的Result对象-表示成功的Result
    public static Result success(){
        Result result = new Result<>();
        result.setCode("200");
        result.setMsg("success");
        return result;

    }


    /**
     * 其中的这个方法:public static <T> Result success(T data){} ，在形参位置，传入实参时指定这个方法级别的泛型T的数据类型，然后在 result.setData(data); 中指定了类Result<T>的泛型T的类型对吗?相当于在 success(T data)方法形参位置根据传入的实参类型对类级别的泛型Result<T>的泛型T的类型进行指定对吗？
     *
     * ChatGPT
     * 您的理解是正确的。在 `public static <T> Result success(T data)` 方法中，泛型 `T` 的定义是方法级别的，但它也同时指定了 `Result<T>` 类的泛型 `T` 的类型。这是因为泛型在 Java 中是一种形式上的参数化类型，它允许在创建对象或调用方法时指定具体的类型。
     *
     * 解释一下：
     *
     * 1. **方法级别的泛型**：在 `public static <T> Result success(T data)` 方法声明中，`<T>` 定义了一个方法级别的泛型参数。这意味着这个 `T` 只在该方法的上下文中有效。当调用这个方法时，会根据传入的实参的类型确定 `T` 的具体类型。
     *
     * 2. **类级别的泛型影响**：在这个方法中，当您创建一个 `Result<T>` 对象并调用 `setData(data)` 方法时，实际上是将方法级别的泛型 `T` 的类型传递给了类级别的泛型 `T`。因此，创建的 `Result` 对象的泛型类型将与传入的数据 `data` 的类型相匹配。
     *
     * 例如，如果您调用 `Result.success("Hello")`，方法中的 `T` 将被解析为 `String`，并且创建的 `Result<String>` 对象将包含一个类型为 `String` 的数据。这样，泛型 `T` 在这个方法内部既是方法级别的（用于确定传入数据的类型），又影响了类级别的泛型（用于创建特定类型的 `Result` 对象）。
     *
     * user
     * 但是result 对象创建时Result result = new Result<>();没有指定泛型，默认为Object 类型，这时的泛型不是已经确定为Object了嘛？可以后来再进行指定吗？
     *
     * ChatGPT
     * 您指出的情况确实是一个重要的细节。在 Java 泛型中，如果在创建对象时没有指定具体的泛型类型，那么这个泛型默认会被视为 `Object` 类型。这是因为 Java 泛型是在编译时进行类型检查的，而在运行时，泛型信息会被擦除（类型擦除），转化为普通的类（例如 `Object`）。
     *
     * 在您的代码 `Result result = new Result<>();` 中，由于没有明确指定泛型类型，因此 `result` 实际上是一个 `Result<Object>` 类型的对象。这意味着 `result` 可以持有任何类型的 `data`。
     *
     * 然而，在方法 `public static <T> Result success(T data)` 中，通过 `setData(data)` 方法设置 `data` 时，`data` 的实际类型是由调用 `success` 方法时传入的参数决定的。这个类型会在编译时进行检查，确保 `data` 参数与 `T` 兼容。但是，在运行时，由于类型擦除，这个具体的类型信息会丢失，`data` 只会被视为 `Object` 类型。
     *
     * 因此，尽管在编译时可以确保类型安全，但实际上 `result` 对象的 `data` 属性在运行时是 `Object` 类型的。这意味着在使用这个 `data` 时，可能需要进行类型转换。
     *
     * 为了避免这种模糊性和潜在的类型转换错误，建议在创建 `Result` 对象时明确指定泛型类型，例如：
     *
     * ```java
     * Result<String> result = new Result<>();
     * ```
     *
     * 这样，在使用 `result` 对象时，就可以确保 `data` 的类型是预期的 `String` 类型，从而减少运行时的类型错误风险。
     *
     * user
     * 但是如果在这里指定了类型后，扩展性就变差了，只能传入String类型了，不够灵活
     *
     * ChatGPT
     * 确实，指定具体的泛型类型（如 `String`）会限制泛型类的灵活性和通用性。为了保持灵活性，同时确保类型安全，可以在方法级别使用泛型。这样，每次调用该方法时，都可以根据传入的参数类型动态确定泛型的具体类型。
     *
     * 在您的 `Result` 类中，`public static <T> Result success(T data)` 方法利用了这一点。通过在方法声明中引入泛型 `<T>`，您可以在每次调用时根据传入的 `data` 参数类型动态确定 `Result` 对象的泛型类型。这样，相同的方法可以用来处理不同类型的数据。
     *
     * 例如，考虑以下调用：
     *
     * - 当调用 `Result.success("Hello World")` 时，返回的将是 `Result<String>` 类型的对象。
     * - 当调用 `Result.success(123)` 时，返回的将是 `Result<Integer>` 类型的对象。
     *
     * 这种方法保持了代码的灵活性和类型安全性。每次调用 `success` 方法时，泛型 `T` 都根据传入参数的类型被适当地推断和确定，而无需在创建 `Result` 实例时显式指定泛型类型。
     *
     * 要点在于，`Result result = new Result<>();` 这样的代码行确实创建了一个通用类型的 `Result` 对象，其 `data` 属性在编译时被视为 `Object` 类型。但是，由于 `success` 方法内部对 `data` 的类型进行了正确的推断，所以实际上返回的 `Result` 对象会安全地包含正确类型的 `data`。这是一种平衡灵活性和类型安全性的有效方式。
     * @param data
     * @param <T>
     * @return
     */
    //编写方法-返回需要的Result对象-表示成功的Result,同时可以携带数据
    public static <T> Result success(T data){
        Result result = new Result<>();
        result.setCode("200");
        result.setMsg("success");
        result.setData(data);
        return result;

    }

    // 泛型擦除（Type Erasure）说明:
    // 在Java中，泛型擦除（Type Erasure）是指在编译时去掉泛型类型信息的过程。这是Java泛型实现的一个重要特征。泛型主要用于编译时类型检查，而在运行时，所有的泛型类型信息都会被擦除，转换为原始类型（Raw Types）或边界类型（Bounds）。这意味着在运行时，JVM看到的代码是不包含泛型信息的。
    //
    // 泛型擦除的举例说明
    // 假设你有以下泛型类和方法：
    class Box<T> {
        private T t;

        public void set(T t) {
            this.t = t;
        }

        public T get() {
            return t;
        }
    }
    // 在这个例子中，Box 是一个泛型类，其中 T 是一个类型参数。当你使用这个类时，你可以为 T 指定任何类型：
public void test(){
    Box<String> stringBox = new Box<>();
    stringBox.set("Hello");

    Box<Integer> integerBox = new Box<>();
    integerBox.set(123);

}
    // 编译时
    // 在编译时，Java编译器会检查类型安全性，确保你没有尝试将错误的类型放入 Box 中。
    // 编译器也会进行类型推断，根据你放入 Box 的数据类型来确定 T 的具体类型。
    // 运行时
    // 但是，当代码被编译成字节码并在JVM上运行时，泛型信息会被擦除。这意味着，不论你之前使用了什么类型参数来实例化 Box 类，JVM只看到 Box 而不是 Box<String> 或 Box<Integer>。
    // 在运行时，所有的泛型类型参数都被替换为它们的原始类型（如果是无界的类型参数，则替换为 Object），或者是它们的边界类型（如果有界的话）。
    // 泛型擦除的目的
    // 泛型擦除的主要目的是为了向后兼容。当Java引入泛型时，已经有大量不使用泛型的代码存在。通过泛型擦除，Java允许新代码使用泛型而不破坏旧代码的兼容性。这使得旧的Java代码和库可以在新版本的Java环境中运行，而不需要任何修改。
    //
    // 结论
    // 泛型擦除意味着泛型仅在编译阶段有效，主要用于类型检查和类型推断。它使得编写更加类型安全的代码成为可能，同时保持了与旧Java代码的兼容性。但是，它也带来了一些限制，比如在运行时无法获取泛型的具体类型信息



}
