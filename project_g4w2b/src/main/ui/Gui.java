package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Used code from here and modified it to fit my program https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class Gui extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField field;

    public Gui() {
        super("The best schedule goal program thingy TM");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 180));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Enter");
        JButton btn2 = new JButton("Load");
        JButton btn3 = new JButton("Save");
        btn.setActionCommand("Enter");
        btn2.setActionCommand("Load");
        btn3.setActionCommand("Save");
        btn.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere
        label = new JLabel("flag");
        field = new JTextField(5);
        add(field);
        add(btn);
        add(btn2);
        add(btn3);
        add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Enter")) {
            label.setText(field.getText());
        } else if (e.getActionCommand().equals("Load")) {
            label.setText(field.getText());
        }
    }

    public void display(String text) {
        label.setText(text);
    }

    public static void main(String[] args) {
        Gui gui = new Gui();
    }
}
