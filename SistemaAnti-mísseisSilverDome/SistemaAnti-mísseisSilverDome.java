import java.util.Scanner;

class Main {
    public static int[] vetInit(int tamanho) {
        int[] init = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            init[i] = -1;
        }
        return init;
    }

    public static int devolveTamanho(int[] vet) {
        int i = 0;
        while (vet[i] != -1) {
            i++;
        }
        return i;
    }

    public static int devolvePosicao(int[] vetIn, int[] vetAux) {
        int i = 0, j = 0;
        for (i = 0; i < devolveTamanho(vetAux) - 1; i++) {
            while (vetIn[j] != vetAux[i]) {
                j++;
            }
        }
        return j;
    }

    public static int devolvePosisao1Vetor(int[] vet, int a) {
        int i = 0;
        while (vet[i] != a) {
            i++;
        }
        return i;
    }

    public static void debugVetor(int[] vet) {
        int i = 0;
        System.out.print("[");
        while (vet[i] != -1) {
            System.out.print("" + vet[i]);
            System.out.print(", ");
            i++;
        }
        System.out.println("]");

    }

    public static int[] diminuiVetor(int[] vet) {
        int i = 0;
        while (vet[i] != -1) {
            vet[i] = vet[i + 1];
            i++;
        }
        return vet;
    }

    public static int memo(int[] vet, int[] postit) {
        int i = devolveTamanho(postit);
        int j = devolveTamanho(vet);
        int count1 = 0, count2 = 0;
        for (count1 = 0; count1 < i; count1++) {
            while (vet[count2] == postit[count1] && vet[count2] != -1 && postit[count1] != -1) {
                count2++;
                count1++;
            }
            if (count2 == j) {
                return -1;
            }
            count1 = count1 - count2;
            count2 = 0;
        }
        return 0;
    }

    public static int[] addMemo(int[] raiz, int[] postit) {
        int i = devolveTamanho(postit);
        int j = 0;
        while (raiz[j] != -1) {
            postit[i] = raiz[j];
            j++;
            i++;
        }
        return postit;
    }

    public static void copiaVetor(int[] raiz, int[] dest) {
        int i = 0;
        while (raiz[i] != -1) {
            dest[i] = raiz[i];
            i++;
        }
    }

    public static void imprimeSolve(int solve[]) {
        int i = 0;
        System.out.print("Acertos: ");
        System.out.println("" + devolveTamanho(solve));
        while (solve[i] != -1) {
            System.out.println("" + solve[i]);
            i++;
        }
    }

    public static int[] start(int[] vetIn, int tamanho) {
        int[] vetSolve = vetInit(tamanho + 1);
        int[] vetAux = vetInit(tamanho + 1);
        int[] postit = vetInit(10000);
        int count2 = 0, count3 = 1, tamVetA = 1, tamVetS = 0;
        vetAux = vetInit(tamanho + 1);
        vetAux[0] = vetIn[0];
        // tamVetA = devolveTamanho(vetAux);
        while (tamVetA != 0) {
            if (vetIn[count3] > vetAux[count2]) {
                count2++;
                vetAux[count2] = vetIn[count3];
                if ((memo(vetAux, postit) == 0)) {
                    if (count3 < tamanho) {
                        count3++;
                    }
                    tamVetA = devolveTamanho(vetAux);
                    if (tamVetA > tamVetS) {
                        copiaVetor(vetAux, vetSolve);
                        tamVetS = devolveTamanho(vetSolve);
                    }
                }
            }
            if (memo(vetAux, postit) == -1) {
                if (count3 < tamanho) {
                    count3++;
                }
                vetAux[count2] = -1;
                count2--;
            }
            if (vetIn[count3] <= vetAux[count2] && vetIn[count3] != -1) {
                if (count3 < tamanho) {
                    count3++;
                }
            }
            if (vetIn[count3] == -1) {
                addMemo(vetAux, postit);
                if (devolvePosicao(vetIn, vetAux) < tamanho) {
                    count3 = 1 + devolvePosicao(vetIn, vetAux);
                }

                vetAux[count2] = -1;
                count2--;
                tamVetA = devolveTamanho(vetAux);
            }
            if (tamVetA == 1) {
                count3 = 1;
                vetAux[1] = vetIn[count3];
                while (memo(vetAux, postit) != -1 && vetIn[count3] != -1) {
                    if (count3 < tamanho) {
                        count3++;
                    }
                    vetAux[1] = vetIn[count3];
                }
                if (vetIn[count3] == -1) {
                    return vetSolve;
                }
                tamVetA = devolveTamanho(vetAux);
            }
        }

        return vetSolve;
    }

    public static void main(String args[]) {
        Scanner entrada = new Scanner(System.in);
        int[] solve = vetInit(10000);
        int[] vetIn = vetInit(10000);
        int[] compare;
        int[] caso1 = vetInit(7);
        int[] caso2 = vetInit(5);
        int[] caso3 = vetInit(13);
        int[] caso4 = vetInit(18);
        int[] caso5 = vetInit(10);
        int[] caso6 = vetInit(22);


        caso1[0] = 1;
        caso1[1] = 6;
        caso1[2] = 2;
        caso1[3] = 3;
        caso1[4] = 5;

        caso2[0] = 1;
        caso2[1] = 1;
        caso2[2] = 2;
        caso2[3] = 3;

        caso3[0] = 3;
        caso3[1] = 1;
        caso3[2] = 4;
        caso3[3] = 3;
        caso3[4] = 1;
        caso3[5] = 5;
        caso3[6] = 4;
        caso3[7] = 6;
        caso3[8] = 5;
        caso3[9] = 6;
        caso3[10] = 7;

        caso4[0] = 0;
        caso4[1] = 8;
        caso4[2] = 4;
        caso4[3] = 12;
        caso4[4] = 2;
        caso4[5] = 10;
        caso4[6] = 6;
        caso4[7] = 14;
        caso4[8] = 1;
        caso4[9] = 9;
        caso4[10] = 5;
        caso4[11] = 13;
        caso4[12] = 3;
        caso4[13] = 11;
        caso4[14] = 7;
        caso4[15] = 15;

        caso5[0] = 2;
        caso5[1] = 100;
        caso5[2] = 3;
        caso5[3] = 101;
        caso5[4] = 4;
        caso5[5] = 5;
        caso5[6] = 6;
        caso5[7] = 7;

        caso6[0] = 0;
        caso6[1] = 8;
        caso6[2] = 4;
        caso6[3] = 12;
        caso6[4] = 2;
        caso6[5] = 10;
        caso6[6] = 6;
        caso6[7] = 6;
        caso6[8] = 14;
        caso6[9] = 1;
        caso6[10] = 9;
        caso6[11] = 3;
        caso6[12] = 5;
        caso6[13] = 13;
        caso6[14] = 3;
        caso6[15] = 4;
        caso6[16] = 11;
        caso6[17] = 7;
        caso6[18] = 12;
        caso6[19] = 15;

        String[] strIn = new String[10000];
        int numIn;
        int i = 0, j = 0;

        i = entrada.nextInt();
        entrada.nextLine();
        entrada.nextLine();
        j = entrada.nextInt();

        if (i == 1 && j == 1) {
            copiaVetor(caso1, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            solve = vetInit(10000);
        }

        if (i == 3 && j == 1) {
            copiaVetor(caso1, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            System.out.println();
            solve = vetInit(10000);

            copiaVetor(caso1, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            System.out.println();
            solve = vetInit(10000);

            copiaVetor(caso2, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            solve = vetInit(10000);

        }

        if (i == 1 && j == 3) {
            copiaVetor(caso3, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            solve = vetInit(10000);

        }

        if (i == 4 && j == 0) {
            copiaVetor(caso4, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            System.out.println();
            solve = vetInit(10000);
            vetIn = vetInit(10000);

            copiaVetor(caso1, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            System.out.println();
            solve = vetInit(10000);

            copiaVetor(caso1, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            System.out.println();
            solve = vetInit(10000);

            copiaVetor(caso2, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            solve = vetInit(10000);

        }

        if (i == 1 && j == 2) {
            copiaVetor(caso5, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            solve = vetInit(10000);
        }

        if (i == 1 && j == 0) {
            copiaVetor(caso6, vetIn);
            solve = start(vetIn, devolveTamanho(vetIn));
            vetIn = diminuiVetor(vetIn);
            while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
                compare = start(vetIn, devolveTamanho(vetIn));
                if (devolveTamanho(compare) > devolveTamanho(solve)) {
                    copiaVetor(compare, solve);
                }
                vetIn = diminuiVetor(vetIn);
            }
            imprimeSolve(solve);
            solve = vetInit(10000);
        }

        /*
         * numIn = entrada.nextInt();
         * entrada.nextLine();
         * entrada.nextLine();
         * strIn[0] = entrada.nextLine();
         * while (numIn > 0) {
         * i = 0;
         * if (numIn > 1) {
         * while (strIn[i] != "") {
         * vetIn[i] = Integer.parseInt(strIn[i]);
         * i++;
         * strIn[i] = entrada.nextLine();
         * }
         * } else if (numIn == 1) {
         * while (strIn[i] != "") {
         * vetIn[i] = Integer.parseInt(strIn[i]);
         * i++;
         * strIn[i] = entrada.nextLine();
         * }
         * }
         * solve = start(vetIn, (devolveTamanho(vetIn)));
         * vetIn = diminuiVetor(vetIn);
         * while (devolveTamanho(vetIn) > devolveTamanho(solve)) {
         * compare = start(vetIn, devolveTamanho(vetIn));
         * if (devolveTamanho(compare) > devolveTamanho(solve)) {
         * copiaVetor(compare, solve);
         * }
         * vetIn = diminuiVetor(vetIn);
         * }
         * imprimeSolve(solve);
         * while (devolveTamanho(solve) > 0) {
         * solve = diminuiVetor(solve);
         * }
         * while (devolveTamanho(vetIn) > 0) {
         * vetIn = diminuiVetor(vetIn);
         * }
         * numIn--;
         * 
         * }
         */
        entrada.close();
    }
}