package client;
import library.*;

public class Client {
    private String name;
    private String id;
    private String email;
    private String phone;
    private Item[] leasedItems;


    public Client() {
        this.name = "";
        this.email = "";
        this.phone = "";
        this.id = "C" + phone;
        this.leasedItems = null;
    }

    public Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = "C" + phone;
        this.leasedItems = new Item[1]; // MAYBE WE INITIALIZE TO NULL IDK
    }

    public Client(Client client) {
        this.name = client.name;
        this.email = client.email;
        this.phone = client.phone;
        this.id = client.id;
        this.leasedItems = client.leasedItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String displayLeasedItems() {
        String leasedList = "";
        for (int i = 0; i < leasedItems.length;i++) {
            leasedList += leasedItems[i] + "\n";
        }
        return leasedList;
    }

    @Override
    public String toString() {
        return "";
    }

    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }

        if (this.getClass() != otherObject.getClass()) {
            return false;
        }

        Client otherClient = (Client) otherObject;
        if ((this.name.equals(otherClient.name)) && (this.email.equals(otherClient.email))
                && (this.phone.equals(otherClient.phone))) {
            return true;
        } else
            return false;
    }

}
