import javax.swing.*;
import java.awt.*;

public class a1 extends JPanel {

    private static final int PANEL_SIZE = 600;
    private static final int NUM_SQUARES = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = PANEL_SIZE / 2;
        int centerY = PANEL_SIZE / 2;
        int size = PANEL_SIZE - 100; // Initial size of the largest square

        drawConcentricSquares(g2d, centerX, centerY, size, NUM_SQUARES);
    }

    private void drawConcentricSquares(Graphics2D g2d, int x, int y, int size, int numSquares) {
        if (numSquares == 0 || size <= 0) {
            return;
        }

        // Draw upright square
        drawSquare(g2d, x, y, size);

        // Draw rotated square (45 degrees)
        drawRotatedSquare(g2d, x, y, size);

        // Recursively draw smaller squares
        int nextSize = size / 2;
        drawConcentricSquares(g2d, x, y, nextSize, numSquares - 1);
    }

    private void drawSquare(Graphics2D g2d, int x, int y, int size) {
        int halfSize = size / 2;
        g2d.drawRect(x - halfSize, y - halfSize, size, size);
    }

    private void drawRotatedSquare(Graphics2D g2d, int x, int y, int size) {
        int halfSize = size / 2;
        Polygon square = new Polygon();

        square.addPoint(x, y - halfSize);
        square.addPoint(x + halfSize, y);
        square.addPoint(x, y + halfSize);
        square.addPoint(x - halfSize, y);

        g2d.drawPolygon(square);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Concentric squares (straight lines)");
        a1 panel = new a1();
        panel.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}