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

        DefaultListModel<Contact> listModel = new DefaultListModel<>();
        this.vList = new JList<>(listModel);

        // Dimension btnDim = new Dimension(10, 20);

        // Search
        this.btnSearch = new JButton("Search");
        // this.btnSearch.setMaximumSize(btnDim);
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
        // this.btnReset.setMaximumSize(btnDim);
        this.btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtSearch.setText("");
                listModel.clear();
            }
        });

        // Cancel
        this.btnCancel = new JButton("Cancel");
        // this.btnCancel.setMaximumSize(btnDim);
        this.btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI mg = new MainGUI(addressBook);
                mg.setVisible(true);
            }
        });

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        topPanel.add(this.btnSearch);
        topPanel.add(this.txtSearch);

        JPanel lowPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        JPanel leftPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        leftPanel.add(this.btnReset);
        leftPanel.add(this.btnCancel);
        lowPanel.add(leftPanel);
        lowPanel.add(new JScrollPane(this.vList));

        JPanel panel = new JPanel(new BorderLayout(50, 20));
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(lowPanel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
    }
}
