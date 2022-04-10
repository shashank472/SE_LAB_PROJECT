import java.awt.*;
import java.awt.event.*;
import java.rmi.server.ObjID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Statistical extends JFrame{

    
    JButton btnPaid = new JButton("OK");
    JLabel standardDev = new JLabel();
    JPanel panel = new JPanel();
    JFrame frame;
    JTable table2;
    Object[][] tab;
    float b;
    int t;

    public Object[][] populate(ArrayList<MenuStructure> a){
        Object[][] k = new Object[a.size()][6];
        for(int i = 0;i<a.size();i++){
            k[i][0] = a.get(i).Fid.toString();
            k[i][1] = a.get(i).name.toString();
            b = a.get(i).Calculate_mean();
            k[i][2] = Float.toString(b);
            t = a.get(i).Calculate_median();
            k[i][3] = Integer.toString(t);
            t = a.get(i).Calculate_mode();
            k[i][4] = Integer.toString(t);
            b = a.get(i).Calculate_stddev();
            k[i][5] = Float.toString(b);                 
        }
        return k;
    }

    Statistical(ArrayList<MenuStructure> a){
        frame = new JFrame();
        frame.setTitle("Statistical Report");//frame helps in setting the title of the frame
        frame.setSize(1302,720);//frame helps in setting the x and the y dimensions of the frame
        frame.getContentPane().setBackground(new Color(0xF7E2E2));
        tab = populate(a);
        String[] coloumn = {"Id","Name","Mean","Median","Mode","Standard Deviation"};
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
