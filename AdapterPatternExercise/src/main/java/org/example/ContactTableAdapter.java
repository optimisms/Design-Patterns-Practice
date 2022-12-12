package org.example;

public class ContactTableAdapter implements TableData {
    private final ContactManager manager;
    private final int NUM_OF_ATTRIBUTES = 4;
    private final String ATTR_ONE = "First";
    private final String ATTR_TWO = "Last";
    private final String ATTR_THREE = "Phone";
    private final String ATTR_FOUR = "Email";
    private final int ATTR_ONE_WIDTH = 10;
    private final int ATTR_TWO_WIDTH = 9;
    private final int ATTR_THREE_WIDTH = 14;
    private final int ATTR_FOUR_WIDTH = 26;

    public ContactTableAdapter(ContactManager manager) {
        this.manager=manager;
    }

    @Override
    public int getColumnCount() {
        return NUM_OF_ATTRIBUTES;
    }

    @Override
    public int getRowCount() {
        return manager.getContactCount();
    }

    @Override
    public int getColumnSpacing() {
        return 2;
    }

    @Override
    public int getRowSpacing() {
        return 0;
    }

    @Override
    public char getHeaderUnderline() {
        return '~';
    }

    @Override
    public String getColumnHeader(int col) {
        return switch (col) {
            case 0 -> ATTR_ONE;
            case 1 -> ATTR_TWO;
            case 2 -> ATTR_THREE;
            case 3 -> ATTR_FOUR;
            default -> null;
        };
    }

    @Override
    public int getColumnWidth(int col) {
        return switch (col) {
            case 0 -> ATTR_ONE_WIDTH;
            case 1 -> ATTR_TWO_WIDTH;
            case 2 -> ATTR_THREE_WIDTH;
            case 3 -> ATTR_FOUR_WIDTH;
            default -> 0;
        };
    }

    @Override
    public Justification getColumnJustification(int col) {
        return Justification.Center;
    }

    @Override
    public String getCellValue(int row, int col) {
        Contact contact = manager.getContact(row);
        return switch (col) {
            case 0 -> contact.getFirstName();
            case 1 -> contact.getLastName();
            case 2 -> contact.getPhone();
            case 3 -> contact.getEmail();
            default -> null;
        };
    }
}
