package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperationButton extends JButton implements ActionListener, FocusListener {
    private String operationName;
    private int operationValue;
    private Color focusGained = new Color(0, 103, 213);
    private Color focusLost = new Color(0, 133, 213);

    public OperationButton(String name) {
        super(name);
        this.operationName = name;
        this.setForeground(Color.white);
        this.setBackground(focusLost);
        this.addFocusListener(this);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        switch (name) {
            case "+":
                this.operationValue = 1;
                break;
            case "-":
                this.operationValue = 2;
                break;
            case "*":
                this.operationValue = 3;
                break;
            case "/":
                this.operationValue = 4;
                break;
            default:
                this.operationValue = 0;
                break;
        }
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

    public String getOperationName() {
        return this.operationName;
    }
    public int getOperationValue(){
        return this.operationValue;
    }
}
