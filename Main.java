package Student.com;

// Import Statements
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main{
    // Variable Declaration.
    ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("image/main.jpg")));
    JWindow w; JPanel p; JProgressBar pb; Timer timer; JLabel l1;
    Main(){
        // Add Window Panel
        w = new JWindow();
        w.setBounds(300,100,800,500);
        w.setVisible(true);

        //Add Panel.
        p = new JPanel();
        w.add(p);
        p.setBorder(BorderFactory.createLineBorder(Color.black));

        // Set Background Image.
        l1 = new JLabel(img);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(l1);

        // Set ProgressBar.
        pb = new JProgressBar(0,100);
        w.add(pb,BorderLayout.PAGE_END);
        pb.setForeground(Color.BLUE);
        pb.setBackground(Color.green);
        w.revalidate();

        // Wording Of ProgressBar.
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = pb.getValue();
                if(x==100){
                    w.dispose();
                    new User_Login();
                    timer.stop();
                }
                else{
                    pb.setValue(x+1);
                }
            }
        });
        timer.start();
    }
    public static void main(String[] args) {
        new Main();
    }
}
