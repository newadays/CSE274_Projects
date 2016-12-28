// This is only intended to test your class for very basic errors.  Basically, it 
// tests whether your Queue code will compile and run, but does not test whether it works.
public class Main {

	public static void main(String[] args) {
		IntStack Q = new IntStack(20);
		Q.isEmpty();
		Q.isFull();
		Q.push(1);
		Q.peek();
		Q.size();
		Q.pop();
	}

}
