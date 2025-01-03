package TicTacToe;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private JLabel heading, status;
    private JButton[] buttons = new JButton[9];
    private JPanel buttonContainer;
    private Font fontHeading = new Font("", Font.BOLD, 20);

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

    private void startGame() {
        // Ensure status is initialized before using it
        if (status != null) {
            status.setText("Player 1 Chance");
        } else {
            System.err.println("Error: Status JLabel is not initialized.");
        }
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
        }

        // Initialize status
        status = new JLabel("Status");
        status.setHorizontalAlignment(JLabel.CENTER);
        status.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create button container
        buttonContainer = new JPanel();
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        buttonContainer.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < buttons.length; i++) {
            buttonContainer.add(buttons[i]);
        }

        // Add components to the frame
        this.add(heading, BorderLayout.NORTH);
        this.add(status, BorderLayout.SOUTH);
        this.add(buttonContainer);
    }
}
