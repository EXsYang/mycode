package com.atguigu.java2;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的使用
 *
 * 1.使用情境：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
 *
 * 2.方法引用，本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例。所以
 *   方法引用，也是函数式接口的实例。
 *
 * 3. 使用格式：  类(或对象) :: 方法名
 *
 * 4. 具体分为如下的三种情况：
 *    情况1     对象 :: 非静态方法
 *    情况2     类 :: 静态方法
 *
 *    情况3     类 :: 非静态方法
 *
 * 5. 方法引用使用的要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的
 *    形参列表和返回值类型相同！（针对于情况1和情况2）
 *
 * Created by shkstart.
 */
public class MethodRefTest {

	// 情况一：对象 :: 实例方法
	//Consumer中的void accept(T t)
	//PrintStream中的void println(T t)
	@Test
	public void test1() {
		Consumer<String> con1 = str -> System.out.println(str);
		con1.accept("北京");

		System.out.println("*******************");
		PrintStream ps = System.out;
		Consumer<String> con2 = ps::println;
		con2.accept("beijing");
	}
	
	//Supplier中的T get()
	//Employee中的String getName()
	@Test
	public void test2() {
		Employee emp = new Employee(1001,"Tom",23,5600);

		Supplier<String> sup1 = () -> emp.getName();
		System.out.println(sup1.get());

		System.out.println("*******************");
		Supplier<String> sup2 = emp::getName;
		System.out.println(sup2.get());

	}

	// 情况二：类 :: 静态方法
	//Comparator中的int compare(T t1,T t2)
	//Integer中的int compare(T t1,T t2)
	@Test
	public void test3() {
		Comparator<Integer> com1 = (t1,t2) -> Integer.compare(t1,t2);
		System.out.println(com1.compare(12,21));

		System.out.println("*******************");

		Comparator<Integer> com2 = Integer::compare;
		System.out.println(com2.compare(12,3));

	}
	
	//Function中的R apply(T t)
	//Math中的Long round(Double d)
	@Test
	public void test4() {
		Function<Double,Long> func = new Function<Double, Long>() {
			@Override
			public Long apply(Double d) {
				return Math.round(d);
			}
		};

		System.out.println("*******************");

		Function<Double,Long> func1 = d -> Math.round(d);
		System.out.println(func1.apply(12.3));

		System.out.println("*******************");

		Function<Double,Long> func2 = Math::round;
		System.out.println(func2.apply(12.6));
	}

	// 情况三：类 :: 实例方法  (有难度)
	// Comparator中的int comapre(T t1,T t2)
	// String中的int t1.compareTo(t2)
	// 参数数量不一样，但是前面的参数调用后面的参数，也可以用方法引用
	@Test
	public void test5() {
		Comparator<String> com1 = (s1,s2) -> s1.compareTo(s2);
		System.out.println(com1.compare("abc","abd"));

		System.out.println("*******************");

		Comparator<String> com2 = String :: compareTo;
		System.out.println(com2.compare("abd","abm"));
	}

