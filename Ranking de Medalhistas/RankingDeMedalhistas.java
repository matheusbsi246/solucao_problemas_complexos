import java.util.Scanner;

class Main {

    public static void qSort(int[] v, String[] str, int i) {
        qs_aux(v, str, 0, i - 1);
    }

    public static void qs_aux(int[] v, String[] str, int inf, int sup) {
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

            while (j > 0 && v[--j] < pivot)
                ;

            if (j <= inf)
                break;

            if (j <= i)
                break;

            int tmp = v[i];
            v[i] = v[j];
            v[j] = tmp;

            String tmp2 = str[i];
            str[i] = str[j];
            str[j] = tmp2;
        }

        int tmp = v[i];
        v[i] = v[sup];
        v[sup] = tmp;

        String tmp2 = str[i];
        str[i] = str[sup];
        str[sup] = tmp2;
        
        qs_aux(v, str, inf, i - 1);
        qs_aux(v, str, i + 1, sup);
    }

    public static void main(String args[]) {
        int i, h;
        Scanner entrada = new Scanner(System.in);
        i = entrada.nextInt();
        entrada.nextLine();
        h = entrada.nextInt();
        entrada.nextLine();

        String[] str = new String[i];
        int vet[] = new int[i];

        int count = 0;
        int tamanho = i;

        while (i > 0) {
            vet[count] = entrada.nextInt();
            str[count] = entrada.nextLine();
            str[count] = str[count].substring(1, str[count].length());
            count++;
            i--;
        }
        qSort(vet, str, tamanho);
        for (int count2 = 0; count2 < h; count2++) {
            System.out.println("" + str[count2]);
        }
        entrada.close();
    }
}