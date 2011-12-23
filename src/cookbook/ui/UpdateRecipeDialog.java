/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook.ui;

import cookbook.model.Advice;
import cookbook.model.Category;
import cookbook.model.CookBookManager;
import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UpdateRecipeDialog extends JDialog {

    private JTextField name;
    private JTextArea descr;
    private JTextArea ingr;
    private JTextArea adv;
    private JComboBox category;
    private boolean ok = false;
    private JButton okButton;
    private JButton cancelButton;

    public UpdateRecipeDialog(JFrame owner, Recipe recipe) {
        super(owner, "New recipe", true);
        this.setSize(300, 600);
        this.setLocation(125, 125);

        name = new JTextField();
        descr = new JTextArea(10, 20);
        ingr = new JTextArea(10, 20);
        adv = new JTextArea(10, 20);

        CookBookManager manager = new CookBookManager();

        JScrollPane descrScroll = new JScrollPane(descr);
        JScrollPane ingrScroll = new JScrollPane(ingr);
        JScrollPane advScroll = new JScrollPane(adv);

        List<String> cat = new ArrayList<String>();        
        for (Category x : manager.getAllCategory()) {
            cat.add(x.getName());
        }
        
        category = new JComboBox(cat.toArray());

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
        name.setText(recipe.getName());
        vBox.add(name);
        vBox.add(Box.createVerticalStrut(10));

        JPanel panLabel2 = new JPanel();
        panLabel2.add(new JLabel("Description: "));
        vBox.add(panLabel2);
        descr.setText(recipe.getDescription());
        vBox.add(descrScroll);
        vBox.add(Box.createVerticalStrut(10));

        JPanel panLabel3 = new JPanel();
        panLabel3.add(new JLabel("Ingredients: (FORMAT: name quantity dimension;)"));
        vBox.add(panLabel3);

        StringBuilder str = new StringBuilder();
        for (Ingredient x : recipe.getIngridients()) {
            str.append(x.getName() + " " + x.getQuantity() + "" + x.getDimension() + "\n");
        }
        ingr.setText(str.toString());

        vBox.add(ingrScroll);
        vBox.add(Box.createVerticalStrut(10));

        JPanel panLabel4 = new JPanel();
        panLabel4.add(new JLabel("Advice: (FORMAT: advice;)"));
        vBox.add(panLabel4);

        str = new StringBuilder();
        for (Advice x : recipe.getAdvices()) {
            str.append(x.getDescription() + "\n");
        }
        adv.setText(str.toString());

        vBox.add(advScroll);
        vBox.add(Box.createVerticalStrut(10));

        this.add(vBox, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        vBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }

    public boolean getOk() {
        return ok;
    }

    public String getRecipeName() {
        return name.getText();
    }

    public String getDescr() {
        return descr.getText();
    }

    public String getCategory() {
        return (String) category.getSelectedItem();
    }

    public String[] getIngr() {
        return ingr.getText().split("\n ");
    }

    public String[] getAdv() {
        return adv.getText().split("\n ");
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
