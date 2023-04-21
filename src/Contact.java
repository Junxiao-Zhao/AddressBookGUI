import java.io.Serializable;

public class Contact implements Serializable {
    private String fname;
    private String lname;
    private String phone;

    public Contact(String f, String l, String p) {
        this.fname = f;
        this.lname = l;
        this.phone = p;
    }

    // Getters
    public String getName() {
        return this.fname + " " + this.lname;
    }

    public String getPhone() {
        return this.phone;
    }

    // Setters
    public void setName(String f, String l) {
        this.fname = f;
        this.lname = l;
    }

    public void setPhone(String p) {
        this.phone = p;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s", this.fname, this.lname, this.phone);
    }
}
