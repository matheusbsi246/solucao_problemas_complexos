import java.util.Scanner;

class Main {
    public static long fatorial(int i) {
        long l = i;
        int j = i - 1;
        while (j > 1) {
            l = l * j;
            j--;
        }
        return l;
    }

    public static void main(String args[]) {
        int i, h;
        Scanner entrada = new Scanner(System.in);
        i = entrada.nextInt();
        h = entrada.nextInt();
        entrada.nextLine();
        long[] vet = new long[h];

        for (int j = 0; j < h; j++) {
            String str = new String();
            str = entrada.nextLine();
            vet[j] = fatorial(str.length());
        }
        for (int j = 0; j < h; j++) {
            System.out.println(vet[j]);
        }
        entrada.close();
    }

}