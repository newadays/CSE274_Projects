import java.util.*;
//import data_structures.*;
/**
 *
 * @author Tyler Davis
 */
// This is case sensitive
public class StudentTrends implements Trends {
	// TODO: Add code
    private int a,b,m,n=0;
    
    ArrayList<Pair<String,Integer>> table;
    //StringHash<Integer> test;
    Pair<String,Integer> del;
    //IntHash<String> cnt;
    PairVector words;
    private boolean sorted;
    
    public StudentTrends() {
    	this(17,1,30000);
    }
    public StudentTrends(int a, int b, int m) {
    	this.a = a;
    	this.b = b;
    	this.m = m;
    	table = new ArrayList<Pair<String,Integer>>(Collections.nCopies(m,null));
    	//test = new StringHash<Integer>(a,b,m);
    	del = new Pair<String,Integer>("",-1);
    	//cnt = new IntHash<String>();
    	words = new PairVector();
    	sorted = false;
    }
    // Needed: insert, remove, find, printStructure, clear
    
    //re-hash after update m
    private int hash(String key) {
    	int tot=0;
    	for(int i=key.length()-1;i>=0;i--) {
    		char c = key.charAt(i);
    		tot = (256*tot+a*c)%m;
    	}
    	tot +=b;
    	return tot %m;
    }
    
    // re-hash after update m
    private void resize() {
    	if(n >= (m/2)-1) {
    		m = m*2;
    		n = 0;
    		ArrayList<Pair<String,Integer>> tmp = table;
    		table = new ArrayList<Pair<String,Integer>>(Collections.nCopies(m, null));
    		for(int i = 0; i < tmp.size(); i++) {
    			if(table.get(i) != null) {
    				Pair<String, Integer> t = tmp.get(i);
    				increaseCount(t.first, t.second);
    			}
    		}
    		table = tmp;
    	}
    }
    
    // Increase the count of String s
    // NullPointerException
	@Override
	public void increaseCount(String s, int amount) {
		// TODO Auto-generated method stub
		
//		test.insert(s, amount);
//		words.push_back(new Pair<String, Integer>(s,amount));
//		n++;
		
		resize();
		if(table.get(hash(s)) != null) {
			int i = hash(s);
			Pair<String, Integer> tmp = table.get(i);
			while(tmp != null && tmp.first.compareTo(tmp.first) != 0) {
				i = (i+1)%m;
				tmp = table.get(i);
			}
			if(tmp == null) {
				Pair<String, Integer> nu = new Pair<String, Integer>(s,amount);
				table.set(i, nu);
				words.push_back(nu);
				n++;
			} else {
				tmp.second = new Integer(tmp.second.intValue() + amount);
			}
			
			
			//table.set(i, tmp);

			
		} else {
			Pair<String, Integer> nu = new Pair<String, Integer>(s,amount);
			table.set(hash(s), nu);
			words.push_back(nu);
			//cnt.insert(amount, s);
			n++;
		}
		sorted = false;
  	}
	
	// Return the number of times s has been seen
	@Override
	public int getCount(String s) {
		// TODO Auto-generated method stub
//		if(test.find(s) == null) {
//			return 0;
//		} else {
//			return test.find(s);
//		}
		if(table.get(hash(s)) != null) {
			int i = hash(s);
			Pair<String, Integer> tmp = table.get(i);
			while(tmp != null && tmp.first.compareTo(tmp.first) != 0) {
				tmp = table.get(i);
				i = (i+1)%m;
			}
			return tmp.second;
		}
		return 0; 
	}
	
	// gets the nth most popular item
	@Override
	public String getNthPopular(int n) {
		if(n > words.size() ) {
			return "N is too big";
		}
		// Bubble Sort
		//int extra = 0;
//		for(int i = 0;i<words.size();i++) {
//			for(int j = i; j<words.size(); j++) {
//				Pair f = words.get(i);
//				Pair s = words.get(j);
//				if((Integer)s.second > (Integer)f.second) {
//					Pair<String, Integer> tmp = words.get(i);
//					words.set(i, words.get(j));
//					words.set(j, tmp);
//				} else if (s == f) {
//					
//				}
//				
//				
//			}
//		}
		
		//int extra = 0;
		if(!sorted) {
		boolean swap = false;
		for(int i = 1; i<words.size();i++) {
			for(int j = i; j>0; j--) {
				Pair s = words.get(j-1);
				Pair f = words.get(j);
				Integer a = (Integer)f.second;
				Integer b = (Integer)s.second;
				String fir = (String)f.first;
				String sec = (String)s.first;
				if(a > b) {
					Pair<String, Integer> tmp = s;
					words.set(j-1, f);
					words.set(j, tmp);
					swap = true;
					if(swap) {
						swap = false;
					} else {
						j = 0;
					}
				} else if((Integer)s.second == (Integer)f.second) {
					//String small = sec;
					if(fir.compareTo(sec) < 0) {
						Pair<String, Integer> tmp = s;
						words.set(j-1, f);
						words.set(j, tmp);
						swap = true;
					}
					if(swap) {
						swap = false;
					} else {
						j = 0;
					}
//					if(fir.length() <= sec.length()) {
//						small = fir;
//					}
//					
//					for(int k = 0; k<small.length();k++) {
//						if(fir.charAt(k) < sec.charAt(k)) {
//							Pair<String, Integer> tmp = s;
//							words.set(j-1, f);
//							words.set(j,tmp);
//							break;
//						}
//					}
//					if(fir.compareTo(sec) < 0) {
//						Pair<String, Integer> tmp = s;
//						words.set(i, f);
//						words.set(j, tmp);
//					} else if (fir.compareTo(sec) == 0 && s != f) {
//						for(int k = 0; k < fir.length();k++) {
//							if(fir.charAt(k) < sec.charAt(k)) {
//								Pair<String, Integer> tmp = s;
//								words.set(i, f);
//								words.set(j, tmp);
//								break;
//							}
//						}
//					}
				} 
				
				
			}
		}
		}
//		int extra = 0;
//		for(int i = 0; i<words.size();i++) {
//			Pair s = words.get(i);
//			Integer a = (Integer)s.second;
//			String fir = (String)s.second;
//			if(a < b) {
//				Pair<String, Integer> tmp = s;
//				for(int j = i; j>=0;j--) {
//					
//				}
//			}
//		}
		//Pair<String, Integer> tmp = words.get(n);
		//return (String)tmp.first;
		int i = n;
//		if(words.get(0).second.equals(words.get(words.size()-1).second)) {
//			i = 0;
//		}
		Pair s = words.get(i);
//		i--;
//		while(i >= 0 && s.second.equals(words.get(i).second)) {
//			s = words.get(i);
//			i--;
//		}
//		i++;
//		while(i < words.size() && s.second.equals(words.get(i).second)) {
//			String f = (String)s.first;
//			String l = (String)words.get(i).first;
//			if(f.compareTo(l) > 0) {
//				s = words.get(i);
//			}
//			i++;
//		}
		sorted = true;
		return (String)s.first;

	}
	
	// return the total # of unique strings in the list
	@Override
	public int numEntries() {
		// TODO Auto-generated method stub
		return n;
	}
	
	
}
