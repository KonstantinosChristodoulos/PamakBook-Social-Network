package PamakBook;

import javax.swing.*;
import java.awt.*;

public class InfectionFrame extends JFrame {

    private User user;
    private JFrame loginFrame;
    private JPanel panel;

    public InfectionFrame(User user, JFrame loginFrame) {
        this.user = user;
        this.loginFrame = loginFrame;

        
        setTitle("Πιθανή Μετάδοση Ιού");
        setSize(400, 350); // Λίγο πιο κοντό για να ταιριάζει στο screenshot
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();

        

        
        StringBuilder sb = new StringBuilder();
        sb.append("************************************************************************\n");
        sb.append(user.getName()).append(" has been infected. The following users have to be tested\n");
        sb.append("************************************************************************\n\n");
        
        for (User u : user.potentialTransmissions()) {
            sb.append(u.getName()).append("\n");
        }
        

        
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 12)); 
        textArea.setEditable(false); 
         

        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        
        panel.add(scrollPane, BorderLayout.CENTER);


       
        JButton backBtn = new JButton("Back to Login Screen");
        
        
        backBtn.addActionListener(e -> {
            loginFrame.setVisible(true);
            dispose();
        });

        
        
        panel.add(backBtn);
        
        add(panel);
        setVisible(true);
    }
}
