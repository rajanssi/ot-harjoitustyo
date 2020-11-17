package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// UI and main entry in same class, for now
public class Sudoku extends JFrame {

    // Size of the board
    public static final int GRID_SIZE = 9;
    // Name-constants for UI control (sizes, colors and fonts)
    public static final int CELL_SIZE = 60;   // Cell width/height in pixels
    public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
    public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;
    // Board width/height in pixels
    public static final Color OPEN_CELL_BGCOLOR = Color.YELLOW;
    public static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0);
    public static final Color OPEN_CELL_TEXT_NO = Color.RED;
    public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240);
    public static final Color CLOSED_CELL_TEXT = Color.BLACK;
    public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);
    private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];
    // Hardcoded puzzle, for testing
    private int[][] puzzle = Puzzle.getCells();
    private boolean[][] masks = Puzzle.getMasks();

    // Setup Sudoku grid UI
    public Sudoku() {
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        
        InputListener listener = new InputListener();

        for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
                tfCells[row][col] = new JTextField();
                cp.add(tfCells[row][col]);
                if (masks[row][col]) {
                    tfCells[row][col].setText("");
                    tfCells[row][col].setEditable(true);
                    tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
                    tfCells[row][col].addActionListener(listener);
                } else {
                    tfCells[row][col].setText(puzzle[row][col] + "");
                    tfCells[row][col].setEditable(false);
                    tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
                    tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
                }
                tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
                tfCells[row][col].setFont(FONT_NUMBERS);
            }
        }

        cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setVisible(true);

    }

    // Main entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Sudoku::new);
    }

    // Handle input in Sudoku cells
    private class InputListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int rowSelected = -1;
            int colSelected = -1;
            
            JTextField source = (JTextField) e.getSource();
            // Scan JTextFileds for all rows and columns, and match with the source object
            boolean found = false;
            for (int row = 0; row < GRID_SIZE && !found; ++row) {
                for (int col = 0; col < GRID_SIZE && !found; ++col) {
                    if (tfCells[row][col] == source) {
                        rowSelected = row;
                        colSelected = col;
                        found = true;
                    }
                }
            }
            // Validate input
            int inputNumber;
            try {
                inputNumber = Integer.parseInt(tfCells[rowSelected][colSelected].getText());
            } catch (NumberFormatException ex) {
                inputNumber = 0;
            }
            // Check if correct number
            if (inputNumber == puzzle[rowSelected][colSelected]) {
                tfCells[rowSelected][colSelected].setBackground(Color.GREEN);
                masks[rowSelected][colSelected] = false;
            } else {
                tfCells[rowSelected][colSelected].setBackground(Color.RED);
                masks[rowSelected][colSelected] = true;
            }

            // Check if Sudoku is complete
            boolean completed = true;
            for (int row = 0; row < GRID_SIZE && completed; ++row) {
                for (int col = 0; col < GRID_SIZE && completed; ++col) {
                    if (masks[row][col] == true) {
                        completed = false;
                    }
                }
            }
            if (completed) {
                JOptionPane.showMessageDialog(null, "Voitit pelin!");
            }
        }
    }
}
