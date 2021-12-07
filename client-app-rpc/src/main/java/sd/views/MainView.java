package sd.views;

import sd.ClientController;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JFrame frame;
    private JButton sevenDays;
    private JButton baseline;
    private JButton optimumInterval;
    private JTextField textInterval;
    private JLabel nrHours;
    private JLabel answer;
    private ClientController clientController;

    public MainView(){
        clientController = new ClientController();
        frame = new JFrame("Client view");
        sevenDays = new JButton("Display last seven days consumption");
        baseline = new JButton("Display baseline");
        optimumInterval = new JButton("Display optimum interval");
        textInterval = new JTextField();
        nrHours = new JLabel("Number of hours: ");
        answer = new JLabel();

        initializeMainView();
        frame.setVisible(true);
    }

    private void initializeMainView(){
        sevenDays.setBounds(20,20,400,50);
        baseline.setBounds(20, 90, 400, 50);
        optimumInterval.setBounds(20, 160, 400, 50);
        textInterval.setBounds(210, 230, 60, 30);
        nrHours.setBounds(20, 230, 260, 30);
        answer.setBounds(210,280, 200, 20);

        frame.setSize(460, 400);
        frame.getContentPane().setLayout(null);
        frame.setResizable(true);

        addToFrame();
        addListeners();
        frame.setLocation(dim.width/2-frame.getSize().width/2,
                dim.height/2-frame.getSize().height/2);
    }

    private void addListeners(){
        sevenDays.addActionListener(e-> clientController.viewSevenDays());
        baseline.addActionListener(e-> clientController.baseline());
        optimumInterval.addActionListener(e->clientController.optimalInterval(textInterval.getText()));
    }

    private void addToFrame(){
        frame.getContentPane().add(sevenDays)
                .getParent().add(baseline)
                .getParent().add(optimumInterval)
                .getParent().add(textInterval)
                .getParent().add(nrHours)
                .getParent().add(answer);
    }

}
