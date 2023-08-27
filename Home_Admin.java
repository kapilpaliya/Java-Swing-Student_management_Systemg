package Student.com;

// Import Statements.
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Home_Admin extends JFrame implements ActionListener{
    // Variable Declaration.
    JButton addStudent,operationStudent, showStudent, addAdmin, operationAdmin, showAdmin, logo; Container c;
    Font font1 = new Font(Font.SANS_SERIF,Font.BOLD,20);
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    ImageIcon i_logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("image/i_logo.jpg"))),
    i_addStudent = new ImageIcon(Objects.requireNonNull(getClass().getResource("image/i_addStudent.jpg"))),
    i_operationStudent = new ImageIcon(Objects.requireNonNull(getClass().getResource("image/i_operationStudent.jpg"))),
    i_showStudent = new ImageIcon(Objects.requireNonNull(getClass().getResource("image/i_showStudent.jpg"))),
    i_addAdmin = new ImageIcon(Objects.requireNonNull(getClass().getResource("image/i_addAdmin.jpg"))),
    i_operationAdmin = new ImageIcon(Objects.requireNonNull(getClass().getResource("image/i_operationAdmin.jpg"))),
    i_showAdmin = new ImageIcon(Objects.requireNonNull(getClass().getResource("image/i_showAdmin.jpg"))),
    frame_logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("image\\student.png")));

    Home_Admin(){
        // Frame Creation.
        setBounds(300,100,800,570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(frame_logo.getImage());
        setTitle("Admin_Home_Page");
        setResizable(false);
        revalidate();

        // Add Container In Frame.
        c = this.getContentPane();
        c.setBackground(Color.white);
        c.setLayout(null);

        // Container Background Type Logo.
        logo = new JButton(i_logo);
        logo.setBounds(250,0,100,100);
        logo.setBackground(Color.white);
        logo.setBorder(null);
        c.add(logo);

        // Motivation Text.
        l1 = new JLabel("\"Success is the ");
        l1.setBounds(355,0,200,25);
        l1.setFont(font1);
        l1.setForeground(Color.magenta);
        c.add(l1);
        l2 = new JLabel("sum of small ");
        l2.setBounds(355,30,200,25);
        l2.setFont(font1);
        l2.setForeground(Color.magenta);
        c.add(l2);
        l3 = new JLabel("efforts, Repeated\"");
        l3.setBounds(355,60,200,25);
        l3.setFont(font1);
        l3.setForeground(Color.magenta);
        c.add(l3);

        // Add Student Logo And Label.
        addStudent = new JButton(i_addStudent);
        addStudent.setBounds(88,120,150,150);
        addStudent.setBackground(Color.white);
        addStudent.setBorder(null);
        addStudent.addActionListener(this);
        c.add(addStudent);
        l4 = new JLabel("Add_Student");
        l4.setBounds(92,270,150,25);
        l4.setFont(font1);
        c.add(l4);

        // Operation Student Logo And Label.
        operationStudent = new JButton(i_operationStudent);
        operationStudent.setBounds(326,120,150,150);
        operationStudent.setBackground(Color.white);
        operationStudent.setBorder(null);
        operationStudent.addActionListener(this);
        c.add(operationStudent);
        l5 = new JLabel("Operation_Student");
        l5.setBounds(326,270,200,25);
        l5.setFont(font1);
        c.add(l5);

        // Show Student Data Logo And Label.
        showStudent = new JButton(i_showStudent);
        showStudent.setBounds(564,120,150,150);
        showStudent.setBackground(Color.white);
        showStudent.setBorder(null);
        showStudent.addActionListener(this);
        c.add(showStudent);
        l6 = new JLabel("Show_Students");
        l6.setBounds(564,270,150,25);
        l6.setFont(font1);
        c.add(l6);

        // Add Admin Logo And Label.
        addAdmin = new JButton(i_addAdmin);
        addAdmin.setBounds(88,315,150,150);
        addAdmin.setBackground(Color.white);
        addAdmin.setBorder(null);
        addAdmin.addActionListener(this);
        c.add(addAdmin);
        l7 = new JLabel("Add_Admin");
        l7.setBounds(92,467,150,25);
        l7.setFont(font1);
        c.add(l7);

        // Operation Admin Logo And Label.
        operationAdmin = new JButton(i_operationAdmin);
        operationAdmin.setBounds(326,315,150,150);
        operationAdmin.setBackground(Color.white);
        operationAdmin.addActionListener(this);
        operationAdmin.setBorder(null);
        c.add(operationAdmin);
        l8 = new JLabel("Operation_Admin");
        l8.setBounds(326,467,200,25);
        l8.setFont(font1);
        c.add(l8);

        // Show Admin Data Logo And Label.
        showAdmin = new JButton(i_showAdmin);
        showAdmin.setBounds(564,315,150,150);
        showAdmin.setBackground(Color.white);
        showAdmin.setBorder(null);
        showAdmin.addActionListener(this);
        c.add(showAdmin);
        l9 = new JLabel("Show_Admins");
        l9.setBounds(564,467,150,25);
        l9.setFont(font1);
        c.add(l9);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        // Add Student Data Button Action.
        if(e.getSource()==addStudent){
            this.setVisible(false);
            new User_Registration();
        }

        // Operation Student Data Button Action.
        if(e.getSource()==operationStudent){
            this.setVisible(false);
            new Operation_Student();
        }

        // Show Student Data Button Action.
        if(e.getSource()==showStudent){
            this.setVisible(false);
            new Show_Student();
        }

        // Add Admin Data Button Action.
        if(e.getSource()==addAdmin){
            this.setVisible(false);
            new Admin_Registration();
        }

        // Operation Admin Data Button Action.
        if(e.getSource()==operationAdmin){
            this.setVisible(false);
            new Operation_Admin();
        }

        // Show Admin Data Button Action.
        if(e.getSource()==showAdmin){
            this.setVisible(false);
            new Show_Admin();
        }
    }
}
