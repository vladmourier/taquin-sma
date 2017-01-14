package ui;

import taquin.Taquin;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class Homepage extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner SpinnerGridSize;
    private JSpinner SpinnerNbPcs;

    public Homepage() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        SpinnerGridSize.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        SpinnerNbPcs.setModel(new SpinnerNumberModel(1, 1, 1, 1));
        SpinnerGridSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int val = (int)((JSpinner)e.getSource()).getValue();
                SpinnerNbPcs.setModel(new SpinnerNumberModel((int)SpinnerNbPcs.getModel().getValue(), 1,val*val, 1));
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
        Taquin.createGame((int)SpinnerGridSize.getValue(), (int)SpinnerNbPcs.getValue());
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
//
//    public static void main(String[] args) {
//        Homepage dialog = new Homepage();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
