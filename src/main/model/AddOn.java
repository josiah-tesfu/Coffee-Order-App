package model;

// Represents an add-on having a name and amount.
public class AddOn {

    String addOnName;
    int addOnNum;

    //EFFECTS: Creates an add-on and sets add-on number to 0
    public AddOn(String addOnName) {
        this.addOnName = addOnName;
        this.addOnNum = 0;
    }

    public String getAddOnName() {
        return addOnName;
    }

    public int getAddOnNum() {
        return addOnNum;
    }

    public void incrementNum() {
        addOnNum++;
    }
}
