package cookbook.ui;

import cookbook.model.Advice;
import cookbook.model.Category;
import cookbook.model.CookBookManager;
import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXLabel;

public class CookBookUI extends JFrame {

    private JTable resultTable;
    private DefaultTableModel resultTabelModel;
    private JXLabel viewer;
    private JFrame thisFrame;
    private CookBookManager manager;
    private JMenu recipeMenu;
    private JMenu searchMenu;
    private JComboBox category;

    public CookBookUI() {
        thisFrame = this;

        manager = new CookBookManager();

        this.createCommonTable();
        this.initComponent();
        this.setSize(800, 600);
        this.setLocation(100, 100);
        this.setTitle("Cook Book");

    }

    private void initComponent() {
        viewer = new JXLabel("", SwingConstants.LEFT);
        viewer.setVerticalAlignment(SwingConstants.TOP);
        viewer.setLineWrap(true);

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        List<String> cat = new ArrayList<String>();
        cat.add("All");
        for (Category x : manager.getAllCategory()) {
            cat.add(x.getName());
        }

        category = new JComboBox(cat.toArray());
        category.addActionListener(new CategoryViewActionListener());

        recipeMenu = new JMenu("Recipe");
        menuBar.add(recipeMenu);
        JMenuItem addRecipeMenuItem = new JMenuItem("Add recipe");
        addRecipeMenuItem.addActionListener(new AddRecipeMenuItemHandler());
        recipeMenu.add(addRecipeMenuItem);
        JMenuItem deleteRecipeMenuItem = new JMenuItem("Delete recipe");
        deleteRecipeMenuItem.addActionListener(new DeleteRecipeMenuItemHandler());
        recipeMenu.add(deleteRecipeMenuItem);
        JMenuItem updateRecipeMenuItem = new JMenuItem("Update recipe");
        updateRecipeMenuItem.addActionListener(new UpdateRecipeMenuItemHandler());
        recipeMenu.add(updateRecipeMenuItem);

        searchMenu = new JMenu("Search");
        menuBar.add(searchMenu);
        JMenuItem searchByNameMenuItem = new JMenuItem("Search by name");
        searchMenu.add(searchByNameMenuItem);
        searchByNameMenuItem.addActionListener(new SearchByStrMenuItemHandler());
        JMenuItem searchByIngridientMenuItem = new JMenuItem("Search by ingridient");
        searchMenu.add(searchByIngridientMenuItem);
        searchByIngridientMenuItem.addActionListener(new SearchByCategoryMenuItemHandler());
        
        JScrollPane scrollPaneTable = new JScrollPane(resultTable);
        scrollPaneTable.setPreferredSize(new Dimension(320, 600));
        scrollPaneTable.setMaximumSize(new Dimension(320, 600));

        JScrollPane scrollPaneText = new JScrollPane(viewer);

        Box mainBox = Box.createVerticalBox();
        Box tableTextBox = Box.createHorizontalBox();

        tableTextBox.add(scrollPaneTable);
        tableTextBox.setPreferredSize(new Dimension(790, 600));

        tableTextBox.add(Box.createHorizontalStrut(3));
        tableTextBox.add(scrollPaneText);

        Box comboBox = Box.createHorizontalBox();
        comboBox.add(category, BorderLayout.WEST);

        comboBox.setPreferredSize(new Dimension(300, 20));
        comboBox.setMaximumSize(new Dimension(300, 20));

        mainBox.add(tableTextBox);
        mainBox.add(comboBox);

        this.add(mainBox, BorderLayout.WEST);

        thisFrame.setResizable(false);

        this.displayTable(manager.getAllRecipes());
    }

