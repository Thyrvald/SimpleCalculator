package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorWindow extends JFrame {
    public CalculatorWindow() {
        super("Calculator");
        this.setDefaultCloseOperation(3);
        int scWid = Toolkit.getDefaultToolkit().getScreenSize().width,
                scHei = Toolkit.getDefaultToolkit().getScreenSize().height,
                wWid = 400,
                wHei = 500;
        this.setBounds(new Rectangle((scWid - wWid) / 2, (scHei - wHei) / 2, wWid, wHei));

        initComponents();
    }

    public void initComponents() {
        for (int i = 0; i < 100; i++) {
            numbersArray[i] = 0;
        }

        this.getContentPane().add(displayPanel, BorderLayout.NORTH);
        this.getContentPane().add(bottomPanel);
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.add(numberPanel);
        bottomPanel.add(operationPanel);

        numberPanel.setLayout(new GridLayout(4, 3));
        operationPanel.setLayout(new GridLayout(4, 2));
        displayPanel.add(outcome);
        outcome.setFont(new Font(outcome.getFont().getName(), Font.TRUETYPE_FONT, 24));

        operationPanel.add(plus);
        operationPanel.add(minus);
        operationPanel.add(mult);
        operationPanel.add(div);
        operationPanel.add(equals);
        operationPanel.add(reset);
        operationPanel.add(emptyButton1);
        operationPanel.add(emptyButton2);

        numberPanel.add(one);
        numberPanel.add(two);
        numberPanel.add(three);
        numberPanel.add(four);
        numberPanel.add(five);
        numberPanel.add(six);
        numberPanel.add(seven);
        numberPanel.add(eight);
        numberPanel.add(nine);
        numberPanel.add(emptyButton);
        numberPanel.add(zero);
        numberPanel.add(coma);

        emptyButton.setEnabled(false);
        emptyButton1.setEnabled(false);
        emptyButton2.setEnabled(false);

        one.addActionListener(numberListener);
        two.addActionListener(numberListener);
        three.addActionListener(numberListener);
        four.addActionListener(numberListener);
        five.addActionListener(numberListener);
        six.addActionListener(numberListener);
        seven.addActionListener(numberListener);
        eight.addActionListener(numberListener);
        nine.addActionListener(numberListener);
        zero.addActionListener(numberListener);

        plus.addActionListener(operationListener);
        minus.addActionListener(operationListener);
        mult.addActionListener(operationListener);
        div.addActionListener(operationListener);

        equals.addActionListener((e -> {
            boolean noMultOrDiv = true;
            for (int x : operationArray) {
                if (operationArray[x] != 3 && operationArray[x] != 4)
                    noMultOrDiv = true;
                else {
                    noMultOrDiv = false;
                    break;
                }
            }
            for (i = 0; i < 100; i++) {
                if (operationArray[i] == 1) {
                    if ((outcomeValue == 0 && i == 0) && noMultOrDiv)
                        outcomeValue += numbersArray[0];
                    outcomeValue += numbersArray[j];
                } else if (operationArray[i] == 2) {
                    outcomeValue += (numbersArray[j - 1] - numbersArray[j]);
                } else if (operationArray[i] == 3) {
                    temp = numbersArray[j - 1];
                    while (operationArray[i + 1] == 3) {
                        temp *= numbersArray[j];
                        j++;
                        i++;
                    }
                    temp *= numbersArray[j];
                    outcomeValue += temp;
                } else if (operationArray[i] == 4) {
                    temp = numbersArray[j - 1];
                    while (operationArray[i + 1] == 4) {
                        temp /= numbersArray[j];
                        j++;
                        i++;
                    }
                    temp /= numbersArray[j];
                    outcomeValue += temp;
                }
                j++;
            }

            outcome.setText("= " + outcomeValue);

            reset();
        }));

        reset.addActionListener(e -> {
            outcome.setForeground(Color.BLACK);
            outcome.setText("= ");
            outcomeValue = 0;
            reset();
        });
        coma.addActionListener((e -> {
            outcome.setText(outcome.getText() + ((JButton) e.getSource()).getText());
            if (!isComa)
                isComa = true;
        }));
    }

    //number variables

    private int i, j = 1;
    private float temp = 0;
    private float[] numbersArray = new float[100];
    private int[] operationArray = new int[100];
    private int floatIterator = 0;
    private int operationIterator = 0;
    private float outcomeValue = 0;
    private boolean isComa = false;
    private float tmp = 0;
    private double comaPower = -1;

    private void reset() {
        isComa = false;
        comaPower = -1;
        floatIterator = 0;
        operationIterator = 0;
        i = 0;
        j = 1;
        tmp = 0;
        for (int it = 0; it < 100; it++) {
            numbersArray[it] = 0;
            operationArray[it] = 0;
        }

    }

    //panels

    private JPanel bottomPanel = new JPanel();
    private JPanel numberPanel = new JPanel();
    private JPanel operationPanel = new JPanel();
    private JPanel displayPanel = new JPanel();
    private JLabel outcome = new JLabel("= ");

    //Number buttons

    private NumberButton one = new NumberButton("1", 1);
    private NumberButton two = new NumberButton("2", 2);
    private NumberButton three = new NumberButton("3", 3);
    private NumberButton four = new NumberButton("4", 4);
    private NumberButton five = new NumberButton("5", 5);
    private NumberButton six = new NumberButton("6", 6);
    private NumberButton seven = new NumberButton("7", 7);
    private NumberButton eight = new NumberButton("8", 8);
    private NumberButton nine = new NumberButton("9", 9);
    private NumberButton zero = new NumberButton("0", 0);
    private NumberButton coma = new NumberButton(".", 0);

    //Operation buttons

    private OperationButton plus = new OperationButton("+");
    private OperationButton minus = new OperationButton("-");
    private OperationButton mult = new OperationButton("*");
    private OperationButton div = new OperationButton("/");
    private OperationButton equals = new OperationButton("=");
    private OperationButton reset = new OperationButton("R");

    //Empty buttons

    private JButton emptyButton = new OperationButton("");
    private JButton emptyButton1 = new OperationButton("");
    private JButton emptyButton2 = new OperationButton("");

    //Action listeners

    private ActionListener numberListener = e -> {
        outcome.setForeground(Color.BLACK);
        outcome.setText(outcome.getText() + ((NumberButton) e.getSource()).getNumberValue());
        if (!isComa) {
            numbersArray[floatIterator] *= 10;
            numbersArray[floatIterator] += ((NumberButton) e.getSource()).getNumberValue();
        } else {
            tmp = (float) (((NumberButton) e.getSource()).getNumberValue() * Math.pow(10, comaPower));
            numbersArray[floatIterator] += tmp;
            comaPower--;
        }
    };

    private ActionListener operationListener = (e -> {
        outcome.setForeground(Color.BLACK);
        operationArray[operationIterator] = ((OperationButton) e.getSource()).getOperationValue();
        operationIterator++;
        outcome.setText(outcome.getText() + " " + ((OperationButton) e.getSource()).getOperationName() + " ");
        floatIterator++;
        if (isComa)
            isComa = false;
        comaPower = -1;
    });

    public static void main(String[] args) {
        new CalculatorWindow().setVisible(true);
    }
}
