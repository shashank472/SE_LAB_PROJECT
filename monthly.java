import java.awt.*;
import java.awt.event.*;
import java.rmi.server.ObjID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class monthly extends JFrame{

    
    JButton btnPaid = new JButton("OK");
    JLabel standardDev = new JLabel();
    JPanel panel = new JPanel();
    JFrame frame;
    JTable table2;
    Object[][] tab;
    float b;
    int t;

    public Object[][] populate(ArrayList<MenuStructure> a, int b){
        Object[][] k = new Object[1][3];
        int sum = 0;
        for(int i = 0;i<a.size();i++){
            for(int j=0;j<31;j++)
                sum += (Integer.parseInt(a.get(i).cost.toString()) * a.get(i).Monthly_sale[j]);
        }
        k[0][0] = sum;
        k[0][1] = b;
        k[0][2] = sum - b;
        return k;
    }

    monthly(ArrayList<MenuStructure> a){
        chef k = new chef();
        t = k.total_cost;
        frame = new JFrame();
        frame.setTitle("Monthly Report");//frame helps in setting the title of the frame
        frame.setSize(1302,720);//frame helps in setting the x and the y dimensions of the frame
        frame.getContentPane().setBackground(new Color(0xF7E2E2));
        tab = populate(a,t);
        String[] coloumn = {"Monthly sales","Monthly expenses","Monthly profit"};
        final DefaultTableModel model0 = new DefaultTableModel(tab,coloumn);
        table2 = new JTable(model0);
        table2.setFont(new Font("",Font.PLAIN, 25));
        table2.setRowHeight(31);
        Font bigFont = new Font("", Font.PLAIN, 30); // or whatever
        table2.getTableHeader().setFont(bigFont);
        JScrollPane pane0 = new JScrollPane(table2);
        pane0.setBounds(2,2,1300, 718);
        frame.setLayout(null);
        frame.add(pane0);
        frame.setVisible(true);
    }
}
