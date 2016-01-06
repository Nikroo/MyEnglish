package by.gsu.curiosity.mybd.lists;


public class ContactItem {
    private String name;
    private String phone;
    private int photoID;
    private String about;
    private String text3;


    public ContactItem(String name, String phone, int photoID, String about, String text3) {
        this.name = name;
        this.phone = phone;
        this.photoID = photoID;
        this.about = about;
        this.text3 = text3;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public int getPhotoID() { return photoID; }
    public String getAbout() { return about; }
    public String getText3() { return text3; }

    public void setName(String val) { name = val; }
    public void setPhone(String val) { phone = val; }
    public void setPhotoID(int val) { photoID = val; }
    public void setAbout(String val) { about = val; }
    public void setText3(String val) { text3 = val; }

    public static ContactItem selectedItem;

}
