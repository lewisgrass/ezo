package com.gmail.danslclo;

import java.io.Console;

public class Golf {
	
	public static void main(String[] args) {
		// get the System console object
		Console console = System.console();
		String userInput = null;
		if(console == null) {
			System.err.println("Cannot retrive console object - are you running your application from an IDE? Exiting the application ... ");
			System.exit(1); // terminate the application
		}
		boolean newOperation = true;
		while(newOperation) {
			userInput = console.readLine("Entrez une opération mathématique.  Tapez exit pour sortir\n").trim();
			if(userInput.isEmpty()) {
				continue;
			}
			if(userInput.equals("exit")) {
				break;
			}
			if(!Parsor.isOperationValid(userInput)) {
				System.out.println("y'a des caractères invalides");
				continue;
			}
			
			try {
				System.out.println(Calculator.parseSimpleOperation(userInput) + "");
			} catch (ZeroDivisionError e) {
				System.err.println("Erreur: division par zéro");
			} catch (IncompleteOperationError e) {
				System.err.println("Erreur: Opération incomplète");
			}
		}
	}
}
