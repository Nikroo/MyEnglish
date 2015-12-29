package by.gsu.curiosity.mybd.info;


public class ContactItem {
    private String name;
    private String phone;
    private int photoID;
    private String about;

    public ContactItem(String name, String phone, int photoID, String about) {
        this.name = name;
        this.phone = phone;
        this.photoID = photoID;
        this.about = about;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public int getPhotoID() { return photoID; }
    public String getAbout() { return about; }

    public void setName(String val) { name = val; }
    public void setPhone(String val) { phone = val; }
    public void setPhotoID(int val) { photoID = val; }
    public void setAbout(String val) { about = val; }

    public static ContactItem selectedItem;

}
