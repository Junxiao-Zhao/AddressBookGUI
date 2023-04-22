import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SearchGUI extends JFrame {

    private static SearchGUI instance;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnReset;
    private JButton btnCancel;
    private JList<Contact> vList;
    private AddressBook addressBook;

    private SearchGUI() {
        this.addressBook = AddressBook.getInstance();
        this.txtSearch = new JTextField();

        DefaultListModel<Contact> listModel = new DefaultListModel<>();
        this.vList = new JList<>(listModel);

        // Search
        this.btnSearch = new JButton("Search");
        this.btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                listModel.clear();
                String target = txtSearch.getText();
                List<Contact> matched = search(target);

                if (matched == null)
                    return;

                for (Contact c : matched)
                    listModel.addElement(c);
            }
        });

        // Reset
        this.btnReset = new JButton("Reset");
        this.btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
                listModel.clear();
            }
        });

        // Cancel
        this.btnCancel = new JButton("Cancel");
        this.btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel();
                listModel.clear();
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

        JScrollPane listPane = new JScrollPane(this.vList);
        listPane.setColumnHeaderView(new JLabel(String.format("%-15s %-15s %-15s", "fname", "lname", "phone")));
        lowPanel.add(listPane);

        JPanel panel = new JPanel(new BorderLayout(50, 20));
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(lowPanel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
    }

    public static SearchGUI getInstance() {

        if (instance == null)
            instance = new SearchGUI();

        return instance;
    }

    private List<Contact> search(String target) {

        if (target.equals(""))
            return null;

        return this.addressBook.matchName(target);
    }

    private void reset() {
        txtSearch.setText("");
    }

    private void cancel() {
        SearchGUI.getInstance().setVisible(false);
        MainGUI.getInstance().setVisible(true);
    }
}
