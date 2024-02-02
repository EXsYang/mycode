package com.lxlx.x;

public class BuyTicketsThread {
	public static void main(String[] args) {
		TicketsHouse officer = new TicketsHouse();
		Thread zhangfei = new Thread(officer);
		zhangfei.setName("’≈∑…");
		Thread likui = new Thread(officer);
		likui.setName("¿ÓÂ”");
		Thread liubei = new Thread(officer);
		liubei.setName("¡ı±∏");
		zhangfei.start();
		likui.start();
		liubei.start();
	}
}
