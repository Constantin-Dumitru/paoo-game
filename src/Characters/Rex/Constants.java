package Characters.Rex;

public class Constants {

    public static class Directions{
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
    }
    public static class PlayerConstants {
        public static final int WALKKING = 0;
        public static final int IDLE = 1;

        public static int GetAnimation(int player_action){
            switch(player_action){
                case WALKKING:
                    return 0;
                default:
                    return 1;
            }
        }
    }
}
