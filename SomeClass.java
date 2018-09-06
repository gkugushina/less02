package less02;

public class SomeClass {
	String string="Hello";
	int number=2;
public void someMethod(MyInt myInt) {
	myInt.doSomething();
}
public void otherMethod(MyOwn myOwn) {
	myOwn.method1(string, number);
}
}
