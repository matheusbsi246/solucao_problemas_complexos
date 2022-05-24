import java.util.*;

class Main {
    public static void main(String args[]) {
        long[] sequencia = new long[200000];
        int n, j;
        int aux;
        int atual = 0, prox = 1;
        Scanner entrada = new Scanner(System.in);

        n = entrada.nextInt();
        entrada.nextLine();

        while (n > 0) {

            j = entrada.nextInt();
            for(aux = 0; aux < j; aux++){
                sequencia[aux] = entrada.nextInt();
            }

            while (sequencia[atual] != sequencia[prox] && prox < j) {
                atual++;
                prox++;
            }

            if (prox == j && sequencia[atual] != sequencia[prox]) {
                System.out.println("não monótona");

            } else {
                System.out.println("monótona");
            }
            atual = 0;
            prox = 1;
            n--;
        }
        entrada.close();
    }

}
