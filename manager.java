import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class manager extends JFrame {
    JLabel label = new JLabel("");
    JPanel panel  = new JPanel();
    JPanel panel2 = new JPanel();
    public ArrayList<MenuStructure> Hmenu = new ArrayList<MenuStructure>();
    manager(){
        this.setTitle("Manager Interface");//this helps in setting the title of the frame
        this.setSize(1302,720);//this helps in setting the x and the y dimensions of the frame
        this.getContentPane().setBackground(new Color(0xF7E2E2));
        this.add(label);
        this.add(panel);
        this.add(panel2);
        this.setLayout(new GridLayout(3,1));
        this.setVisible(true);
        final JTable table = new JTable();

        // create a table model and set a Column Identifiers to this model
        Object[] columns = { "ID", "Dish Name", "Cost"};
        final DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.CYAN.brighter());
        table.setForeground(Color.black);
        table.setModel(model);
        table.setFont(new Font("",Font.PLAIN, 25));
        table.setRowHeight(30);
        Font bigFont = new Font("", Font.PLAIN, 27);
        table.getTableHeader().setFont(bigFont);

        // create JTextFields to hold the value
        final JTextField FID = new JTextField();
        final JTextField DNAME = new JTextField();
        final JTextField DCOST = new JTextField();

        // create JButtons to add the action
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        JButton btnMonthly = new JButton("Monthly Report");
        JButton btnStatistical = new JButton("Statistical Report");

        FID.setBounds(20, 220, 100, 25);
        DNAME.setBounds(20, 250, 100, 25);
        DCOST.setBounds(20, 280, 100, 25);

        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1320, 210);
        this.setLayout(null);
        this.add(pane);
        panel.setLayout(new GridLayout(3,1));

        // add JTextFields to the jthis
        panel.add(new JLabel("Food Id"));
        panel.add(FID);
        panel.add(new JLabel("Dish Name"));
        panel.add(DNAME);
        panel.add(new JLabel("Dish Cost"));
        panel.add(DCOST);

        // add JButtons to the jpanel
        panel2.setLayout(new GridLayout(1,5));
        panel2.add(btnAdd);
        panel2.add(btnDelete);
        panel2.add(btnUpdate);
        panel2.add(btnMonthly);
        panel2.add(btnStatistical);

        // create an array of objects to set the row data
        final Object[] row = new Object[4];

        // button add row - Clicked on Add Button
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = FID.getText();
                row[1] = DNAME.getText();
                row[2] = DCOST.getText();
                model.addRow(row);
                MenuStructure a = new MenuStructure();
                a.insert(row[0],row[1],row[2]);
                Hmenu.add(a);
            }
        });

        // button remove row - Clicked on Delete Button
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            // i = the index of the selected row
                int i = table.getSelectedRow();
                if (i >= 0) {
                // remove a row from jtable
                    model.removeRow(i);
                    Hmenu.remove(i);
                } else{
                    System.out.println("There were issue while Deleting the Row(s).");
                }
            }
        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // i = the index of the selected row
                int i = table.getSelectedRow();

                FID.setText(model.getValueAt(i, 0).toString());
                DNAME.setText(model.getValueAt(i, 1).toString());
                DCOST.setText(model.getValueAt(i, 2).toString());
            }
        });

        // button update row - Clicked on Update Button
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if (i >= 0) {
                model.setValueAt(FID.getText(), i, 0);
                model.setValueAt(DNAME.getText(), i, 1);
                model.setValueAt(DCOST.getText(), i, 2);
                MenuStructure a = new MenuStructure();
                a = Hmenu.get(i);
                Hmenu.remove(i);
                a.Fid = FID.getText();
                a.name = DNAME.getText();
                a.cost = DCOST.getText();
                Hmenu.add(a);
                } else {
                System.out.println("Update Error");
                }
            }
        });

        btnMonthly.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                monthly m = new monthly(Hmenu);
            }
        });

        btnStatistical.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Statistical s = new Statistical(Hmenu);            }
        });

        validate();
    }
}


