public class Problem117 {

    public static void PriannaTooFancy() {
        int n = 50;
        long[] tiles = new long[n+1];

        for (int i = 0; i < n+1; i++) {
            FillTiles(i, tiles);
        }

        long combos = tiles[n];
        System.out.println(combos);
    }

    public static void FillTiles(int n, long[] tiles){
       if (n == 0 || n == 1 || n == 2) {
            tiles[n] = n;
            return;
        }
        if (n == 3) {
            tiles[n] = 4;
            return;
        }
        if (n == 4) {
            tiles[n] = 8;
            return;
        }

        tiles[n] = tiles[n-1] + tiles[n-2] + tiles[n-3] + tiles[n-4];
    }

    public static void AlexComputer() {
        int n = 50;
        long tiles[] = new long[n];

        tiles[0] = 1;
        tiles[1] = 2;
        tiles[2] = 4;
        tiles[3] = 8;

        for (int i = 4; i < n; i++) {
            tiles[i] = tiles[i-1] + tiles[i-2] + tiles[i-3] + tiles[i-4];
        }

        System.out.println(tiles[n - 1]);
    }

    public static void main(String[] args) {
        AlexComputer();
    }
}
 