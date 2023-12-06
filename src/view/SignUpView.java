package view;

import interface_adapter.sign_up.SignUpController;
import interface_adapter.sign_up.SignUpState;
import interface_adapter.sign_up.SignUpViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignUpView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignUpViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField confirmPasswordInputField = new JPasswordField(15);
    private final SignUpController signupController;
    private final ViewManagerModel viewManager;

    private final JButton signUp;
    private final JButton logIn;

    public SignUpView(SignUpController controller, SignUpViewModel signupViewModel, ViewManagerModel viewManager) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.viewManager = viewManager;
        signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(signupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(signupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel confirmPasswordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.CONFIRM_PASSWORD_LABEL), confirmPasswordInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        logIn = new JButton(signupViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            signupController.execute(signupViewModel.getState().getUsername(),
                                    signupViewModel.getState().getPassword(),
                                    signupViewModel.getState().getConfirmPassword());
                        }
                    }
                }
        );
        logIn.addActionListener(this);

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
                        currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
                        currentState.setPassword(String.valueOf(passwordInputField.getPassword()) + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        confirmPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
                        currentState.setConfirmPassword(String.valueOf(confirmPasswordInputField.getPassword())
                                + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(confirmPasswordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == logIn) {
            viewManager.setActiveView("log in");
            viewManager.firePropertyChanged();
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignUpState state = (SignUpState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
        if (state.getPasswordError()!= null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());

        }
        if (state.getConfirmPasswordError() != null){
            JOptionPane.showMessageDialog(this, state.getConfirmPasswordError());
        }
    }
}