    private void createCommonTable() {
        String[] columnNames = new String[]{
            "№",
            "Name"
        };

        resultTabelModel = new DefaultTableModel(columnNames, 0) {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        resultTable = new JTable(resultTabelModel);
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resultTable.setAutoCreateRowSorter(true);
        resultTable.addMouseListener(new TableClicked());
        resultTable.getColumnModel().getColumn(0).setMinWidth(25);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        resultTable.getColumnModel().getColumn(1).setMinWidth(292);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(292);
    }

    private void displayTable(List<Recipe> toDisplay) {
        if (manager.getAllRecipes() == null) {
            return;
        }

        while (resultTabelModel.getRowCount() > 0) {
            resultTabelModel.removeRow(0);
        }
        String[] mas = new String[2];
        for (Recipe recipe : toDisplay) {
            mas[0] = Integer.toString(recipe.getNumber());
            mas[1] = recipe.getName();
            resultTabelModel.addRow(mas);
        }

    }

    private class CategoryViewActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (category.getSelectedItem().toString().equals("All")) {
                displayTable(manager.getAllRecipes());
            } else {
                displayTable(manager.selectByCategory(category.getSelectedItem().toString()));
            }
        }
    }

    private class DeleteRecipeMenuItemHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (resultTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Select recipe please",
                        "Selection", JOptionPane.ERROR_MESSAGE);
                return;
            }
            manager.deleteRecipe(resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString());
            displayTable(manager.selectByCategory(category.getSelectedItem().toString()));
        }
    }

    private class AddRecipeMenuItemHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AddRecipeDialog dialog = new AddRecipeDialog(thisFrame);

            dialog.setVisible(true);

            if (!dialog.getOk()) {
                return;
            }

            Recipe recipe = new Recipe();
            recipe.setName(dialog.getRecipeName());
            recipe.setDescription(dialog.getDescr());
            recipe.setCategory(new Category(dialog.getCategory()));

            for (String x : dialog.getIngr()) {
                String[] y = x.split(" ");
                recipe.addIngridient(new Ingredient(y[0], y[1], y[2]));
            }

            for (String x : dialog.getAdv()) {
                recipe.addAdvice(new Advice(x));
            }

            manager.addNewRecipe(recipe);
            displayTable(manager.selectByCategory(category.getSelectedItem().toString()));
        }
    }

    private class UpdateRecipeMenuItemHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = resultTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Select recipe please",
                        "Selection", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Recipe recipe = manager.getRecipeByName(resultTable.getValueAt(selectedRow, 1).toString());
            UpdateRecipeDialog dialog = new UpdateRecipeDialog(thisFrame, recipe);
            dialog.setVisible(true);

            if (!dialog.getOk()) {
                return;
            }

            recipe.setName(dialog.getRecipeName());
            recipe.setDescription(dialog.getDescr());
            recipe.setCategory(new Category(dialog.getCategory()));

            recipe.getIngridients().clear();
            for (String x : dialog.getIngr()) {
                String[] y = x.split(" ");
                recipe.addIngridient(new Ingredient(y[0], y[1], y[2]));
            }

            recipe.getAdvices().clear();
            for (String x : dialog.getAdv()) {
                recipe.addAdvice(new Advice(x));
            }

            displayTable(manager.selectByCategory(category.getSelectedItem().toString()));
        }
    }

    private class TableClicked implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int selectedRow = resultTable.getSelectedRow();

            if (selectedRow == -1) {
                return;
            }

            String selectedName = resultTable.getValueAt(selectedRow, 1).toString();
            Recipe recipe;
            recipe = manager.getRecipeByName(selectedName);

            StringBuilder str = new StringBuilder();
            str.append("<html>"
                    + "<h3><font color=\"blue\">NAME:</font></h3>"
                    + "<font color=\"red\" size=\"5\" face=\"Comic Sans MS\">"
                    + recipe.getName().toUpperCase() + "</font><p>"
                    + "<h3><font color=\"blue\">DESCRIPTION:</font></h3>"
                    + "<font size=\"4\" >"
                    + recipe.getDescription() + "</font>"
                    + "<h3><font color=\"blue\">INGREDIENTS:</font></h3>"
                    + "<font size=\"4\" >");

            for (Ingredient ingr : recipe.getIngridients()) {
                str.append(ingr.getName() + " " + ingr.getQuantity() + ""
                        + ingr.getDimension() + "<p>");
            }

            str.append("</font>"
                    + "<h3><font color=\"blue\">CATEGORY:</font></h3>"
                    + "<font size=\"4\" >"
                    + recipe.getCategory().getName() + "</font>"
                    + "<h3><font color=\"blue\">ADVICES:</font></h3>"
                    + "<font size=\"4\" >");

            for (Advice adv : recipe.getAdvices()) {
                str.append(adv.getDescription() + "<p>");
            }

            str.append("</font></html>");
            viewer.setText(str.toString());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }

    private class SearchByStrMenuItemHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Input piece of name:");
            List<Recipe> list = manager.selectByStr(name);
            displayTable(list);
        }

    }

    private class SearchByCategoryMenuItemHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Input including ingridient:");
            List<Recipe> list = manager.getRecipesByIngredient(name);
            displayTable(list);
        }

    }
}
