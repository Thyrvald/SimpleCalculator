package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import calculator.CalculatorWindow;

public class NumberButton extends JButton implements ActionListener, FocusListener {
    private int numberValue;
    private String numberName;
    private Color focusGained = new Color(0, 103, 213);
    private Color focusLost = new Color(0, 133, 213);

    public NumberButton(String number, int value) {
        super(number);
        this.numberValue = value;
        this.numberName = number;
        this.setForeground(Color.white);
        this.setBackground(focusLost);
        this.addFocusListener(this);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
        this.setBackground(focusGained);
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.setBackground(focusLost);
    }

    public int getNumberValue() {
        return this.numberValue;
    }

    public String getNumberName() {
        return this.numberName;
    }
}
