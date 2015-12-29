package by.gsu.curiosity.mybd.info;

import java.util.ArrayList;

/**
 * Created by Curiosity on 21.12.2015.
 */
public class ContactGroup {
    private String name;
    private ArrayList<ContactItem> contacts;

    public ContactGroup(String name) {
        this.name = name;
        this.contacts = new ArrayList<ContactItem>();
    }

    public void addContact(ContactItem item) {
        contacts.add(item);
    }
    public String getName() {
        return name;
    }
    public ArrayList<ContactItem> getContactsList() {
        return contacts;
    }
    public ContactItem getContactItem(int position) {
        return contacts.get(position);
    }
}
