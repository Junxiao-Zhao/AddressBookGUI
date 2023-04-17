import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchGUI extends JFrame {

    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnReset;
    private JButton btnCancel;
    private JList<Contact> vList;
    private List<Contact> addressBook;

    SearchGUI(List<Contact> ab) {
        this.addressBook = ab;
        this.txtSearch = new JTextField();
        // this.txtSearch.setPreferredSize(getPreferredSize());
        txtSearch.setColumns(20);

        DefaultListModel<Contact> listModel = new DefaultListModel<>();
        this.vList = new JList<>(listModel);
        // this.vList.setMaximumSize(new Dimension(50, 500));

        Dimension btnDim = new Dimension(100, 30);

        // Search
        this.btnSearch = new JButton("Search");
        this.btnSearch.setMaximumSize(btnDim);
        this.btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
                String target = txtSearch.getText();

                if (target.equals(""))
                    return;

                for (Contact c : addressBook) {
                    String cname = c.getName();

                    if (cname.toLowerCase().contains(target.toLowerCase())) {
                        listModel.addElement(c);
                    }
                }
            }
        });

        // Reset
        this.btnReset = new JButton("Reset");
        this.btnReset.setMaximumSize(btnDim);
        this.btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtSearch.setText("");
                listModel.clear();
            }
        });

        // Cancel
        this.btnCancel = new JButton("Cancel");
        this.btnCancel.setMaximumSize(btnDim);
        this.btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI mg = new MainGUI(addressBook);
                mg.setVisible(true);
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        Dimension whiteSpace = new Dimension(20, 20);
        btnPanel.add(this.btnSearch);
        btnPanel.add(Box.createRigidArea(whiteSpace));
        btnPanel.add(this.btnReset);
        btnPanel.add(Box.createRigidArea(whiteSpace));
        btnPanel.add(this.btnCancel);

        JPanel txtPanel = new JPanel();

        txtPanel.setLayout(new GridLayout(5, 1, 10, 20));
        txtPanel.add(Box.createRigidArea(whiteSpace));
        txtPanel.add(this.txtSearch);
        txtPanel.add(this.vList);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.add(btnPanel, BorderLayout.WEST);
        panel.add(txtPanel, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
    }
}
