public class Position {
    int row;
    int column;
    int index;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.index = row * 3 + column + 1;
    }

    public Position(int index) {
        this.index = index;
        this.row = (index - 1) / 3;
        this.column = (index - 1) % 3;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getIndex() {
        return index;
    }

    public int setIndex(int index) {
        this.index = index;
        return index;
    }
}
