import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddNewContactGUI extends JFrame {

    private static AddNewContactGUI instance;
    private JTextField txtFName;
    private JTextField txtLName;
    private JTextField txtPhone;
    private JButton btnSave;
    private JButton btnReset;
    private JButton btnCancel;
    private AddressBook addressBook;

    private AddNewContactGUI() {

        this.addressBook = AddressBook.getInstance();

        Dimension labelDim = new Dimension(100, 20);

        // First Name
        JLabel labelF = new JLabel("First Name");
        labelF.setPreferredSize(labelDim);
        this.txtFName = new JTextField();
        JPanel panelF = new JPanel(new BorderLayout(20, 20));
        panelF.add(labelF, BorderLayout.WEST);
        panelF.add(this.txtFName, BorderLayout.CENTER);

        // Last Name
        JLabel labelL = new JLabel("Last Name");
        labelL.setPreferredSize(labelDim);
        this.txtLName = new JTextField();
        JPanel panelL = new JPanel(new BorderLayout(20, 20));
        panelL.add(labelL, BorderLayout.WEST);
        panelL.add(this.txtLName, BorderLayout.CENTER);

        // Phone Number
        JLabel labelP = new JLabel("Phone Number");
        labelP.setPreferredSize(labelDim);
        this.txtPhone = new JTextField();
        JPanel panelP = new JPanel(new BorderLayout(20, 20));
        panelP.add(labelP, BorderLayout.WEST);
        panelP.add(this.txtPhone, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        JPanel textFieldPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        textFieldPanel.add(panelF);
        textFieldPanel.add(panelL);
        textFieldPanel.add(panelP);
        panel.add(textFieldPanel, BorderLayout.CENTER);

        // Save
        Dimension btnDim = new Dimension(100, 20);
        this.btnSave = new JButton("Save");
        this.btnSave.setMaximumSize(btnDim);
        this.btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fname = txtFName.getText();
                String lname = txtLName.getText();
                String phone = txtPhone.getText();
                save(fname, lname, phone);
            }
        });

        // Reset
        this.btnReset = new JButton("Reset");
        this.btnReset.setMaximumSize(btnDim);
        this.btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        // Cancel
        this.btnCancel = new JButton("Cancel");
        this.btnCancel.setMaximumSize(btnDim);
        this.btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        buttonPanel.add(this.btnSave);
        buttonPanel.add(this.btnReset);
        buttonPanel.add(this.btnCancel);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
    }

    public static AddNewContactGUI getInstance() {

        if (instance == null)
            instance = new AddNewContactGUI();

        return instance;
    }

    private void save(String fname, String lname, String phone) {

        if (fname.equals("") && lname.equals("") && phone.equals(""))
            return;

        this.addressBook.addContact(fname, lname, phone);
        FileManager.getInstance().write(this.addressBook);
    }

    private void reset() {
        this.txtFName.setText("");
        this.txtLName.setText("");
        this.txtPhone.setText("");
    }

    private void cancel() {
        AddNewContactGUI.getInstance().setVisible(false);
        MainGUI.getInstance().setVisible(true);
    }
}
