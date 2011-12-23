/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddRecipeDialog extends JDialog {

    private JTextField name;
    private JTextArea descr;
    private boolean ok = false;
    private JButton okButton;
    private JButton cancelButton;

    public AddRecipeDialog(JFrame owner) {
        super(owner, "New recipe", true);
        this.setSize(300, 300);
        this.setLocation(125, 125);

        name = new JTextField();
        descr = new JTextArea(10, 20);

        JScrollPane descrScroll = new JScrollPane(descr);

        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        okButton.addActionListener(new ButtonHandler());
        cancelButton.addActionListener(new ButtonHandler());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        Box vBox = Box.createVerticalBox();

        JPanel panLabel1 = new JPanel();
        panLabel1.add(new JLabel("Recipe name:"));
        vBox.add(panLabel1);
        vBox.add(name);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panLabel3 = new JPanel();
        panLabel3.add(new JLabel("Description: "));
        vBox.add(panLabel3);
        vBox.add(descrScroll);
        vBox.add(Box.createVerticalStrut(10));


        this.add(vBox, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        vBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }

    public boolean getOk() {
        return ok;
    }

    public String getDescr() {
        return name.getText();
    }

    public String getName() {
        return descr.getText();
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            if (clickedButton.getText().equals("Cancel")) {
                setVisible(false);
                return;
            } else {
                ok = true;
                setVisible(false);
                return;
            }
        }
    }
}
