import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ishan on 3/21/15.
 */
public class GUIController extends JFrame {
    public void start() {
        int MAIN_NUM_ROW = 3;
        int MAIN_NUM_COL = 1;
        Color backgroundColor  = TDExpress.backgroundColor;
        this.setLayout(new GridLayout(MAIN_NUM_ROW, MAIN_NUM_COL));
        // Initializing guiControllerPanel
        final JPanel[][] guiControllerPanel = new JPanel[MAIN_NUM_ROW][MAIN_NUM_COL];
        for(int i = 0; i  < MAIN_NUM_ROW; i++) {
            for(int j = 0; j < MAIN_NUM_COL; j++) {
                guiControllerPanel[i][j] = new JPanel();
                guiControllerPanel[i][j].setBackground(backgroundColor);
                this.add(guiControllerPanel[i][j]);
            }
        }

        // Displaying the login panel
        final LandingPage landingPage = new LandingPage();
        guiControllerPanel[1][0].add(landingPage.userLoginPanel);

        // TODO: Have to make a button to switch to registration panel
        // Displaying the panel switcher
        guiControllerPanel[0][0].add(landingPage.panelSwitcher);
        // Setting the action listener on the panel switcher.
        landingPage.panelSwitcher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(landingPage.panelSwitcher.getText().equalsIgnoreCase("Register")) {
                    landingPage.panelSwitcher.setText("Login");
                    guiControllerPanel[1][0].removeAll();
                    guiControllerPanel[1][0].add(landingPage.userRegistrationPanel);
                    revalidate();
                    repaint();
                } else {
                    landingPage.panelSwitcher.setText("Register");
                    guiControllerPanel[1][0].removeAll();
                    guiControllerPanel[1][0].add(landingPage.userLoginPanel);
                    revalidate();
                    repaint();
                }
            }
        });

        this.setTitle("TDExpress");
        this.setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
