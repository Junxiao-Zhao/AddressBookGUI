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
        Dimension btnDim = new Dimension(100, 20);
        this.btnSearch = new JButton("Search");
        this.btnSearch.setMaximumSize(btnDim);
        this.btnReset = new JButton("Reset");
        this.btnReset.setMaximumSize(btnDim);
        this.btnCancel = new JButton("Cancel");
        this.btnCancel.setMaximumSize(btnDim);
        this.vList = new JList<>();
        this.vList.setPreferredSize(new Dimension(200, 100));

        DefaultListModel<Contact> listModel = new DefaultListModel<>();

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 0));
        Dimension whiteSpace = new Dimension(20, 20);
        btnPanel.add(this.btnSearch);
        btnPanel.add(Box.createRigidArea(whiteSpace));
        btnPanel.add(this.btnReset);
        btnPanel.add(Box.createRigidArea(whiteSpace));
        btnPanel.add(this.btnCancel);

        JPanel txtPanel = new JPanel();
        txtPanel.setLayout(new BoxLayout(txtPanel, BoxLayout.Y_AXIS));
        txtPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 20));
        txtPanel.add(this.txtSearch);
        txtPanel.add(Box.createRigidArea(whiteSpace));
        txtPanel.add(this.vList);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.add(btnPanel, BorderLayout.WEST);
        panel.add(txtPanel, BorderLayout.EAST);

        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
    }
}
