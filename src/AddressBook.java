import java.util.*;

public class AddressBook {

    private static AddressBook instance;
    private List<Contact> addressBook;

    private AddressBook() {
        this.addressBook = new ArrayList<>();
        FileManager.getInstance().load(this);
    }

    public static AddressBook getInstance() {

        if (instance == null)
            instance = new AddressBook();

        return instance;
    }

    public List<Contact> matchName(String target) {

        List<Contact> names = new ArrayList<>();

        for (Contact c : addressBook) {
            String cname = c.getName();

            if (cname.toLowerCase().contains(target.toLowerCase())) {
                names.add(c);
            }
        }

        return names;
    }

    public List<Contact> getContacts() {
        return this.addressBook;
    }

    public void setContacts(List<Contact> contacts) {
        this.addressBook = contacts;
    }

    public void add(Contact c) {
        this.addressBook.add(c);
    }

    public void addContact(String f, String l, String p) {
        this.addressBook.add(new Contact(f, l, p));
    }

    public void remove(int index) {
        this.addressBook.remove(index);
    }

    public int size() {
        return this.addressBook.size();
    }
}
