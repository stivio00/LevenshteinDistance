package me.stephenk.EditOperations.UI;

import me.stephenk.EditOperations.AtomicOperation;
import me.stephenk.EditOperations.Levenshtein;
import me.stephenk.EditOperations.Operation;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.logging.Logger;

public class StringDistancePane {
    private JTextField textField1;
    private JList list1;
    private JTextField textField2;
    public JPanel panel;
    private JList list2;
    private JTextArea textArea1;
    private JLabel label1;
    private DefaultListModel<Operation> listModel;
    private DefaultListModel<AtomicOperation> listModel2;

    public StringDistancePane() {
        listModel = new DefaultListModel<>();
        listModel2 = new DefaultListModel<>();

        list1.setModel(listModel);
        list2.setModel(listModel2);

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                Logger.getLogger(getClass().getName()).info("Hey from 1");
                updateUI();
            }
        });
        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                Logger.getLogger(getClass().getName()).info("Hey from 2");
                updateUI();
            }
        });
    }

    private void updateUI() {
        String s1 = textField1.getText();
        String s2 = textField2.getText();
        label1.setText("Distance: " + Levenshtein.distance(s1, s2));
        listModel.clear();
        listModel.addAll(Levenshtein.operationsList(s1, s2));
        listModel2.clear();
        listModel2.addAll(Levenshtein.atomicOperations(s1,s2));
        StringBuilder sb = new StringBuilder();
        var m = Levenshtein.matrix(s1,s2);
        sb.append("String1: "+ s1+ " length: " + s1.length() + "\n");
        sb.append("String2: "+ s2 + " length: " + s2.length() + "\n");
        for(float[] row : m){
            sb.append(Arrays.toString(row));
            sb.append("\n");
        }

        textArea1.setText(sb.toString());

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
