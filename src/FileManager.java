import java.io.*;

public class FileManager {

    private String filePath;
    private static FileManager instance;

    private FileManager() {
        this.filePath = "data.bin";
    }

    public static FileManager getInstance() {

        if (instance == null)
            instance = new FileManager();

        return instance;
    }

    public void load(AddressBook addressBook) {

        try {
            FileInputStream f = new FileInputStream(this.filePath);
            ObjectInputStream is = new ObjectInputStream(f);

            // read stored contacts
            Contact c;
            int num = (Integer) is.readObject();
            for (int i = 0; i < num; i++) {
                c = (Contact) is.readObject();
                addressBook.add(c);
            }

            is.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void write(AddressBook addressBook) {

        try {
            FileOutputStream f = new FileOutputStream("data.bin");
            ObjectOutputStream os = new ObjectOutputStream(f);

            os.writeObject(addressBook.size()); // the size of current Contacts

            // save all contacts
            for (Contact c : addressBook.getContacts()) {
                os.writeObject(c);
            }

            os.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
