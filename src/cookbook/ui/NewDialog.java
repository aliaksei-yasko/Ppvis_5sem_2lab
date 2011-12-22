/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook.ui;

import java.awt.BorderLayout;
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

public class NewDialog extends JDialog {

    private JTextField name;
    private JTextArea descr;
    private boolean ok = false;
    private JButton okButton;

    public NewDialog(JFrame owner) {
        super(owner, "New function", true);
        this.setSize(300, 600);
        this.setLocation(125, 125);
        
        name = new JTextField();
        descr = new JTextArea(10, 50);

        JScrollPane descrScroll = new JScrollPane(descr);

        okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        Box vBox = Box.createVerticalBox();

        JPanel panLabel1 = new JPanel();
        panLabel1.add(new JLabel("Receipe name:"));
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
}
