package main;

public class Player {
        private String name;
        private String playing_position;
        private String description;
        //poza
        private boolean available = true;

        //constructor
        public Player(String name, String playing_position, String description) {
            this.name = name;
            this.playing_position = playing_position;
            this.description = description;
        }

        //getters and setters
        public String getPlaying_position() {
            return playing_position;
        }

        public void setPlaying_position(String playing_position) {
            this.playing_position = playing_position;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
