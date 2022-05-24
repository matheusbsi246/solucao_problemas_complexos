import java.util.*;

class Main {

    static int[][] memo = new int[5500][5500];

    public static int solve(String S) {
        for (int i = 0; i < memo.length; i++)
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
                if (j <= i)
                    memo[i][j] = 0;
            }
        char[] c = new char[1000];
        c = S.toCharArray();
        return solveRec(c, 0, S.length() - 1);
    }

    public static int solveRec(char[] S, int i, int j) {

        if (memo[i][j] >= 0)
            return memo[i][j];

        if (S[i] == S[j])

        {
            memo[i][j] = solveRec(S, i + 1, j - 1);
            return memo[i][j];
        } else {
            int op1 = solveRec(S, i, j - 1) + 1;
            int op2 = solveRec(S, i + 1, j) + 1;

            memo[i][j] = Math.min(op1, op2);
            return memo[i][j];
        }
    }

    public static void main(String args[]) {
        int nEntradas;
        String[] sequencia = new String[100];
        Scanner entrada = new Scanner(System.in);
        nEntradas = entrada.nextInt();
        entrada.nextLine();

        for (int aux = 0; aux < nEntradas; aux++) {
            sequencia[aux] = entrada.nextLine();
        }
        for (int aux2 = 0; aux2 < nEntradas; aux2++) {
            System.out.println("" + solve(sequencia[aux2]));
        }
        entrada.close();

    }

}
