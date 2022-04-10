import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class chef extends JFrame{
    JLabel label = new JLabel("");
    JPanel panel  = new JPanel();
    JPanel panel2 = new JPanel();
    int size;
    JTable table2;
    ArrayList<Ingredient> o1 = new ArrayList<Ingredient>();
    public static int total_cost = 0;
    
    Object[][] menu;

    public Object[][] copy(ArrayList<order> temp){
        Object[][] k = new Object[temp.size()][3];
        for(int i=0;i<temp.size();i++){
            k[i][0] = temp.get(i).Id.toString();
            k[i][1] = temp.get(i).DishName.toString();
            k[i][2] = temp.get(i).Quantity.toString();
        }
        return k;
    }

    chef(){
        
    }

    chef(ArrayList<order> men){
        this.setTitle("Chef Interface");//this helps in setting the title of the this
        this.setSize(1302,720);//this helps in setting the x and the y dimensions of the this
        this.getContentPane().setBackground(new Color(0xF7E2E2));
        this.add(label);
        this.add(panel);
        this.add(panel2);
        this.setLayout(new GridLayout(3,1));
        this.setVisible(true);
        final JTable table = new JTable();
        menu = copy(men);
        String[] coloumn = {"Id","Name","Quantity"};
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
        Object[] columns = { "ID", "Ingrediant Name","Quantity","Cost"};
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
        final JTextField IngredientName = new JTextField();
        final JTextField Cost = new JTextField();
        final JTextField Quantity = new JTextField();

        // create JButtons to add the action
        JButton btnbuy = new JButton("buy");
        JButton btnUpdate = new JButton("Update");

        FoodId.setBounds(20, 220, 100, 25);
        IngredientName.setBounds(20, 250, 100, 25);
        Cost.setBounds(20, 280, 100, 25);
        Quantity.setBounds(20, 310, 100, 25);

        btnbuy.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
       

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 210);
        this.setLayout(null);
        this.add(pane);
        panel.setLayout(new GridLayout(4,1));

        // add JTextFields to the jthis
        panel.add(new JLabel("ID"));
        panel.add(FoodId);
        panel.add(new JLabel("Ingrediant name"));
        panel.add(IngredientName);
        panel.add(new JLabel("Cost"));
        panel.add(Cost);
        panel.add(new JLabel("Quantity"));
        panel.add(Quantity);

        // add JButtons to the jpanel
        panel2.setLayout(new GridLayout(1,2));
        panel2.add(btnbuy);
        panel2.add(btnUpdate);

        // create an array of objects to set the row data
        final Object[] row = new Object[4];
        // button add row - Clicked on add Button
        btnbuy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = FoodId.getText();
                row[1] = IngredientName.getText();
                row[2] = Cost.getText();
                row[3] = Quantity.getText();
                
                total_cost += Integer.parseInt(row[2].toString())*Integer.parseInt(row[3].toString());
                // add row to the model
                model.addRow(row);
                Ingredient a = new Ingredient();
                a.insert(row[0],row[1],row[2],row[3]);
                o1.add(a);
            }
        });

       

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // i = the index of the selected row
                int i = table.getSelectedRow();

                FoodId.setText(model.getValueAt(i, 0).toString());
                IngredientName.setText(model.getValueAt(i, 1).toString());
                Cost.setText(model.getValueAt(i, 2).toString());
                Quantity.setText(model.getValueAt(i, 3).toString());
            }
        });

        // button update row - Clicked on Update Button
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if (i >= 0) {
                model.setValueAt(Quantity.getText(), i, 3);
                Ingredient a = new Ingredient();
                a = o1.get(i);
                o1.remove(i);
                int k = Integer.parseInt(a.quantity.toString())*Integer.parseInt(a.cost.toString());
                total_cost = total_cost - k;
                a.quantity = Quantity.getText();
                k = Integer.parseInt(a.quantity.toString())*Integer.parseInt(a.cost.toString());
                total_cost += k;
                o1.add(a);
                } else {
                    System.out.println("Update Error");
                }
            }
        });
    }
    
}

