package model;

// Represents an add-on having a name and amount.
public class AddOn {

    String addOnName;
    int addOnNum;

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
