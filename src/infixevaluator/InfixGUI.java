/*
 * Matthew Towles
 * 01/20/2019
 * GUI and Main class
 */
package infixevaluator;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * GUI and Main class
 * Creates GUI when program is initialized
 * Expects user input with valid infix equation
 * One event listener - button
 * When button pressed, all calculations are performed
 * Result is output to the GUI
 * 
 * @author matthew.towles
 */
public class InfixGUI extends JFrame {
    
    private JLabel infixLabel;
    private JTextField infixField;
    private JButton evaluateButton;
    private JLabel resultLabel;
    private JTextArea resultText;
    
    /**
     * Constructor - calls method to initialize GUI
     */
    public InfixGUI() {
        init();
    }
    
    /**
     * Initialize the GUI and its fields
     */
    private void init() {
        infixLabel = new JLabel();
        infixField = new JTextField();
        evaluateButton = new JButton();
        resultLabel = new JLabel();
        resultText = new JTextArea();
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        infixLabel.setText("Enter infix expression: ");
        resultLabel.setText("Result: ");
        evaluateButton.setText("Evaluate");
        evaluateButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ev) {
               evaluateButtonActionPerformed(ev);
           } 
        });
        
        // make the GUI:
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
                layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(
                        layout.createParallelGroup(
                            GroupLayout.Alignment.LEADING
                        )
                        .addComponent(infixLabel)
                        .addComponent(resultLabel)
                    )
                    .addGap(18, 18, 18)
                    .addGroup(
                        layout.createParallelGroup(
                            GroupLayout.Alignment.LEADING, false
                        )
                        .addComponent(evaluateButton)
                        .addComponent(infixField, 
                                GroupLayout.DEFAULT_SIZE, 
                                223, 
                                Short.MAX_VALUE
                        )
                        .addComponent(resultText, 
                                GroupLayout.Alignment.TRAILING, 
                                GroupLayout.DEFAULT_SIZE, 
                                GroupLayout.DEFAULT_SIZE, 
                                Short.MAX_VALUE
                        )
                    )
                    .addContainerGap(14, Short.MAX_VALUE)
            )
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                    layout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE
                    )
                    .addComponent(infixLabel)
                    .addComponent(infixField, 
                            GroupLayout.PREFERRED_SIZE, 
                            GroupLayout.DEFAULT_SIZE, 
                            GroupLayout.PREFERRED_SIZE
                    )
                )
                .addGap(18, 18, 18)
                .addComponent(evaluateButton)
                .addGap(18, 18, 18)
                .addGroup(
                    layout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE
                    )
                    .addComponent(resultLabel)
                    .addComponent(resultText)
                )
                .addContainerGap(186, Short.MAX_VALUE)
            )
        );
        pack();
    }
    
    /**
     * Event listener for evaluate button
     * Creates InfixEvaluator and evaluates user input
     * Then outputs result in resultText on GUI
     * 
     * @param ev 
     */
    private void evaluateButtonActionPerformed(ActionEvent ev) {
        try {
            InfixEvaluator ie = new InfixEvaluator(this.infixField.getText());
            this.resultText.setText(Integer.toString(ie.process()));
        } catch (DivideByZero ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(), 
                    ex.toString(), 
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    ex.toString(),
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    ex.toString(),
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    /**
     * Main
     * @param args 
     */
    public static void main(String[] args) {        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InfixGUI().setVisible(true);
            }
        });
    }
}
