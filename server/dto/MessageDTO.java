package dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {
    private int FLAG;
    private int roomNumber;
    private String userName;
    private String contents;

    private MessageDTO() {}
    private MessageDTO(Builder builder) {
        this.FLAG = builder.FLAG;
        this.roomNumber = builder.roomNumber;
        this.userName = builder.userName;
        this.contents = builder.contents;
    }

    public static class Builder implements IBuilderDTO {
        // Essential
        private final int roomNumber;
        private final String userName;
        // Selective(MUST Initialization)
        private int FLAG            = 0;
        private String contents     = "";

        public Builder(int roomNumber, String name) {
            this.roomNumber = roomNumber;
            this.userName = name;
        }
        public Builder flag(int n) {
            this.FLAG = n;
            return this;
        }
        public Builder contents(String contents) {
            this.contents = contents;
            return this;
        }

        @Override
        public MessageDTO build() {
            return new MessageDTO(this);
        }
    }

    public int getFLAG() {
        return FLAG;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getName() {
        return userName;
    }

    public String getContents() {
        return contents;
    }
}
