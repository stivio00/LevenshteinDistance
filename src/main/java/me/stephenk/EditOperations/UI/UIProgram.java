package me.stephenk.EditOperations.UI;

import javax.swing.*;

public class UIProgram {
    public static void main(String[] args){
        var form = new StringDistancePane();
        var f = new JFrame("Edit distance UI");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setContentPane(new StringDistancePane().panel);
        f.pack();
        f.setVisible(true);
    }
}
