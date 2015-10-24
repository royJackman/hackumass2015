package com.umass.hackathon;
import java.util.*;

public class Method implements IClassComponent {
	// fields
	String name;
	String type;
	String input;
	ArrayList<IBodyPart> parts;
	
	public Method(String[] s) {
		parts = new ArrayList<IBodyPart>();
		
		for (int i = 0; i < s.length; i++) {
			if (s[i].equals("called")) {
				this.name = s[++i];
			} else if (s[i].equals("type")) {
				this.type = s[++i];
			} else if (s[i].equals("input")) {
				input = getInput(Arrays.copyOfRange(s, i, s.length - 1));
			}
		}
	}
	
	public String getInput(String[] s) {
		String temp = "";
		int i = 0;
		while (!s[i].equals("input")) { i++; }
		
		for (int j = i+1; j < s.length; j+=2) {
			String 
		}
		return temp;
	}
	
	public void addComponent(String[] s) {
		IBodyPart newComp = null;
		
		int i;
		for (i = 0; i < s.length; i++) {
			if (s[i].equals("variable")) {
				newComp = new Variable( Arrays.copyOfRange(s, ++i, s.length - 1) );
				break;
			}
		}
		
		if (newComp != null) {
			this.parts.add(newComp);
		}
	}
	
	
	public void print() {
		System.out.println("public " + this.type + " " + this.name + " (" + this.input + ") {");
		for (int i = 0; i < parts.size(); i++) {
			this.parts.get(i).print();
		}
		System.out.println("}");
	}
}
