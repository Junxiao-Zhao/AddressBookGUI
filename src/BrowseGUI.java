import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class BrowseGUI extends JFrame {

    private JButton btnDelete;
    private JButton btnClose;
    private JList<Contact> vList;
    private List<Contact> addressBook;
    private int selectedIndex;

    BrowseGUI(List<Contact> ab) {
        this.addressBook = ab;
        this.selectedIndex = -1;

        DefaultListModel<Contact> listModel = new DefaultListModel<>();
        this.addContacts(listModel);
        this.vList = new JList<>(listModel);

        // Select
        this.vList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedIndex = vList.getSelectedIndex();
                }
            }
        });

        // Delete
        this.btnDelete = new JButton("Delete");
        this.btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0 && selectedIndex < addressBook.size()) {
                    addressBook.remove(selectedIndex);
                    listModel.remove(selectedIndex);
                }
            }
        });

        // Close
        this.btnClose = new JButton("Close");
        this.btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI mg = new MainGUI(addressBook);
                mg.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        buttonPanel.add(this.btnDelete);
        buttonPanel.add(this.btnClose);

        JScrollPane listPane = new JScrollPane(this.vList);
        listPane.setColumnHeaderView(new JLabel(String.format("%-15s %-15s %-15s", "fname", "lname", "phone")));

        JPanel panel = new JPanel(new BorderLayout(50, 20));
        panel.add(listPane, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
    }

    public void addContacts(DefaultListModel<Contact> listModel) {
        for (Contact c : addressBook)
            listModel.addElement(c);
    }
}
