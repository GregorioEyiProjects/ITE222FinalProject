package ITE222FinalProject.V1.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClickableTextAreaExample implements MouseListener {

    private JTextArea textArea;

    public ClickableTextAreaExample() {
        JFrame frame = new JFrame("Clickable Text Area");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setText("Click on specific words for an action.\nFor example, click on 'Calculate'.");
        textArea.addMouseListener(this);

        frame.add(textArea, BorderLayout.CENTER);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();

        String selectedText = textArea.getText().substring(start, end);

        if (selectedText.equals("Calculate")) {
            // Perform calculation logic here
            JOptionPane.showMessageDialog(null, "Calculation performed!");
        } else {
            // Handle other clicked words or display an informative message
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Implement other required MouseListener methods (mousePressed, mouseReleased, etc.)

    public static void main(String[] args) {
        new ClickableTextAreaExample();
    }
}

