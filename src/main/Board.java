package main;

import java.util.Random;

public class Board {

	public final static int dead = 0;
	public final static int alive = 1;

	private final int width = 6;
	private final int heigth = 6;

	int[][] board;

	public Board() {
		this.board = new int[width][heigth];
	}

	public void printBoard() {
		System.out.println("---");
		for (int y = 0; y < heigth; y++) {
			String line = "|";
			for (int x = 0; x < width; x++) {
				if (this.board[x][y] == dead) {
					line += "0";
				} else {
					line += "1";
				}
			}
			line += "|";
			System.out.println(line);
		}
		System.out.println("---\n");
	}

	public void startRandom() {
		Random random = new Random();
		double valor;
		for (int y = 0; y < this.heigth; y++) {
			for (int x = 0; x < this.width; x++) {
				valor = Math.round(random.nextDouble());
				if(valor == 0) {
					this.setState(x, y, dead);
				}
				else {
					this.setState(x, y, alive);
				}
				
			}
		}
	}

	public void setState(int x, int y, int state) {
		if (x < 0 || x >= width) {
			return;
		}

		if (y < 0 || y >= heigth) {
			return;
		}

		this.board[x][y] = state;
	}

	public int countVizinhasalives(int x, int y) {
		int count = 0;

		count += getState(x - 1, y - 1);
		count += getState(x, y - 1);
		count += getState(x + 1, y - 1);

		count += getState(x - 1, y);
		count += getState(x + 1, y);

		count += getState(x - 1, y + 1);
		count += getState(x, y + 1);
		count += getState(x + 1, y + 1);

		return count;
	}

	public int getState(int x, int y) {
		if (x < 0 || x >= width) {
			return dead;
		}

		if (y < 0 || y >= heigth) {
			return dead;
		}

		return this.board[x][y];
	}

	public void step() {
		int[][] newBoard = new int[width][heigth];

		for (int y = 0; y < heigth; y++) {
			for (int x = 0; x < width; x++) {
				int alives = countVizinhasalives(x, y);
				//Qualquer célula viva com menos de dois vizinhos vivos morre de solidão.
				if (getState(x, y) == alive) {
					if (alives < 2) {
						newBoard[x][y] = dead;
						//Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.
					} else if (alives == 2 || alives == 3) {
						newBoard[x][y] = alive;
						//Qualquer célula viva com mais de três vizinhos vivos morre de superpopulação.
					} else if (alives > 3) {
						newBoard[x][y] = dead;
					}
					//Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.
				} else {
					if (alives == 3) {
						newBoard[x][y] = alive;
					}
				}

			}
		}

		this.board = newBoard;
	}

}
