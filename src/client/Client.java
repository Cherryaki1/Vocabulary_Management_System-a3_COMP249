package client;

public class Client {
    private String name;
    private String id;
    private String email;
    private String phone;

    public Client() {
        this.name = "";
        this.email = "";
        this.phone = "";
    }

    public Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Client(Client client) {
        this.name = client.name;
        this.email = client.email;
        this.phone = client.phone;
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
