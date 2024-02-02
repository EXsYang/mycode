package com.hspedu.furn.util;

import lombok.Data;

/**
 * @author yangda
 * @create 2023-12-21-22:40
 * @description: 工具类返回json格式数据
 * 1.Result<T> 自定义泛型类
 * 2.Result<T> 对象就是后端返回给前端的数据，是以json格式返回
 *
 */
@Data
public class Result<T> {

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
    // public static <T> Result success(T data){
    //     Result result = new Result<>();
    //     result.setCode("200");
    //     result.setMsg("success");
    //     result.setData(data);
    //     return result;
    // }

    /**
     * 这种改动确实对之前的结论产生了影响。在这个新的方法版本中，您在方法签名中明确指定了返回类型为 Result<T>，并且在方法体内创建了一个 Result<T> 类型的对象。这样做的好处是增加了类型安全性和明确性。
     *
     * 具体的变化包括：
     *
     * 类型安全性提升：通过明确指定返回类型为 Result<T>，您确保了返回的 Result 对象与传入的 data 类型相匹配。这样，调用者在接收返回的 Result 对象时，可以直接知道其泛型类型，而无需进行额外的类型转换或推断。
     *
     * 明确性增强：在方法签名中明确指出返回的是 Result<T> 类型，使得方法的意图和行为更加清晰。这对于维护代码和阅读代码的人来说是一个优势，因为它减少了对代码行为的猜测。
     *
     * 综上所述，通过这种修改，您增强了方法的类型安全性和明确性，同时保持了泛型的灵活性。这种做法是推荐的，特别是在创建公共API或者需要明确类型信息的场合。
     *
     * User
     * 通过形参传递的data的类型，再通过result.setData(data);指定倒result对象的类的泛型T,这样传递的对吗
     * GPT
     * 您的理解基本正确，但需要稍微澄清一点：在 Java 泛型中，泛型的类型是在编译时确定的，而不是在运行时。所以，当您调用 success(T data) 方法时，泛型 T 的具体类型是根据传入参数 data 的类型在编译时就确定了，而不是在运行时通过 setData 方法“传递”给 Result 对象的。
     *
     * 具体来说：
     *
     * 当您调用 success(T data) 方法时，比如 success("Hello")，编译器会查看 "Hello" 是一个 String 类型，因此会将方法中的泛型 T 解析为 String。
     *
     * 这意味着 Result<T> 在这个上下文中实际上是 Result<String>。因此，当创建 new Result<>() 实例时，实际上是创建了一个 Result<String> 的实例。
     *
     * 当调用 result.setData(data) 时，您实际上是将一个 String 类型的对象设置为 Result<String> 的 data 属性。这里没有类型的“传递”，而是类型已经在方法调用时确定，setData 只是将一个已经确定类型的对象赋值给 data 属性。
     *
     * 总结来说，泛型 T 的类型是通过传入 success 方法的参数 data 的类型在编译时确定的。setData 方法只是在这个已确定的类型框架内操作，而不是决定或改变泛型 T 的类型。
     *
     * User
     * 这个方法中声明的Result<T> 中的T的类型是根据哪里确定的呢？形参(T data)中的类型T是方法级别的还是类级别的？
     * GPT
     * 在 public static <T> Result<T> success(T data) 方法中，泛型 T 的类型由方法调用时传入的参数 data 的类型确定。具体来说：
     *
     * 泛型 T 的确定：当这个方法被调用时，比如 Result.success("some string")，编译器会根据传入参数的类型（在此例中为 String）来推断泛型 T 的类型。因此，在这个例子中，T 被推断为 String 类型。
     *
     * 方法级别的泛型：在 success(T data) 方法声明中的 <T> 表明 T 是方法级别的泛型。这意味着 T 的具体类型只在这个方法的上下文中有效，并且是基于每次方法调用时传入的参数来动态确定的。
     *
     * 与类级别泛型的关系：虽然 T 是方法级别的泛型，但是在这个方法中，它同时被用于指定返回的 Result<T> 对象的泛型类型。这就意味着，返回的 Result<T> 对象的泛型类型将与传入 success 方法的 data 参数的类型一致。
     *
     * 因此，这个方法中的 T 是方法级别的泛型，它的类型是根据传入方法的参数 data 的类型在编译时确定的，并且这个类型信息被用于确定返回的 Result<T> 对象的泛型类型。
     *
     * @param data
     * @param <T> Result<T> 中的T的类型是方法级别的泛型
     * @return
     */
    //编写方法-返回需要的Result对象-表示成功的Result,同时可以携带数据
    public static <T> Result<T> success(T data){

        // result 的类型是原生类型（未指定泛型）
        // Result result = new Result<>(); //

        // Result<T> result = new Result<>(); //当调用这个方法时，实际上已经指定了Result<T> T的类型

        /**
         * 虽然 T 是方法级别的泛型，但是在这个方法中，它同时被用于指定返回的 Result<T> 对象的泛型类型。这里指定了T的类型后，后面再创建对象还可以指定其他类型吗？例如： public static <T> Result<T> success(T data){
         *
         *         // Result<T> result = new Result<>(); //当调用这个方法时，实际上已经指定了Result<T> T的类型
         *
         *         Result result = new Result<>();
         *
         *         Result<String> result2 = new Result<>();
         *
         *         result.setCode("200");
         *         result.setMsg("success");
         *         result.setData(data);
         *         return result;
         *
         *     }
         * GPT
         * 在您的示例中，对于 Result<T> 类型的泛型 T，一旦在方法调用时确定了类型，就不能在方法体内改变它。但是您可以创建不同泛型类型的 Result 对象。让我们逐一看看您的代码示例：
         *
         * Result<T> result = new Result<>();：这里创建了一个 Result<T> 类型的对象，其泛型 T 与方法参数 T 相同。由于泛型 T 是从方法参数 data 推断出来的，这个 result 对象将具有与 data 相同的类型。
         *
         * Result result = new Result<>();：这里创建的 result 是一个原生类型的 Result 对象，没有指定泛型类型。它实际上是 Result<Object> 类型，意味着它可以持有任何类型的 data。在使用时，可能需要进行类型转换。
         *
         * Result<String> result2 = new Result<>();：这里创建了一个泛型类型为 String 的 Result 对象。它与方法的泛型 T 无关，这是一个独立于方法泛型参数的声明。
         *
         * 需要注意的是，尽管您可以在方法内创建不同泛型类型的 Result 对象，但方法的返回类型是由其签名确定的。在这个例子中，方法的返回类型是 Result<T>，这意味着您应该返回与参数 data 的类型相匹配的 Result 对象。
         *
         * 由于在这个方法中 result 的类型是原生类型（未指定泛型），它在编译时不会进行严格的类型检查，这可能导致类型安全问题。因此，在实际应用中，建议始终明确泛型类型以保持类型安全。在您的例子中，如果 data 的类型是 String，那么返回 result（原生类型）是可以的，但返回 result2（Result<String> 类型）则可能会导致类型不匹配错误。
         */
        // Result<String> result2 = new Result<>();

        /**
         *
         * 当您在 public static <T> Result<T> success(T data) 方法中使用泛型 T，这里的泛型 T 的行为和确定方式涉及几个关键点：
         *
         * 方法级泛型的确定：
         *
         * 在这个方法声明中，<T> 指明了 T 是一个方法级的泛型参数。这意味着 T 的具体类型将由调用这个方法时传入的参数决定。
         * 例如，如果调用 Result.success("Hello")，T 会被推断为 String 类型；如果调用 Result.success(123)，T 则被推断为 Integer 类型。
         * 泛型在方法内的应用：
         *
         * 在您的方法体内，Result<T> result = new Result<>(data); 这行代码创建了一个 Result<T> 类型的对象。此时，T 已经根据方法调用时传入的参数 data 的类型确定了。
         * 这意味着 result 对象和它的 data 成员变量将具有相同的类型，这个类型就是调用方法时确定的 T 的类型。
         * 类级泛型的关联：
         *
         * 虽然 T 在方法签名中定义，但它也被用于指定类 Result 的泛型类型。因此，在这个方法内部创建的 Result 对象的类型将直接关联到传入参数 data 的类型。
         * 这就是说，Result<T> 对象的泛型类型 T 和方法参数 T 是同一个类型，它们是在方法调用时确定的。
         * 总结一下，当您调用 Result.success(data) 方法时，传入的 data 参数的类型决定了泛型 T 的具体类型。这个类型随后用于构建 Result<T> 对象，确保 result 对象的 data 成员变量与传入的 data 参数具有相同的类型。这样的设计确保了类型的一致性和安全性，同时保持了代码的灵活性。
         */
        Result<T> result = new Result<>(data); //

        result.setCode("200");
        result.setMsg("success");
        // result.setData(data);
        return result;

    }


    //编写方法-返回需要的Result对象-表示失败的Result
    /**
     * 失败的状态码根据不同的错误，要返回不同的状态码code比如404,403,500,同时发生的错误不同
     * ,对错误的描述msg也不一样,这里做成形参,这样更加灵活
     */
    public static Result error(String code,String msg){
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;

    }

    //编写方法-返回需要的Result对象-表示失败的Result,同时可以携带数据
    public static <T> Result<T> error(String code,String msg,T data){

        Result<T> result = new Result<>(data);

        result.setCode(code);
        result.setMsg(msg);
        return result;

    }




}
