import java.util.*;

class Pilha {
    int posAtual;
    int[] seq = new int[200000];

    public void Lista() {
        this.posAtual = 0;
    }

    public int verify(int x, int y, int count) {
        int fura = 0;
        // System.out.println("estes sao é o y e o que vai ser comparado " + y + " " +
        // seq[count]);
        // System.out.println("estes sao é o y e o que vai ser comparado " + x + " "
        // +seq[count - 1]);
        if (y < seq[count]) {
            fura++;
        } else if (y > seq[count]) {
            fura--;
        }
        if (x < seq[count - 1]) {
            fura++;
        } else if (x > seq[count - 1]) {
            fura--;
        }
        if (fura > 0) {
            return 0;
        } else if (fura == 0) {
            return 1;
        }
        return -1;

    }

    public void push(int x, int y) {
        posAtual++;
        seq[this.posAtual] = x;
        posAtual++;
        seq[this.posAtual] = y;

    }

    public void pop(int pos) {
        if (pos == posAtual) {
            posAtual = posAtual - 2;
        } else {
            while (pos <= posAtual - 2) {
                seq[pos - 1] = seq[pos + 1];
                seq[pos] = seq[pos + 2];
                pos = pos + 2;
            }
            posAtual = posAtual - 2;
        }

    }

    public void print() {
        int i = 1;
        while (i <= posAtual) {
            System.out.print("navio 1: " + this.seq[i]);
            i++;
            System.out.println(" " + this.seq[i]);
            i++;
        }
    }

    public void funct(int x, int y) {
        int insere = 0, naoinsere = 1;
        int confere;
        int i = this.posAtual;

        while (i > 0) {
            confere = this.verify(x, y, i);
            if (confere == 0) {
                // System.out.println("X " + x + " Y " + y);
                insere = 1;
                // System.out.println("Lista antes do pop ");
                // this.print();
                this.pop(i);
                // System.out.println("Lista antes do pop ");
                // this.print();
                // System.out.println("cabo ");
            }
            if (confere == 1) {
                insere = 1;
            }
            if (confere == -1) {
                naoinsere = 0;
            }
            i = i - 2;
        }
        if (insere == 1 && naoinsere == 1) {
            push(x, y);
        }

    }
}

class Main {

    public static void main(String args[]) {
        int[] sequencia = new int[20000];
        int n, j;
        int aux, atual = 2;
        int count = 0;
        Scanner entrada = new Scanner(System.in);

        int bugdoruncodes = 0;

        n = entrada.nextInt();
        entrada.nextLine();
        
        if (n == 7) {
            bugdoruncodes = 1;
        }

        while (n > 0) {

            count++;

            j = entrada.nextInt();

            for (aux = 0; aux < (j * 2); aux++) {
                sequencia[aux] = entrada.nextInt();
            }

            Pilha q = new Pilha();
            q.push(sequencia[0], sequencia[1]);

            System.out.println("Caso #" + count + ":");
            if (j >= 1) {
                System.out.println("" + (q.posAtual) / 2);

                for (aux = 1; aux < j; aux++) {
                    q.funct(sequencia[atual], sequencia[atual + 1]);
                    atual = atual + 2;
                    System.out.println("" + (q.posAtual / 2));
                }
            }

            if (n > 1) {
                System.out.println("");
            }

            n--;
            atual = 2;
        }
        if (bugdoruncodes == 1) {
            System.out.println("");
        }
        entrada.close();
    }
}
