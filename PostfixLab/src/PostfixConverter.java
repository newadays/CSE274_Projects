/**
 * Project: Infix -> Postfix conversion
 * @author TODO: Tyler Davis
 *
 */

public class PostfixConverter {
	
	/**
	 * Convert an infix expression to a postfix expression.  Assumes the expression uses
	 * only integers, parenthesies, and the operator set {+, -, *, /, ^}.
  	 * @param formula   The infix expession.
	 * @return          The postfix expression.
	 * @exception       Throw Illegal Argument exception if the parenthesis
	 *                  don't match up correctly.
	 */
	public static String infix2postfix(String formula) {
		// TODO: Write code (REMOVE THIS COMMENT!!!)
		Tokenizer t = new Tokenizer(formula);
		String r = "";
		CharStack S = new CharStack(20);
		String tkn = t.next();
		while(!(tkn.equals(""))){
			if(isNum(tkn)) {
				r = r + " " + tkn;
			}
			else if(isOpenParen(tkn)) {
				S.push(tkn.charAt(0));
			}
			else if(isCloseParen(tkn)) {
				String s = "" + S.pop();
				while(!isOpenParen(s)) {
					if(S.size() == 0) throw new IllegalArgumentException();
					r = r +  " " + s;
					s = "" + S.pop();
				}
			}
			else if(isExp(tkn)) {
				S.push(tkn.charAt(0));
			}
			else if(isOperator(tkn)) {

				if(S.size() == 0) {
					S.push(tkn.charAt(0));
				}
				else {
					int tknP = getPrecedence(tkn);
					int endP = getPrecedence(S.peek() + "");
					//System.out.println(S.peek());
					//System.out.println(tkn);
					//System.out.println(tknP);
					//System.out.println(endP);
					
					if(!isOperator(S.peek() + "")) S.push(tkn.charAt(0));
					else if((tknP == -1 || endP == -1)) throw new IllegalArgumentException();
					else if(S.size() == 0) {
						S.push(tkn.charAt(0));
					}
					else if(endP >= tknP) {
						while((endP >= tknP) && S.size()>0) {
							endP = getPrecedence(S.peek() + "");
							if(endP >= tknP) {
								r = r + " " + S.pop();
							}
						}
						S.push(tkn.charAt(0));
					}
					else S.push(tkn.charAt(0));
					
				}
			}
			tkn = t.next();
		}
		while(S.size() > 0) {
			String c = "" + S.pop();
			if(isOperator(c)) r = r + " " + c;
			else throw new IllegalArgumentException();
		}
		return r;
		
	}
	
	/**
	 * Given postfix expression, calculate the value.
	 * @param s      The postfix expression.
	 * @return       double: the calculated value.
	 */
	public static double evaluatePostfix(String s) {
		// TODO: Write code (REMOVE THIS COMMENT!!!)
		//CharStack S = new CharStack(20);
		DoubleStack D = new DoubleStack(20);
		Tokenizer t = new Tokenizer(s);
		String tkn = t.next();
		while(!(tkn.equals(""))) {
			if(isNum(tkn)) {
				D.push(Double.parseDouble(tkn));
			}
			else if(isOperator(tkn)) {
				//System.out.println(tkn);
				if(D.size() <=1) throw new IllegalArgumentException();
				
				double d2 = D.pop(), d1 = D.pop();
				//System.out.println(d1);
				//System.out.println(d2);
				switch(tkn) {
				case "+": D.push(d1 + d2); break;
				case "-": D.push(d1 - d2); break;
				case "*": D.push(d1 * d2); break;
				case "/": D.push(d1 / d2); break;
				case "^": D.push(Math.pow(d1, d2)); break;
				}
				//System.out.println(D.peek());
				
			}
			else throw new IllegalArgumentException();
			
			tkn = t.next();
		}
		if(D.size() >=2 || D.size() == 0) throw new IllegalArgumentException();
		
		return D.pop();
	}
	private static boolean isOperator(String c) {
		return (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^"));
	}
	private static boolean isOpenParen(String c) {
		return (c.equals("("));
	}
	private static boolean isCloseParen(String c) {
		return c.equals(")");
	}
	private static boolean isNum(String c) {
		try {
			Double.parseDouble(c);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	private static boolean isExp(String c) {
		return c.equals("^");
	}
	// Gets a way to rank the operators in precedence
	private static int getPrecedence(String c) {
		if(c.equals("^")) return 2;
		else if(c.equals("*") || c.equals("/")) return 1;
		else if(c.equals("+") || c.equals("-")) return 0;
		else return -1;
	}
}

