import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class bill extends JFrame {

    Object[][] tab;
    JButton btnPaid = new JButton("Paid");
    JPanel panel = new JPanel();
    JFrame billg;
    int total;
    chef chef;

    public Object[][] copy(ArrayList<order> temp){
        Object[][] k = new Object[temp.size()][3];
        for(int i=0;i<temp.size();i++){
            k[i][0] = temp.get(i).Id.toString();
            k[i][1] = temp.get(i).DishName.toString();
            k[i][2] = temp.get(i).Quantity.toString();
        }
        return k;
    }

    public void make_bill(ArrayList<order> a,ArrayList<MenuStructure> c){
        total = 0;
        int mul;
        for(int i=0;i<a.size();i++){
           for(int j=0;j<c.size();j++){
               if((a.get(i).Id.toString()).compareTo(c.get(j).Fid.toString()) == 0){
                    c.get(j).addQuantity(Integer.parseInt(a.get(i).Quantity.toString()));
                    mul = Integer.parseInt(a.get(i).Quantity.toString()) * Integer.parseInt(c.get(j).cost.toString());
                    total += mul;
                    break;
               }else{
                   continue;
               }
           }
        }
    }

    bill(ArrayList<order> o, ArrayList<MenuStructure> c){
        billg = new JFrame();
        billg.setTitle("Bill Reciept");//billg helps in setting the title of the frame
        billg.setSize(1302,720);//billg helps in setting the x and the y dimensions of the frame
        billg.getContentPane().setBackground(new Color(0xF7E2E2));
        tab = copy(o);
        billg.add(panel);
        make_bill(o,c);
        String s = "The total is "+Integer.toString(total)+" Ruppes";
        btnPaid.setBounds(651, 420,300, 50);
        panel.setBounds(420, 420, 600, 420);
        panel.setLayout(new GridLayout(3,2));
        JLabel label = new JLabel(s,SwingConstants.CENTER);
        label.setFont(new Font("",Font.PLAIN,20));
        panel.add(label);
        panel.add(btnPaid);
        String[] coloumn = {"Id","Name","Quantity"};
        final DefaultTableModel model = new DefaultTableModel(tab,coloumn);
        final JTable table = new JTable(model);
        table.setFont(new Font("",Font.PLAIN, 24));
        table.setRowHeight(30);
        Font bigFont = new Font("", Font.PLAIN, 24); // or whatever
        table.getTableHeader().setFont(bigFont);
        JScrollPane pane0 = new JScrollPane(table);
        pane0.setBounds(0, 0, 1302, 412);
        billg.setLayout(null);
        billg.add(pane0);
        btnPaid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(billg, "Amount Paid");
                billg.dispose();
                chef = new chef(o);
            }
        });
    }
}
