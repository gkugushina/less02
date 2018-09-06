package less02;

import java.util.ArrayList;
import java.util.List;

public class LambdaM {

	public static void main(String[] args) {
		MyInt myInt = new MyInt() {
			@Override
			public void doSomething() {
				System.out.println("Hello Lambda");
			}
		};
myInt.doSomething();
//List al = new ArrayList();
SomeClass sc = new SomeClass();
sc.someMethod(()->System.out.println("Hello Lambda"));


sc.otherMethod((string,number)->System.out.println("Hello Lambda"));
	}

}
