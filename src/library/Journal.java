package library;

public class Journal extends Item {
    private int volumeNumber;
    private static int numberOfJournals;

    public Journal() {
        super();
        this.volumeNumber = 0;
        this.id = "J" + ++Journal.numberOfJournals;
    }

    public Journal(String name, String author, int year, int volumeNumber) {
        super(name, author, year);
        this.volumeNumber = volumeNumber;
        this.id = "J" + ++Journal.numberOfJournals;
    }

    public Journal(Journal journal, int volumeNumber) {
        super(journal);
        this.volumeNumber = volumeNumber;
        this.id = "J" + ++Journal.numberOfJournals;
    }

    public int getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" 
        + "Volume number: " + this.volumeNumber;
    }

    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }

        if (this.getClass() != otherObject.getClass()) {
            return false;
        }

        Journal otherJournal = (Journal) otherObject;
        if ((super.equals(otherJournal)) && (this.volumeNumber == otherJournal.volumeNumber)) {
            return true;
        } else {
            return false;
        }

    }

}
