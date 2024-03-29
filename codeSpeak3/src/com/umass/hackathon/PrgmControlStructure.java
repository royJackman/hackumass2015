package com.umass.hackathon;
import java.util.*;
import java.lang.String;

public class PrgmControlStructure implements IBodyPart {

	String mode;
	IClauseComponent[] clauses;
	ArrayList<IBodyPart> body; 
	
	PrgmControlStructure(String[] s) {
		this.mode = "";
		body = BodyBuilder.buildBody(s);
		makeIClause(s);
	}
	
	private void makeIClause(String[] s) {
		int index = 0;
		
		switch (s[index].toLowerCase()) {
			case "if":
			case "while":
			case "for":
			case "do":
				this.mode = s[index].toLowerCase();
				index++;
				break;
			case "else":
				this.mode = s[index].toLowerCase();
				index++;
				if (s[index].equals("if")) {
					this.mode += " if";
					index++;
				}
				break;
			default:
				break;
		}
		
		switch (this.mode) {
			case "if":
			case "else if":
			case "else":
			case "while":
				clauses = new IClauseComponent[1];
				clauses[0] = ClauseCreator.buildIClauseComponent( Arrays.copyOfRange(s, index, s.length));
				break;
			case "do":	
				for (index = s.length - 1; index >= 0; index--) {
					if (s[index].equals("while")) {
						break;
					}
				}
				clauses = new IClauseComponent[1];
				clauses[0] = ClauseCreator.buildIClauseComponent( Arrays.copyOfRange(s, index + 1, s.length));
				break;
			case "for":
				clauses = new IClauseComponent[3];
				
				int[] for_indexes = for_Indexes(s);
				
				clauses[0] = ClauseCreator.buildIClauseComponent( Arrays.copyOfRange( s, index, for_indexes[0]));
				clauses[1] = ClauseCreator.buildIClauseComponent( Arrays.copyOfRange( s, for_indexes[0] + 1, for_indexes[1]));
				clauses[2] = ClauseCreator.buildIClauseComponent( Arrays.copyOfRange( s, for_indexes[1] + 1, s.length));
				
			default:
				clauses = null;
				break;
		}
		

	}
	
	private int[] for_Indexes(String[] s) {
		int[] indexes = new int[2];
		for (int i = 0; i < s.length; i++) {
			if (s[i].toLowerCase().equals("while")) {
				indexes[0] = i;
			} else if (s[i].toLowerCase().equals("and")) {
				indexes[1] = 1;
			}
		}
		return indexes;
	}
	
	public void printBodyPart() {
		switch (this.mode) {
			case "if":
			case "else if":
			case "else":
			case "while":
				System.out.println(this.mode + this.clauses[0].printIClauseComponent() + " {");
				for (int i = 0; i < body.size(); i++) {
					body.get(i).printBodyPart();
				}
				System.out.println("}");
				break;
			case "do":
				System.out.println(this.mode + "{");
				for (int i = 0; i < body.size(); i++) {
					body.get(i).printBodyPart();
				}
				System.out.println("} while " + this.clauses[0].printIClauseComponent() + ";" );
				break;
			case "for":
				System.out.print(this.mode + "(");
				System.out.print(this.clauses[0].printIClauseComponent() + "; " 
									+ this.clauses[1].printIClauseComponent() + "; "
									+ this.clauses[2].printIClauseComponent() + " ) {");
				for (int i = 0; i < body.size(); i++) {
					body.get(i).printBodyPart();
				}
				System.out.println("}");
			default:
				break;
		}
	}
}
