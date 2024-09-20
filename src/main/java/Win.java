

/*public class Win  {

    public boolean win() {
        List<int[]> winnerlist = List.of(new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}, new int[]{1, 4, 7}, new int[]{2, 5, 8}, new int[]{3, 6, 9}, new int[]{1, 5, 9}, new int[]{3, 5, 7});

        for (int[] i : winnerlist) {
            if (checkline(i, '♡')) {
                //playerWinn = true;
                return true;

            }
            if (checkline(i, '☮')) {
                //computerWinn = true;
                return true;
            }

        }


        return false;
    }

    public boolean checkline(int[] i, char gamecharacter) {

        for (int k : i) {
            if (fields.get(k).getGameCharacter() != gamecharacter) {
                return false;
            }

        }
        return true;

    }

    public boolean thisFieldIsUse(int key) {
        return fields.get(key).isUsed();


    }

    public ArrayList<Integer> computerFreeField() {
        ArrayList<Integer> freefields = new ArrayList<>();


        fields.keySet().forEach(key -> {
            if (!fields.get(key).isUsed()) {
                freefields.add(key);
            }
        });
        return freefields;
    }
}*/
