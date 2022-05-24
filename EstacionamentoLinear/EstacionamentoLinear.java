import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

class Main {
    public static Integer solveE1(int[] vet, int tam) {
        Integer sol = 0;
        for (int i = 0; i < tam; i++) {
            sol = sol + (vet[i] * vet[i]);
        }
        return sol;
    }

    public static Integer solveE2(int[] vet, int tam) {
        Integer sol = 0;
        for (int i = 0; i < tam - 1; i++) {
            sol = sol + ((vet[i] + vet[i + 1]) * (vet[i] + vet[i + 1]));
        }
        return sol;
    }

    public static boolean arrayRepeat(Integer[] iVet, int tam) {
        while (tam > 1) {
            if (iVet[tam - 1] == iVet[tam - 2]) {
                return true;
            }
            tam--;
        }
        return false;
    }

    public static int solveArray(Integer[] iVet, int tam) {
        Integer[] auxVet = new Integer[tam];
        int i = 2;
        for (int j = 0; j < tam; j++) {
            auxVet[j] = iVet[j];
        }
        auxVet[tam - 1] = 0;
        auxVet[tam - 2] = 0;
        while (arrayRepeat(auxVet, tam)) {

            for (int j = 0; j < tam; j++) {
                auxVet[j] = iVet[j] % i;
            }
            i++;
            if(i > 100000){
                return -1;
            }
            Arrays.sort(auxVet);
        }

        int maior = 0;
        for (int j = 0; j < tam; j++) {
            if (maior < auxVet[j])
                maior = auxVet[j];
        }

        return i - 1;
    }

    public static int leitura(String nomeArq, String[] str) {
        int j = -1;
        try {
            FileReader ent = new FileReader(nomeArq);
            BufferedReader br = new BufferedReader(ent);

            j = 0;
            while ((str[j] = br.readLine()) != null) {

                j++;
            }

            br.close();
        } catch (IOException erro) {
            System.out.println(" Erro na leitura dos dados ");
        } // F
        return j;
    }

    public static void main(String args[]) {

        Integer E1, E2, E3;

        Scanner entrada = new Scanner(System.in);

        Integer iVet[] = new Integer[10000];

        String[] str = new String[10000];

        int w = 0;

        int tam = 0;

        while (w != 32) {
            str[tam] = entrada.nextLine();
            w = str[tam].charAt(0);
            tam++;
        }
        tam--;

        int[] vet = new int[10000];

        for (int j = 0; j < tam; j++) {

            for (int k = 0; k < str[j].length(); k++) {

                vet[k] = str[j].charAt(k);
            }

            E1 = solveE1(vet, str[j].length());
            E2 = solveE2(vet, str[j].length());
            E3 = E1 + E2;
            iVet[j] = E3;
        }
        entrada.close();
        int solve = solveArray(iVet, tam);
        System.out.println(solve);
    }

}
