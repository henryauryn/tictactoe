import java.util.*;
public class TicTacToe {
    String[] values = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
    boolean finished;
    int playerTurn;

    void render() {
        System.out.println(values[0] + " | " + values[1] + " | " + values[2]);
        System.out.println(values[3] + " | " + values[4] + " | " + values[5]);
        System.out.println(values[6] + " | " + values[7] + " | " + values[8]);
    }

    void makeTurn(int playerTurn) {
        Scanner userScanner = new Scanner(System.in);
        int userInput;
        System.out.printf("It is the turn of player %d! What grid position (1-9), would you like to occupy?\n", playerTurn);
        do {
            System.out.println("Make sure the square you choose is empty!:");
            userInput = (userScanner.nextInt()) - 1;
        } while(!values[userInput].equals(" "));
        if (playerTurn == 1) {
            values[userInput] = "X";
        } else {
            values[userInput] = "O";
        }
    }

    boolean fullGrid() {
        return !Arrays.asList(values).contains(" ");
    }

    boolean anyVictories() {
        HashSet<String> subArray = new HashSet<String>();
        for (int i = 0; i < 9; i+=3) {
            subArray = new HashSet<String>();
            Collections.addAll(subArray, values[i], values[i+1], values[i+2]);
            if (subArray.size() == 1 && !subArray.contains(" ")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            subArray = new HashSet<String>();
            Collections.addAll(subArray, values[i], values[i+3], values[i+6]);
            if (subArray.size() == 1 && !subArray.contains(" ")) {
                return true;
            }
        }
        subArray = new HashSet<String>();
        Collections.addAll(subArray, values[0], values[4], values[8]);
        if (subArray.size() == 1 && !subArray.contains(" ")) {
            return true;
        }
        subArray = new HashSet<String>();
        Collections.addAll(subArray, values[2], values[4], values[6]);
        if (subArray.size() == 1 && !subArray.contains(" ")) {
            return true;
        }
        return false;

    }

    void playGame() {
        finished = false;
        playerTurn = 1;
        int stepCount = 0;
        do {
            render();
            makeTurn(playerTurn);
            stepCount++;
            if (anyVictories()) {
                stepCount = stepCount / 2;
                stepCount = (playerTurn == 1) ? stepCount + 1 : stepCount;
                System.out.printf("Well done, player %d! You won in %d steps.\n", playerTurn, stepCount);
                break;
            }
            finished = fullGrid();
            playerTurn = (playerTurn == 1) ? 2 : 1;
        } while (!finished);
        render();
    }

    public static void main(String[] args) {
        TicTacToe newGame = new TicTacToe();
        newGame.playGame();
    }
}