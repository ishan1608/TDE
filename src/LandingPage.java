import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

/**
 * Created by ishan on 3/21/15.
 */
public class LandingPage {
    // Login Panel
    JPanel userLoginPanel;
    JPanel userLoginInformationPanel;
    JTextField userId;
    JPasswordField userPassword;
    JButton userLoginButton;
    Color userLoginPanelColor = new Color(147, 123,45);

    // Registration Panel
    JPanel userRegistrationPanel;
    JPanel userRegistrationInformationPanel;
    JTextField userRegistrationId;
    JPasswordField userRegistrationPassword;
    JTextField userUnixId;
    JPasswordField userUnixPassword;
    JTextField userTDEId;
    JPasswordField userTDEPassword;
    JButton userRegistrationButton;
    Color userRegistrationPanelColor = new Color(183, 109, 119);

    // Panel switcher
    JButton panelSwitcher;

    public LandingPage() {
        // Login Panel
        userLoginPanel = new JPanel();
        userLoginPanel.setBackground(userLoginPanelColor);
        userLoginPanel.setLayout(new GridLayout(2, 1));
        // Login information panel
        userLoginInformationPanel = new JPanel();
        userLoginInformationPanel.setBackground(userLoginPanelColor);
        userLoginInformationPanel.setLayout(new GridLayout(2, 2));
        JLabel userIdLabel = new JLabel("username");
        userLoginInformationPanel.add(userIdLabel);
        userId = new JTextField();
        userLoginInformationPanel.add(userId);
        JLabel userPasswordLabel = new JLabel("password");
        userLoginInformationPanel.add(userPasswordLabel);
        userPassword = new JPasswordField();
        userLoginInformationPanel.add(userPassword);
        // Adding to login panel
        userLoginPanel.add(userLoginInformationPanel);
        userLoginButton = new JButton("Login");
        userLoginPanel.add(userLoginButton);

        // Registration Panel
        userRegistrationPanel = new JPanel(new GridLayout(2, 1));
        userRegistrationPanel.setBackground(userRegistrationPanelColor);
        // Registration Information Panel
        userRegistrationInformationPanel = new JPanel();
        userRegistrationInformationPanel.setBackground(userRegistrationPanelColor);
        userRegistrationInformationPanel.setLayout(new GridLayout(3, 4));

        JLabel userRegistrationIdLabel = new JLabel("username");
        userRegistrationInformationPanel.add(userRegistrationIdLabel);

        userRegistrationId = new JTextField();
        userRegistrationInformationPanel.add(userRegistrationId);

        JLabel userRegistrationPasswordLabel = new JLabel("password");
        userRegistrationInformationPanel.add(userRegistrationPasswordLabel);

        userRegistrationPassword = new JPasswordField();
        userRegistrationInformationPanel.add(userRegistrationPassword);

        JLabel userUnixIdLabel = new JLabel("Unix ID");
        userRegistrationInformationPanel.add(userUnixIdLabel);

        userUnixId = new JTextField();
        userRegistrationInformationPanel.add(userUnixId);

        JLabel userUnixPasswordLabel = new JLabel("Unix Password");
        userRegistrationInformationPanel.add(userUnixPasswordLabel);

        userUnixPassword = new JPasswordField();
        userRegistrationInformationPanel.add(userUnixPassword);

        JLabel userTDEIdLabel = new JLabel("TDE ID");
        userRegistrationInformationPanel.add(userTDEIdLabel);

        userTDEId = new JTextField();
        userRegistrationInformationPanel.add(userTDEId);

        JLabel userTDEPasswordLabel = new JLabel("TDE Password");
        userRegistrationInformationPanel.add(userTDEPasswordLabel);

        userTDEPassword = new JPasswordField();
        userRegistrationInformationPanel.add(userTDEPassword);

        userRegistrationPanel.add(userRegistrationInformationPanel);
        userRegistrationButton = new JButton("Register");
        userRegistrationPanel.add(userRegistrationButton);

        // Register button handler
        userRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Getting the information
                String userID = userRegistrationId.getText().trim();
                char[] userPWD = userRegistrationPassword.getPassword();
                String  userUnixID = userUnixId.getText().trim();
                char[] userUnixPWD = userUnixPassword.getPassword();
                String userTDEID = userTDEId.getText().trim();
                char[] userTDEPWD = userTDEPassword.getPassword();

                try {
                    FileOutputStream userInfoFO = new FileOutputStream(".userInfo");
                    ObjectOutputStream userInfoWriter = new ObjectOutputStream(userInfoFO);
                    UserInfo userInfo = new UserInfo(userID, userPWD, userUnixID, userUnixPWD, userTDEID, userTDEPWD);
                    userInfoWriter.writeObject(userInfo);
                    System.out.println("Information successfully saved.");

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Login button handler
        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userProvidedID = userId.getText();
                char[] userProvidedPassword = userPassword.getPassword();
                // Reading from the file
                try {
                    FileInputStream userInfoFI = new FileInputStream(".userInfo");
                    ObjectInputStream userInfoReader = new ObjectInputStream(userInfoFI);
                    UserInfo userInfo = (UserInfo) userInfoReader.readObject();
                    // Comparing it with the user provided data
                    if(userInfo.userID.equals(new String(userProvidedID))) {
                        // Compare password
                        if(Arrays.equals(userProvidedPassword, userInfo.userPWD)) {
                            System.out.println("Successfully logged in.");
                        } else {
                            System.out.println("Password for " + userProvidedID + " is wrong");
                        }
                    } else {
                        System.out.println("username : " + userProvidedID + " not found");
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Panel switcher
        panelSwitcher = new JButton("Register");
    }
}
