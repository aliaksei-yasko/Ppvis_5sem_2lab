package cookbook.ui;

import cookbook.model.CookBookManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.JFrame;
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

    public CookBookUI() {
        thisFrame = this;

        manager = new CookBookManager();
        
        this.createCommonTable();
        this.initComponent();
        this.setSize(700, 500);
        this.setLocation(100, 100);
        this.setTitle("Cook Book");
    }

    private void initComponent() {

        viewer = new JXLabel("", SwingConstants.LEFT);
        viewer.setVerticalAlignment(SwingConstants.TOP);
        viewer.setLineWrap(true);

        JScrollPane scrollPaneTable = new JScrollPane(resultTable);
        scrollPaneTable.setPreferredSize(new Dimension(150, 500));
        scrollPaneTable.setMaximumSize(new Dimension(150, 500));

        JScrollPane scrollPaneText = new JScrollPane(viewer);

        Box mainBox = Box.createVerticalBox();
        Box tableTextBox = Box.createHorizontalBox();

        tableTextBox.add(scrollPaneTable);
        tableTextBox.setPreferredSize(new Dimension(690, 500));

        tableTextBox.add(Box.createHorizontalStrut(3));
        tableTextBox.add(scrollPaneText);

        mainBox.add(tableTextBox);

        this.add(mainBox, BorderLayout.WEST);

        thisFrame.setResizable(false);
    }

    private void createCommonTable() {
        String[] columnNames = new String[]{
            "Number",
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
    }

    private void displayTable() {
    }

    private class ViewAllActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
        }
    }

    private class DeleteActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    private class NewActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NewDialog dialog = new NewDialog(thisFrame);

            dialog.setVisible(true);

            if (!dialog.getOk()) {

                return;
            }
        }
    }

    private class UpdateActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = resultTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Select function please",
                        "Selection", JOptionPane.ERROR_MESSAGE);
                return;
            }

            NewDialog dialog = new NewDialog(thisFrame);
            dialog.setVisible(true);

            if (!dialog.getOk()) {
                ViewAllActionHandler s = new ViewAllActionHandler();
                s.actionPerformed(e);
                return;
            }
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

            String selectedName = resultTable.getValueAt(selectedRow, 0).toString();

//            viewer.setText("<html>"
//                    + "<h3>FUNCTION:</h3>"
//                    + "<font color=\"red\" size=\"5\" face=\"Comic Sans MS\">"
//                    + selectedFunc + "</font><p>"
//                    + "<h3>DESCRIPTION:</h3>"
//                    + "<font size=\"4\" face=\"Comic Sans MS\">"
//                    + selectedDescr + "</font><p>"
//                    + "<h3>DECLARATION:</h3>"
//                    + "<font size=\"4\" face=\"Comic Sans MS\">"
//                    + selectedDeclar + "</font><p>"
//                    + "<h3>OBSERVATIONS:</h3>"
//                    +"<font size=\"4\" face=\"Comic Sans MS\">"
//                    + selectedObserv + "</font><p>"
//                    + "<h3>EXAMPLE:</h3>"
//                    + "<font size=\"4\" face=\"Verdana\"> "
//                    + compil
//                    + "</font></html>");

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }
}
