import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class receptionist extends JFrame{
    JLabel label = new JLabel("");
    JPanel panel  = new JPanel();
    JPanel panel2 = new JPanel();
    int size;
    JTable table2;
    ArrayList<order> o1 = new ArrayList<order>();
    ArrayList<MenuStructure> temp = new ArrayList<MenuStructure>();

    public void make_array(ArrayList<MenuStructure> Hmenu){
        for(int i=0;i<Hmenu.size();i++){
            temp.add(Hmenu.get(i));
        }
        size = Hmenu.size();
    }

   

    public Object[][] copy(){
        Object[][] k = new Object[size][3];
        for(int i=0;i<temp.size();i++){
            k[i][0] = temp.get(i).Fid.toString();
            k[i][1] = temp.get(i).name.toString();
            k[i][2] = temp.get(i).cost.toString();
        }
        return k;
    }
    
    Object[][] menu;

    receptionist(ArrayList<MenuStructure> men){
        this.setTitle("Receptionist Interface");//this helps in setting the title of the frame
        this.setSize(1302,720);//this helps in setting the x and the y dimensions of the frame
        this.getContentPane().setBackground(new Color(0xF7E2E2));
        this.add(label);
        this.add(panel);
        this.add(panel2);
        this.setLayout(new GridLayout(3,1));
        this.setVisible(true);
        make_array(men);
        menu = copy();
        final JTable table = new JTable();
        String[] coloumn = {"Id","Name","Cost"};
        final DefaultTableModel model0 = new DefaultTableModel(menu,coloumn);
        table2 = new JTable(model0);
        table2.setFont(new Font("",Font.PLAIN, 18));
        table2.setRowHeight(22);
        Font bigFont = new Font("", Font.PLAIN, 20); // or whatever
        table2.getTableHeader().setFont(bigFont);
        JScrollPane pane0 = new JScrollPane(table2);
        pane0.setBounds(888, 0, 412, 210);
        this.setLayout(null);
        this.add(pane0);
        Object[] columns = { "ID", "Dish Name","Quantity"};
        final DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);
        table.setFont(new Font("",Font.PLAIN, 18));
        table.setRowHeight(22);
        table.getTableHeader().setFont(bigFont);
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.CYAN.brighter());
        table.setForeground(Color.black);
        Font font = new Font("", 1, 18);
        table.setFont(font);
        table.setRowHeight(30);

        // create JTextFields to hold the value
        final JTextField FoodId = new JTextField();
        final JTextField DishName = new JTextField();
        final JTextField Quantity = new JTextField();

        // create JButtons to add the action
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        JButton btnGenerate = new JButton("Generate");

        FoodId.setBounds(20, 220, 100, 25);
        DishName.setBounds(20, 250, 100, 25);
        Quantity.setBounds(20, 280, 100, 25);

        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);
        btnGenerate.setBounds(150,355,100,25);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 210);
        this.setLayout(null);
        this.add(pane);
        panel.setLayout(new GridLayout(3,1));

        // add JTextFields to the jthis
        panel.add(new JLabel("ID"));
        panel.add(FoodId);
        panel.add(new JLabel("Dish Name"));
        panel.add(DishName);
        panel.add(new JLabel("Quantity"));
        panel.add(Quantity);

        // add JButtons to the jpanel
        panel2.setLayout(new GridLayout(4,1));
        panel2.add(btnAdd);
        panel2.add(btnDelete);
        panel2.add(btnUpdate);
        panel2.add(btnGenerate);

        // create an array of objects to set the row data
        final Object[] row = new Object[4];
        // button add row - Clicked on Add Button
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = FoodId.getText();
                row[1] = DishName.getText();
                row[2] = Quantity.getText();
                
                // add row to the model
                model.addRow(row);
                order a = new order();
                a.insert(row[0],row[1],row[2]);
                o1.add(a);
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
                    o1.remove(i);
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

                FoodId.setText(model.getValueAt(i, 0).toString());
                DishName.setText(model.getValueAt(i, 1).toString());
                Quantity.setText(model.getValueAt(i, 2).toString());
            }
        });

        // button update row - Clicked on Update Button
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if (i >= 0) {
                model.setValueAt(Quantity.getText(), i, 2);
                order a = new order();
                a = o1.get(i);
                o1.remove(i);
                a.Quantity = Quantity.getText();
                o1.add(a);
                } else {
                    System.out.println("Update Error");
                }
            }
        });

        btnGenerate.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                bill t = new bill(o1,men);
                t.billg.setVisible(true);
            }

        });
        validate();
    }
    
}
