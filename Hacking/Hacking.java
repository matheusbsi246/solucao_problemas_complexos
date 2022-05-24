import java.util.Scanner;

class Main {

    public static void qSort(int[] v, char[] memo, int tamanho) {
        qs_aux(v, memo, 0, tamanho - 1);
    }

    public static void qs_aux(int[] v, char[] memo, int inf, int sup) {
        if (inf >= sup)
            return;
        int i = inf - 1;
        int j = sup;
        int pivot = v[sup];
        while (true) {
            while (v[++i] > pivot)
                ;

            if (i >= sup)
                break;

            while (v[--j] < pivot)
                ;

            if (j <= inf)
                break;

            if (j <= i)
                break;

            int tmp = v[i];
            v[i] = v[j];
            v[j] = tmp;

            char tmp2 = memo[i];
            memo[i] = memo[j];
            memo[j] = tmp2;
        }

        int tmp = v[i];
        v[i] = v[sup];
        v[sup] = tmp;

        char tmp2 = memo[i];
        memo[i] = memo[sup];
        memo[sup] = tmp2;

        qs_aux(v, memo, inf, i - 1);
        qs_aux(v, memo, i + 1, sup);
    }

    static void ordenaMemo(char[] memo, int vet[], int tamanho, int inf, int sup) {
        int count, count2;
        for (count = inf; count <= sup; count++) {
            char tmp = memo[count];
            int pos = count;
            int posTmp = pos;
            for (count2 = count + 1; count2 <= sup; count2++) {
                if (memo[count2] < tmp) {
                    tmp = memo[count2];
                    posTmp = count2;
                }
            }
            count2 = posTmp;
            while (count2 > count) {
                memo[count2] = memo[count2 - 1];
                count2--;
            }
            memo[pos] = tmp;
        }
    }

    public static void letrasRepeticoes(String str) { // funcao que ira retornar um array das letras já ordenadas
        char c; // vetor de char pra percorrer a String
        int[] vet = new int[27]; // vetor que ira verificar o numero de repetições das letras da string
        char[] memo = new char[27]; // vetor que ira verificar o numero de repetições das letras da string
        int tamanho = 0; // variavel que ira acompanhar o tamho do vetor 'vet'
        int i = 0;
        while (i < str.length()) { // percorre a string
            c = str.charAt(i);
            if (c != ' ' && Character.isLetter(c)) {
                int auxiliar = tamanho;
                c = str.charAt(i);
                while (auxiliar >= 0) {

                    if (auxiliar == 0 && c != memo[auxiliar]) {
                        vet[tamanho] = 1; // coloca 1, pois é a primeira vez que aquela letra está aparecendo
                        memo[tamanho] = c; // adiciona a letra no memo;
                        tamanho++; // tamanho do nosso memo, ou seja, a quantidade de letras que ja entraram
                        break;
                    }

                    else if (c == memo[auxiliar]) {
                        vet[auxiliar]++; // coloca mais uma repetição na posição
                        break;
                    }

                    auxiliar--;
                }
            }
            i++;
        }
        qSort(vet, memo, tamanho);
        int tmp2 = 0;
        int j = -1;

        while (true) { // organizando as posições pra mandar pra funcao que ira retornaro vetor de char
            while (vet[++j] != vet[j + 1] && j < tamanho)
                ;

            if (j >= tamanho - 1)
                break;
            tmp2 = j;
            while (vet[++j] == vet[j + 1] && j < tamanho)
                ;
            ordenaMemo(memo, vet, tamanho, tmp2, j);

        }
        for (int count = 0; count < tamanho; count++) {
            System.out.printf("%c %d", memo[count], vet[count]);
            if(count < tamanho - 1){
                System.out.println("");
            }
           
        }

    }

    public static void main(String args[]) {
        int i;
        Scanner entrada = new Scanner(System.in);

        i = entrada.nextInt();
        String m = new String();

        while (i >= 0) {
            m = m + entrada.nextLine();
            i--;
        }

        m = m.toUpperCase();

        letrasRepeticoes(m);
        entrada.close();
    }
}