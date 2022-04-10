import javax.swing.*;
import java.awt.*;


public class Main{
    public static void main(String[] args){
        MyFrame Home = new MyFrame();//this is used for the main homepage of the automation system
        JLabel label = new JLabel("Click on the role:");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setForeground(new Color(0x1A132F));
        label.setFont(new Font("MV Boli",Font.PLAIN,40));
        Home.add(label);
        Home.setVisible(true);
    }
}