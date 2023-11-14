package utilities;

import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		int max = 100;
		System.out.println(AutoString.autoID(input));
	
//		System.out.println(input +(String.format("%04d", max)));
		
//		System.out.println(input.replaceAll(" ", ""));
//		String[] output = input.split(" ");
//		System.out.println(output[2]);
//		
//		String kho = "";
//		for (int i = 0; i < output.length - 1; i++) {
//			
//			kho += output[i].substring(0, 1);
//			
//		}
//		System.out.println(kho);
		
		
		//System.out.println(AutoString.autoEmail("Nguyen Minh Trieu"));
	}

}
