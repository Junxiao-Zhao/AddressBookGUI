import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BrowseGUI extends JFrame {

    private static BrowseGUI instance;
    private JButton btnDelete;
    private JButton btnClose;
    private JList<Contact> vList;
    private DefaultListModel<Contact> listModel;
    private AddressBook addressBook;

    private BrowseGUI() {
        this.addressBook = AddressBook.getInstance();

        // Get contacts

        this.listModel = new DefaultListModel<>();
        this.vList = new JList<>(listModel);

        for (Contact c : addressBook.getContacts()) {
            listModel.addElement(c);
        }

        // Delete
        this.btnDelete = new JButton("Delete");
        this.btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = vList.getSelectedIndex();

                if (selectedIndex >= 0 && selectedIndex < addressBook.size()) {
                    delete(selectedIndex);
                    listModel.remove(selectedIndex);
                    // update();
                }
            }
        });

        // Close
        this.btnClose = new JButton("Close");
        this.btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BrowseGUI.getInstance().setVisible(false);
                MainGUI.getInstance().setVisible(true);
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

    public static BrowseGUI getInstance() {

        if (instance == null)
            instance = new BrowseGUI();

        instance.update();

        return instance;
    }

    public void update() {

        // this.listModel.clear();

        List<Contact> contacts = AddressBook.getInstance().getContacts();
        int index = this.listModel.getSize();

        for (int i = index; i < contacts.size(); i++) {
            this.listModel.addElement(contacts.get(i));
        }
    }

    private void delete(int selectedIndex) {

        this.addressBook.remove(selectedIndex);
        FileManager.getInstance().write(this.addressBook);
    }
}
