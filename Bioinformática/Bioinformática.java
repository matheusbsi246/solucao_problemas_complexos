import java.util.Scanner;

class Main {
    public static int devolvePosicaoDoMenor(int[] vet, int tam) {
        int posicao, aux, j = 0;
        int vetPos;
        while(vet[j] == -1){
            j++;
        }
        vetPos = vet[j];
        posicao = j;
        for (aux = 1; aux < tam; aux++) {
            if (vet[aux] < vetPos && vet[aux] != -1) {
                vetPos = vet[aux];
                posicao = aux;
            }
        }
        return posicao;
    }

    public static void main(String args[]) {
        int m;
        Scanner entrada = new Scanner(System.in);
        m = entrada.nextInt();
        while (m > 0) {

            int n, k, count;
            int[] memo = new int[4];
            int s = 0;
            n = entrada.nextInt();
            k = entrada.nextInt();
            entrada.nextLine();
            int[] vet = new int[k];
            int posicao;
            char[] dnaC = new char[n];
            String[] dna = new String[k];

            for (int i = 0; i < k; i++) {
                dna[i] = entrada.nextLine();
                for (int aux = 0; aux < n; aux++) {
                    dnaC[aux] = dna[i].charAt(aux);
                }
                s = 0;
                memo[0] = 0;
                memo[1] = 0;
                memo[2] = 0;
                memo[3] = 0;
                for (count = n - 1; count >= 0; count--) {
                    if (dnaC[count] == 'A') {
                        memo[0] = memo[0] + 1;
                    }
                    if (dnaC[count] == 'C') {
                        memo[1] = memo[1] + 1;
                        s = s + memo[0];
                    }
                    if (dnaC[count] == 'G') {
                        memo[2] = memo[2] + 1;
                        s = s + memo[0] + memo[1];
                    }
                    if (dnaC[count] == 'T') {
                        memo[3] = memo[3] + 1;
                        s = s + memo[0] + memo[1] + memo[2];
                    }
                }
                vet[i] = s;
            }
            for (int i = 0; i < k; i++) {
                posicao = devolvePosicaoDoMenor(vet, k);
                vet[posicao] = -1;    
                System.out.println("" + dna[posicao]);
            }
            m--;
            if(m > 0){
                System.out.println("");
            }

        }
        entrada.close();
    }
}