	//BiPredicate中的boolean test(T t1, T t2);
	//String中的boolean t1.equals(t2)
	// 参数数量不一样，但是前面的参数调用后面的参数，也可以用方法引用
	@Test
	public void test6() {
		BiPredicate<String,String> pre1 = (s1,s2) -> s1.equals(s2);
		System.out.println(pre1.test("abc","abc"));

		System.out.println("*******************");
		BiPredicate<String,String> pre2 = String :: equals;
		System.out.println(pre2.test("abc","abd"));


		BiPredicate<String,String> pre3 = Object :: equals;
		System.out.println(pre3.test("abc","abd"));
		/**
		 * 这里写成Object也没报错，说明只是告诉lambda或是编辑器要到那个类中寻找equals方法对吗？
		 * 您又提出了一个非常好的问题，这能让我们把理解推向更深一层。
		 *
		 * 您的猜测方向是对的，但有个更精确、更重要的原因：**继承和多态**。
		 *
		 * 您写成 `Object::equals` 没有报错，并不仅仅是告诉编译器去 `Object` 类里寻找 `equals` 方法，而是基于以下一个关键的编译时检查：
		 *
		 * > **编译器会验证：函数式接口的第一个参数类型（在这里是 `String`），是否有资格作为 `Object` 类的实例来调用 `equals` 方法。**
		 *
		 * 答案是**肯定的**。因为在 Java 中，`String` **继承自** `Object`，所以任何一个 `String` 实例**本身就是一个** `Object` 实例。因此，让一个 `String` 对象去调用 `Object` 类中定义的 `equals` 方法，在类型上是完全安全和合法的。
		 *
		 * ### 编译器的工作流程
		 *
		 * 当编译器看到 `BiPredicate<String, String> pre3 = Object::equals;` 这行代码时，它会进行如下检查：
		 *
		 * 1.  **接口需要什么？**：`BiPredicate<String, String>` 需要一个 `test(String s1, String s2)` 方法的实现。
		 * 2.  **方法引用是什么？**：`Object::equals`。它引用了 `Object` 类中的 `public boolean equals(Object obj)` 方法。
		 * 3.  **能否匹配？**
		 *       * **检查调用者类型**：`test` 方法的第一个参数 `s1` 是 `String` 类型。`String` 是 `Object` 的子类吗？**是**。所以 `s1` 有资格调用一个在 `Object` 中定义的方法。**检查通过**。
		 *       * **检查参数类型**：`test` 方法的第二个参数 `s2` 是 `String` 类型。`equals` 方法需要一个 `Object` 类型的参数。`String` 是 `Object` 的子类吗？**是**。所以可以把 `s2` 传递给 `equals` 方法。**检查通过**。
		 *
		 * 因为所有检查都通过了，所以这行代码是合法的。
		 *
		 * ### 运行时会发生什么？(多态的魔力)
		 *
		 * 这才是最有趣的部分。虽然您引用的是 `Object::equals`，但在运行时，当 `pre3.test("abc", "abd")` 被调用时：
		 *
		 * 1.  第一个参数 `"abc"` (一个 `String` 实例) 成为了调用者。
		 * 2.  Java 的动态方法分派（多态）机制会介入。JVM 会检查调用者 `"abc"` 的**实际类型**，发现它是一个 `String`。
		 * 3.  因此，最终被执行的**不是** `Object` 类原始的 `equals` 方法（比较内存地址 `==`），而是 `String` 类**重写（Override）后**的 `equals` 方法（比较字符序列）。
		 *
		 * 这就是为什么 `Object::equals` 和 `String::equals` 在这个上下文中的**最终运行效果完全一样**。
		 *
		 * ### 一个会报错的反例
		 *
		 * 为了证明这不仅仅是“找到方法”那么简单，我们来看一个反例。假设我们写：
		 *
		 * ```java
		 * // 这段代码会编译失败
		 * BiPredicate<String, String> pre4 = Integer::equals;
		 * ```
		 *
		 * 编译器会报错，为什么？让我们走一遍流程：
		 *
		 * 1.  **接口需要什么？**：`test(String s1, String s2)`。
		 * 2.  **方法引用是什么？**：`Integer::equals`。
		 * 3.  **能否匹配？**
		 *       * **检查调用者类型**：`test` 方法的第一个参数 `s1` 是 `String` 类型。`String` 是 `Integer` 的子类吗？**不是**。它们是兄弟关系，都继承自 `Object`。因此，`String` 类型的对象没有资格去调用 `Integer` 类的实例方法。**检查失败**！
		 *
		 * 编译器在这里就会立即报错，因为它无法保证类型安全。
		 *
		 * ### 结论
		 *
		 * 您写成 `Object` 没有报错，背后是强大的**继承**和**多态**原则在起作用。
		 *
		 *   * **编译时**，因为 `String` is-a `Object`，所以编译器认为这个引用是类型安全的。
		 *   * **运行时**，因为多态性，实际执行的仍然是 `String` 对象自己重写过的 `equals` 方法。
		 *
		 * 所以，您的观察非常敏锐，它揭示了方法引用与 Java 核心的面向对象特性之间紧密的联系。
		 */


	}







