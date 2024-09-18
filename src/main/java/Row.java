import java.util.ArrayList;

public class Row {
    ArrayList<Field> fields = new ArrayList<>();

    public Row(int row) {

        for (int column = 0; column < 3; column++) {
            Position position = new Position(row, column);
            fields.add(new Field(position));
        }
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void print() {
        System.out.println("            " + this.fields.get(0).getGameCharacter() + this.fields.get(1).getGameCharacter() + this.fields.get(2).getGameCharacter());

    }

    public boolean isFull() {
        for (Field field : fields) {
            if (field.isEmpty()) {
                return false;
            }
        }
        return true;
    }


}
