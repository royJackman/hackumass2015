package com.umass.hackathon;
import java.lang.*;

public class TestHierarchy {

	public static void main(String[] args) {
		String s = "do {} while b = 2";
		PrgmControlStructure test = new PrgmControlStructure( s.split(" ") );
		test.printBodyPart();
	}

}