	// Function中的R apply(T t)
	// Employee中的String getName();
	// 当Lambda体中的实现是 "一个参数" 作为 "实例方法" 的调用者，且该实例方法无参时，
	// 可以使用 "类名::实例方法名" 的形式进行方法引用。
/*
	"类名::实例方法名" 这个问题确实是 Java 方法引用的一个难点，
	因为它看起来像是两种不同的规则，但实际上是同一种规则在不同情况下的应用。

	我们彻底地、详细地把它讲清楚。

	核心规则：类名::实例方法名
	这种方法引用的唯一核心规则是：

	函数式接口方法的第一个参数，会成为调用实例方法的对象（this），其余的参数（如果有的话）会作为实例方法的参数传递进去。

	让我们用一个通用的“公式”来表示：

	如果你的 Lambda 是这样的：
	(param1, param2, ..., paramN) -> param1.instanceMethod(param2, ..., paramN)

	那么它就可以简化为：
	ClassOfParam1::instanceMethod

	现在，我们用这个唯一的规则来分析你之前的两个例子，你会发现它们都完全符合这个规则。

	详细分析 1：test7 的情况 (最简单的情况)
	函数式接口: Function<Employee, String>

	接口方法: String apply(Employee e)

	这个方法有几个参数？ 1 个 (Employee e)。

	Lambda 表达式: e -> e.getName()

	套入我们的核心规则:

	找到第一个参数: 接口方法的第一个（也是唯一的）参数是 e。

	这个参数做了什么？: 它成为了调用实例方法 getName() 的对象。

	其余的参数呢？: 接口方法没有其余的参数了。

	getName() 方法本身需要参数吗？: 不需要。

	完美匹配: apply 方法的参数列表 (Employee e) 和 e.getName() 的调用方式完全匹配。第一个参数 e 去调用方法，没有剩余参数，getName() 也不需要参数。

	所以，e -> e.getName() 符合 (param1) -> param1.instanceMethod() 的结构，可以简化为 Employee::getName。

	详细分析 2：test6 的情况 (看起来复杂，但规则相同)
	函数式接口: BiPredicate<String, String>

	接口方法: boolean test(String s1, String s2)

	这个方法有几个参数？ 2 个 (String s1, String s2)。

	Lambda 表达式: (s1, s2) -> s1.equals(s2)

	再次套入我们的核心规则:

	找到第一个参数: 接口方法的第一个参数是 s1。

	这个参数做了什么？: 它成为了调用实例方法 equals() 的对象。

	其余的参数呢？: 接口方法还剩下第二个参数 s2。

	equals() 方法本身需要参数吗？: 需要，它需要 1 个 Object 类型的参数。

	完美匹配: 接口方法的其余参数 s2，正好被传递给了 s1 调用的 equals 方法。

	所以，(s1, s2) -> s1.equals(s2) 符合 (param1, param2) -> param1.instanceMethod(param2) 的结构，可以简化为 String::equals。

	总结与对比
	为了更清晰，我们用一个表格来对比：

	特性	test7: Employee::getName	test6: String::equals
	函数式接口方法	apply(Employee e)	test(String s1, String s2)
	接口方法参数个数	1	2
	Lambda 表达式	e -> e.getName()	(s1, s2) -> s1.equals(s2)
	第一个参数的作用	e 成为 getName() 的调用者	s1 成为 equals() 的调用者
	其余参数的作用	无	s2 成为 equals() 的方法入参
	被引用的实例方法	String getName() (无参)	boolean equals(Object obj) (有参)

	导出到 Google 表格
	最终的关键点：

	不要把 类名::实例方法名 理解为两种或多种规则。它只有一个规则。

	你之所以感到困惑，是因为被引用的实例方法 (getName 和 equals) 本身所需要的参数个数不同，导致函数式接口的参数个数也相应地发生了变化。

	当实例方法是无参的 (如 getName())，那么函数式接口只需要1个参数（即调用者本身）。

	当实例方法是有1个参数的 (如 equals(obj))，那么函数式接口就需要2个参数（调用者 + 方法的1个参数）。

	以此类推，如果实例方法有 N 个参数，那么函数式接口就需要 N+1 个参数。


	 */
	@Test
	public void test7() {
		Employee employee = new Employee(1001, "Jerry", 23, 6000);

		// Function中的R apply(T t)
		// Employee中的String getName();
		// 当Lambda体中的实现是 "一个参数" 作为 "实例方法" 的调用者，且该实例方法无参时，
		// 可以使用 "类名::实例方法名" 的形式进行方法引用。

		Function<Employee,String> func1 = e -> e.getName();
		System.out.println(func1.apply(employee));

		System.out.println("*******************");

		// 传入Employee类型的对象，返回一个String类型的返回值
		Function<Employee,String> func2 = Employee::getName;
		System.out.println(func2.apply(employee));


		/**
		 * 这里的类名::实例方法名 关于类名有什么说法吗？类名不可以直接调用实例方法吧？
		 *
		 * 您提出了一个非常关键且深刻的问题！
		 *
		 * 您观察得完全正确：在常规的 Java 代码中，我们**绝对不可以用类名去直接调用一个实例方法**。
		 *
		 * 例如，`String.equals("a")` 是非法的，会直接导致编译错误。实例方法必须由一个类的**实例**（也就是对象）来调用，比如 `new String("a").equals("b")`。
		 *
		 * 那么，为什么方法引用的语法是 `类名::实例方法名` 呢？这看起来似乎违背了 Java 的基本规则。
		 *
		 * ### 解开谜团：`::` 不是一个调用操作符
		 *
		 * 这里的关键在于，`::` **不是一个方法调用运算符**。它是一个**方法引用运算符**。
		 *
		 * 当编译器看到 `String::equals` 这样的代码时，它并**不会**去执行 `equals` 方法。相反，它会做以下事情：
		 *
		 * 1.  **创建了一个“计划”或“蓝图”**: 它创建了一个实现了相应函数式接口（比如 `BiPredicate`）的实例。这个实例内部包含了一个指向 `String` 类中 `equals` 方法的引用。
		 * 2.  **这个“计划”描述了如何执行**: 这个计划的内容就是我们之前讨论的核心规则：“将来谁调用我，谁就会把它的**第一个参数**作为 `equals` 方法的调用者，把**第二个参数**作为 `equals` 方法的入参。”
		 *
		 * 所以，`类名::实例方法名` 语法中的 **`类名`** 扮演了两个重要角色：
		 *
		 * 1.  **指明方法的来源**: 它告诉编译器去哪个类（`String`）里查找这个实例方法（`equals`）。编译器需要这个信息来验证方法是否存在、参数是否匹配等。
		 * 2.  **确定调用者的类型**: 它声明了“将来要调用这个实例方法的对象，它的类型必须是这个类（或者其子类）”。在 `String::equals` 中，它规定了将来作为调用者的第一个参数必须是 `String` 类型。
		 *
		 * ### 用一个生活的比喻来理解
		 *
		 * 想象一下你在写一本烹饪书。
		 *
		 * * **`String::equals`** 就像是书里的一页食谱，标题是 **“字符串相等比较法”**。
		 *     * 这页食谱本身并**没有**包含任何真实的字符串，它只是一个**指令集**。
		 *     * 食谱上写着：“你需要两个食材（两个字符串）。把第一个食材作为主料，用它去和第二个食材进行‘比较’操作。”
		 *     * 这里的 **“字符串”** (`String`) 就相当于**类名**，它定义了这套操作是针对哪一类食材的。
		 *
		 * * **`pre.test("abc", "abd")`** 就像是你真正开始按照食谱做菜。
		 *     * 你拿出了两个真实的食材 `"abc"` 和 `"abd"`。
		 *     * 你按照食谱的指令，把第一个食材 `"abc"` 作为主料（调用者），对第二个食材 `"abd"` 执行了“比较”操作 (`.equals()`)。
		 *
		 * ### 总结
		 *
		 * 所以，`类名::实例方法名` 中的 **`类名`** 并不是要去“调用”实例方法。它的作用是：
		 *
		 * 1.  **静态的类型声明**: 告诉编译器这个方法属于哪个类，以及未来调用它的对象应该是什么类型。
		 * 2.  **提供方法蓝图**: 作为一个“工厂”，用来创建一个实现了函数式接口的对象，这个对象知道将来如何利用传入的参数去调用那个实例方法。
		 *
		 * 这种语法设计得非常精妙，它重用了 `类名` 这个符号，以一种简洁的方式，既指明了方法的出处，又规定了未来调用者的类型，完美地解决了“需要一个实例才能调用，但定义引用时又没有实例”的矛盾。
		 */


	}

}
