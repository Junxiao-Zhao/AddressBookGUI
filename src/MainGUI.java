import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {
    private JButton btnAddNew;
    private JButton btnSearch;
    private JButton btnBrowse;
    private List<Contact> addressBook;

    MainGUI(List<Contact> ab) {

        this.addressBook = ab;

        this.btnSearch = new JButton("Search");
        this.btnBrowse = new JButton("Browse");
        Dimension btnDim = new Dimension(100, 20);

        this.btnSearch.setMaximumSize(btnDim);
        this.btnBrowse.setMaximumSize(btnDim);

        // Add New
        this.btnAddNew = new JButton("Add New");
        this.btnAddNew.setMaximumSize(btnDim);
        this.btnAddNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close this window
                AddNewContactGUI addGUI = new AddNewContactGUI(addressBook);
                addGUI.setVisible(true);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));
        Dimension whiteSpace = new Dimension(20, 20);
        panel.add(Box.createRigidArea(whiteSpace));
        panel.add(this.btnAddNew);
        panel.add(Box.createRigidArea(whiteSpace));
        panel.add(this.btnSearch);
        panel.add(Box.createRigidArea(whiteSpace));
        panel.add(this.btnBrowse);

        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
    }
}
