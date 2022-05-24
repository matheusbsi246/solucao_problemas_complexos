import java.util.*;

class Arvore {

    Node raiz;

    public Arvore() {
        raiz = null;
    }

    class Node {
        int key;

        Node esq, dir;

        public Node() {
            this.esq = null;
            this.dir = null;
        }

        public Node(int key) {
            this.key = key;

            this.esq = null;
            this.dir = null;
        }
    }

    void put(int key) {
        raiz = put(raiz, key);
    }

    int compareTo(int x, int y) {
        return x - y;
    }

    Node put(Node node, int key) {
        if (node == null)
            return new Node(key);

        int cmp = compareTo(node.key, key);

        if (cmp == 0) {
            node.key = key;
        } else if (cmp < 0) {
            // então devemos inserir no ponteiro da direita
            // do node atual
            node.dir = put(node.dir, key);
        } else // if (cmp > 0)
        {
            node.esq = put(node.esq, key);
        }

        return node;
    }

    Node get(int key) {
        return get(raiz, key);
    }

    Node get(Node node, int key) {
        if (node == null)
            return null;

        int cmp = compareTo(node.key, key);

        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            // então devemos inserir no ponteiro da direita
            // do node atual
            return get(node.dir, key);
        } else // if (cmp > 0)
        {
            return get(node.esq, key);
        }
    }
}

class Main {
    public static void solve(int entrada, int[] vet) {
        if (entrada == 0) {
            System.out.println("0");
            return;
        }
        Arvore a;
        int i;
        int tamAtual = 0;
        int maiorTam = 1;
        int n = entrada;
        while (n > 0) {
            a = new Arvore();
            i = entrada - n;
            a.put(vet[i]);
            i++;
            tamAtual = 1;
            while (i < n) {
                while (i < entrada && a.get(vet[i]) == null) {
                    a.put(vet[i]);
                    i++;
                    tamAtual++;
                }
                // Pegar o tamanho da arvore e comparar com o maior tamanho
                if (tamAtual > maiorTam)
                    maiorTam = tamAtual;
                // Sobrescrever a arvore atual tudo isso enquanto o i não chega no tamanho do
                // vetor
                a = new Arvore();
                if (i != entrada) {
                    a.put(vet[i]);
                }
                tamAtual = 1;
                i++;
            }
            n--;
        }

        System.out.println("" + maiorTam);

    }

    public static void main(String args[]) {
        Scanner entrada = new Scanner(System.in);
        int casos = entrada.nextInt();
        int n;
        while (casos > 0) {
            n = entrada.nextInt();
            int[] vet = new int[n];
            for (int i = 0; i < n; i++) {
                vet[i] = entrada.nextInt();
            }
            solve(n, vet);
            casos--;
        }
        entrada.close();
    }
}
