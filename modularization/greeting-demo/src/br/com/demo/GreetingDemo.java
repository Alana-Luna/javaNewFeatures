package br.com.demo;

import com.br.hello.HelloWorld;
import com.br.goodbye.GoodbyeWorld;

public class GreetingDemo {

	public static void main(String[] args) {
		
	// do not forget to bring the external module into the project's build path
		
		HelloWorld callHello = new HelloWorld();
		
		callHello.sayHello();
		
		GoodbyeWorld callGoodbye = new GoodbyeWorld();
		
		callGoodbye.sayGoodbye();
	}

}
