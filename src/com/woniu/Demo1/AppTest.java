package com.woniu.Demo1;


public class AppTest {
	public static void main(String[] args) {
		showAbc sh = new showAbc();
		A a = new A(sh);
		B b = new B(sh);
		C c = new C(sh);
		
		Thread th = new Thread(a);
		Thread th2 = new Thread(b);
		Thread th3 = new Thread(c);
		
		th.start();
		th2.start();
		th3.start();
		
	}
}

class showAbc{
	private int i = 0;
	public synchronized void showA() {
		while(true) {
			if(i==0) {
				System.out.println("A");
				i=1;
				this.notifyAll();
			}
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public synchronized void showB() {
		while(true) {
			if(i==1) {
				System.out.println("B");
				i=2;
				this.notifyAll();
			}
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public synchronized void showC() {
		while(true) {
			if(i==2) {
				System.out.println("C");
				i=0;
				this.notifyAll();
			}
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


class A implements Runnable{
	private showAbc sh;
	
	public A(showAbc sh) {
		this.sh = sh;
	}

	@Override
	public void run() {
		while(true) {
			sh.showA();
		}
		
	}
}
class B implements Runnable{
	private showAbc sh;
	
	public B(showAbc sh) {
		this.sh = sh;
	}
	@Override
	public void run() {
		while(true) {
			sh.showB();
		}
		
	}
}
class C implements Runnable{
	private showAbc sh;
	
	public C(showAbc sh) {
		this.sh = sh;
	}
	@Override
	public void run() {
		while(true) {
			sh.showC();
		}
		
	}
}