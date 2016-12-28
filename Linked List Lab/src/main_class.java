import data_structures.*;

public class main_class {

	public static void main(String[] args) {
		StringArray S = new StringArray();
		S.add(0, "a");
		S.add(1,  "b");
	    System.out.println(S.get(0) + " " + S.get(1) + " " + S.size());
	    S.set(0,  "c");
	    System.out.println(S.get(0) + " " + S.get(1) + " " + S.size());
	    S.remove(0);
	    System.out.println(S.get(0) + " " + S.size());
	}

}
