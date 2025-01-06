package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Game extends JFrame implements ActionListener {

    private JLabel heading, status;
    private JButton[] buttons = new JButton[9];
    private JPanel buttonContainer;
    private Font fontHeading = new Font("", Font.BOLD, 20);

    // for game logic
    boolean isWinner = false;
    int currentPlayer = 0;
    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winingConditions = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    // Constructor
    Game(String title) {
        setTitle(title);

        // Create GUI components
        createComponent();

        // Start the game after initializing components
        startGame();

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createComponent() {
        // Initialize heading
        heading = new JLabel("Tic Tac Toe");
        heading.setFont(fontHeading);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setPreferredSize(new Dimension(90, 60));
            buttons[i].setFont(fontHeading);
            buttons[i].setName(i + "");
            buttons[i].addActionListener(this);
        }

        // Initialize status
        status = new JLabel("Status");
        status.setHorizontalAlignment(JLabel.CENTER);
        status.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create button container
        buttonContainer = new JPanel();
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        buttonContainer.setLayout(new GridLayout(3, 3));
        for (JButton button : buttons) {
            buttonContainer.add(button);
        }

        // Add components to the frame
        this.add(heading, BorderLayout.NORTH);
        this.add(status, BorderLayout.SOUTH);
        this.add(buttonContainer);
    }


    private void startGame() {
        // Ensure status is initialized before using it
        if (status != null) {
            status.setText("Player 0 Chance");
        } else {
            System.err.println("Error: Status JLabel is not initialized.");
        }
        Arrays.fill(gameState, -1);
        for (JButton button : buttons) {
            button.setText("");
        }
        currentPlayer = 0;
        isWinner = false;
    }

    // Button click
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        int currentPosition = Integer.parseInt(sourceButton.getName());

// check for position
        if (gameState[currentPosition] == -1) {
// current position is vacant
            JButton currentButton = buttons[currentPosition];
            setCurrentPositionVale(currentPosition);
            int winner = checkForWinner();
            if (winner >= 0) {
                JOptionPane.showMessageDialog(this, winner + " has won the game");
                int x = JOptionPane.showConfirmDialog(this, "Restart the game");
                System.out.println(x);
                if (x == 0) {
                    startGame();
                }

            }

            boolean result = checkForDraw();
            if (result) {
                JOptionPane.showMessageDialog(this, "Match Draw");
                int x = JOptionPane.showConfirmDialog(this, "Restart the game");
                System.out.println(x);
                if (x == 0) {
                    startGame();
                }
            }


        } else {
            JOptionPane.showMessageDialog(this, "Current Position is not empty");


        }

    }

    private int checkForWinner() {
        int winner = -1;
        for (int[] winnerArray : winingConditions) {

            if ((gameState[winnerArray[0]] == gameState[winnerArray[1]] && gameState[winnerArray[1]] == gameState[winnerArray[2]]) && gameState[winnerArray[0]] != -1) {

                isWinner = true;
                winner = gameState[winnerArray[0]];
                break;
            }

        }
        return winner;
    }

    // match draw wala logic 
    private boolean checkForDraw() {
        boolean isAnyFieldLeft = false;
        for (int state : gameState) {
            if (state == -1) {
                isAnyFieldLeft = true;
            }
        }
        if (!isAnyFieldLeft && !isWinner) {
            return true;
        } else {
            return false;
        }
    }

    private void setCurrentPositionVale(int currentPosition) {
        JButton currentButton = buttons[currentPosition];
        if (currentPlayer == 0) {
            currentButton.setText("0");
            //currentButton.setIcon(new ImageIcon("src/TicTacToe/0.png"));
            gameState[currentPosition] = 0;
            status.setText("Player 1 chance");
            currentPlayer = 1;
        } else {
            currentButton.setText("1");
            //currentButton.setIcon(new ImageIcon("src/TicTacToe/one.png"));
            gameState[currentPosition] = 1;
            status.setText("Player 0 chance");
            currentPlayer = 0;
        }
        System.out.println(Arrays.toString(gameState));

    }
}
