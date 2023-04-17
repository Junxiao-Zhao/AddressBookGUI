import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Contact> c = new ArrayList<>();
        // MainGUI mainGui = new MainGUI(c);
        // mainGui.setVisible(true);
        // AddNewContactGUI a = new AddNewContactGUI();
        // a.setVisible(true);
        SearchGUI s = new SearchGUI(c);
        s.setVisible(true);
    }
}
