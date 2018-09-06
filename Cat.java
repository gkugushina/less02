package less02;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class Cat extends Animale implements Cloneable, Serializable, Externalizable {

private Owner owner;
public Cat(String name, int age) {
	super();
	this.name = name;
	this.age = age;
}
public Cat() {}
public Owner getOwner() {
	return owner;
}
public void setOwner(Owner owner) {
	this.owner = owner;
}

@Override
protected Object clone() throws CloneNotSupportedException {
	return super.clone();
}
public String getName() {
	return name;
}
@Override
public String toString() {
	return "Cat [name=" + name + ", age=" + age + ", owner=" + owner + "]";
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
@Override
public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	name = (String)in.readObject();
	age = in.readInt();
	owner=(Owner)in.readObject();
	
}
@Override
public void writeExternal(ObjectOutput out) throws IOException {
	out.writeObject(name);
	out.writeInt(age);
	out.writeObject(owner);
	
}
}
