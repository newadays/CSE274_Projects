// This is only intended to test your class for very basic errors.  Basically, it 
// tests whether your Queue code will compile and run, but does not test whether it works.
public class Main {

	public static void main(String[] args) {
		

		//1 2 + 3 4 * 5 6 - ^ ^ 7 *
		String post = "(1+2) ^ (3*4) ^ (5-6) * 7";
		System.out.println(PostfixConverter.infix2postfix(post));
		System.out.println();
		
		String in = "2 3 4 * +";
		System.out.println(PostfixConverter.evaluatePostfix(in));
		System.out.println();
		
		String infix = "2 + 3 * 4";
		System.out.println("Infix: " + infix);
		String postfix = PostfixConverter.infix2postfix(infix);
		System.out.println("Postfix: " + postfix);
		double val = PostfixConverter.evaluatePostfix(postfix);
		System.out.println("Value: " + val + "\n");
		
		infix = "(2 + 3) * 4";
		System.out.println("Infix: " + infix);
		postfix = PostfixConverter.infix2postfix(infix);
		System.out.println("Postfix: " + postfix);
		val = PostfixConverter.evaluatePostfix(postfix);
		System.out.println("Value: " + val + "\n");
		
		infix = "10 - 5 - 2";
		System.out.println("Infix: " + infix);
		postfix = PostfixConverter.infix2postfix(infix);
		System.out.println("Postfix: " + postfix);
		val = PostfixConverter.evaluatePostfix(postfix);
		System.out.println("Value: " + val + "\n");
		
		infix = "10 - (5 - 2)";
		System.out.println("Infix: " + infix);
		postfix = PostfixConverter.infix2postfix(infix);
		System.out.println("Postfix: " + postfix);
		val = PostfixConverter.evaluatePostfix(postfix);
		System.out.println("Value: " + val + "\n");
		
	}

}
