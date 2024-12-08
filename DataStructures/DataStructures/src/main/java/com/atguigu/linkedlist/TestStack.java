package com.atguigu.linkedlist;

import java.util.Stack;

//演示栈Stack的基本使用
public class TestStack {

	public static void main(String[] args) {
		Stack<String> stack = new Stack();
		// 入栈
		// stack.add("jack");
		// stack.add("tom");
		// stack.add("smith");

		// 在 Java 的 Stack 类中，add 和 push 方法的功能是相同的，它们都用于将元素添加到栈中。不过，push 是 Stack 类特有的方法，而 add 是从 Vector 类继承而来的。
		// 推荐使用 push: 在使用 Stack 时，推荐使用 push 方法，因为它更清晰地表达了栈的操作意图，符合栈的语义。
		// 虽然 add 和 push 都可以用于将元素添加到 Stack 中，但 push 更符合栈的操作语义，使用时更具可读性。
		stack.push("jack");
		stack.push("jack2");
		stack.push("jack3");

		// 出栈
		// smith, tom , jack
		while (stack.size() > 0) {
			System.out.println(stack.pop());//pop就是将栈顶的数据取出
		}
	}

}
