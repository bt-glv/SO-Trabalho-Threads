package app;

import java.util.ArrayList;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> iii = new ArrayList<Integer> ();
	
		iii.add(112331);
		iii.add(212331);
		iii.add(312331);
		iii.add(412331);
	
		iii.remove(Integer.valueOf(112331));
		System.out.println(iii.get(0));
		
	}

}
