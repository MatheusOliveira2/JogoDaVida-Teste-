package main;

import java.util.Scanner;

public class JogoDaVida {
	public static void main(String[] args) {
		Board game = new Board();
		game.startRandom();
		game.printBoard();
		
		int command = 0;
		do {
			System.out.println("Informe o próximo comando");
			
			System.out.println("1 - Próximo Passo");
			System.out.println("2 - Finalizar");
			
			Scanner scanner = new Scanner(System.in);
			command = scanner.nextInt();
			
			switch (command) {
			case 1: 
				game.step();
				game.printBoard();
				break;
			case 2:
				System.out.println("Finalizado");
				scanner.close();
				break;
			default:
				break;
			
			}
		}while(command != 2);
	}
}
