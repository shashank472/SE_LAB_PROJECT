import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class MyFrame extends JFrame implements ActionListener {

    JButton Manager = new JButton("Manager");
    JButton Receptionist = new JButton("Receptionist");
    
    manager m ;
    receptionist r ;
    


    MyFrame(){
        //setting visibility
        m = new manager();
        m.setVisible(false);
        
        //setting up the main frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//this is used to exit the code when you press the x button
        this.setTitle("Restaurant Automation System");//this helps in setting the title of the frame
        this.setSize(1302,720);//this helps in setting the x and the y dimensions of the frame
        this.getContentPane().setBackground(new Color(0xF7E2E2));

        //setting up the buttons
        Manager.setBackground(new Color(102, 255, 153));
		Manager.setFont(new Font("Calibri", Font.BOLD, 19));
        Manager.setBounds(113, 294, 433, 51);
        Manager.addActionListener(this);
        Receptionist.setBackground(new Color(102, 255, 153));
		Receptionist.setFont(new Font("Calibri", Font.BOLD, 19));
        Receptionist.setBounds(743, 294, 433, 51);
        Receptionist.addActionListener(this);
        this.add(Receptionist);
        this.add(Manager);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Manager){
            m.setVisible(true);
        }
        if(e.getSource() == Receptionist){
            receptionist r = new receptionist(m.Hmenu);;
            r.setVisible(true);
        }
    }
}