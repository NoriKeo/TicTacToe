public class Move {
    Position position;
    char gamecharacter;

    public Move(Position position, char gamecharacter) {
        this.position = position;
        this.gamecharacter = gamecharacter;
    }

    public Position getPosition() {
        return position;
    }


    public char getGamecharacter() {
        return gamecharacter;
    }


}
