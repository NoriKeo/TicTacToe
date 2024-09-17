import java.util.ArrayList;

public class Board {
    ArrayList<Row> rows = new ArrayList<Row>();

    public Board() {

        for (int row = 0; row < 3; row++) {

            rows.add(new Row(row));
        }
    }

    public void print() {
        System.out.println("✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮");
        for (Row row : rows) {
            row.print();

        }
        System.out.println("✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮");

    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public boolean isFull() {
        for (Row row : rows) {
            if (!row.isFull()) {
                return false;
            }
        }
        return true;
    }
}
