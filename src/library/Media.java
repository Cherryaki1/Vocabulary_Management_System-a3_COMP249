package library;

public class Media extends Item {
    private String type;
    private static int numberOfMedia;

    //Default constructor
    public Media() {
        super();
        this.type = "";
        this.id = "M" + ++Media.numberOfMedia;
    }

    // Parameterized constructor
    public Media(String name, String author, int year, String type) {
        super(name, author, year);
        this.type = type;
        this.id = "M" + ++Media.numberOfMedia;
    }

    // Copy constructor
    public Media(Media media) {
        super(media);
        this.id = "M" + ++Media.numberOfMedia;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static int getNumberOfMedia() {
        return Media.getNumberOfMedia();
    }

    @Override
    public String toString() {
        return super.toString() + "\n" 
        + "Type: " + this.type;
    }

    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }

        if (this.getClass() != otherObject.getClass()) {
            return false;
        }

        Media otherMedia = (Media) otherObject;
        if ((super.equals(otherMedia)) && (this.type.equals(otherMedia.type))) {
            return true;
        } else {
            return false;
        }

    }
}
