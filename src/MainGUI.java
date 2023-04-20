import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {

    private static MainGUI instance;
    private JButton btnAddNew;
    private JButton btnSearch;
    private JButton btnBrowse;

    private MainGUI() {

        Dimension btnDim = new Dimension(100, 20);

        // Add New
        this.btnAddNew = new JButton("Add New");
        this.btnAddNew.setMaximumSize(btnDim);
        this.btnAddNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNew();
            }
        });

        // Search
        this.btnSearch = new JButton("Search");
        this.btnSearch.setMaximumSize(btnDim);
        this.btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        // Browse
        this.btnBrowse = new JButton("Browse");
        this.btnBrowse.setMaximumSize(btnDim);
        this.btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browse();
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

    public static MainGUI getInstance() {

        if (instance == null)
            instance = new MainGUI();

        return instance;
    }

    public void addNew() {
        MainGUI.getInstance().setVisible(false);
        AddNewContactGUI.getInstance().setVisible(true);
    }

    public void search() {
        MainGUI.getInstance().setVisible(false);
        SearchGUI.getInstance().setVisible(true);
    }

    public void browse() {
        MainGUI.getInstance().setVisible(false);
        BrowseGUI.getInstance().setVisible(true);
    }
}
