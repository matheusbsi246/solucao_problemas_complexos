import java.util.*;

class Arvore {

    Node raiz;

    public Arvore() {
        raiz = null;
    }

    class Node {
        int key;
        char c;
        int value;

        Node esq, dir;

        public Node() {
            this.esq = null;
            this.dir = null;
        }

        public Node(int key, char c) {
            this.key = key;
            this.c = c;
            this.value = 0;

            this.esq = null;
            this.dir = null;
        }
    }

    void put(int key, char c) {
        raiz = put(raiz, key, c);
    }

    int compareTo(int x, int y) {
        return x - y;
    }

    Node put(Node node, int key, char c) {
        if (node == null)
            return new Node(key, c);

        int cmp = compareTo(node.key, key);

        if (cmp == 0) {
            node.key = key;
        } else if (cmp < 0) {
            // então devemos inserir no ponteiro da direita
            // do node atual
            node.dir = put(node.dir, key, c);
        } else // if (cmp > 0)
        {
            node.esq = put(node.esq, key, c);
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
    public static void main(String args[]) {
        Scanner entrada = new Scanner(System.in);

        int questoes = entrada.nextInt();
        int alunos = entrada.nextInt();
        entrada.nextLine();
        int res = 0;

        Arvore a = new Arvore();
        Arvore b = new Arvore();

        char[][] al = new char[alunos][questoes];


        String str = entrada.nextLine();

        for (int i = 0; i < questoes; i++) {
            a.put(i, str.charAt(i));
        }

        for (int j = 0; j < alunos; j++) {
            str = entrada.nextLine();
            for (int i = 0; i < questoes; i++) {
                al[j][i] = str.charAt(i);
            }
        }

        for (int j = 0; j < alunos; j++) {
            for (int i = 0; i < questoes; i++) {
                b.raiz = a.get(i);
                if (al[j][i] == b.raiz.c && b.raiz.value == 0) {
                    b.raiz.value = 1;
                    res++;
                }
            }
            if (res == questoes) {
                System.out.println("" + (j + 1));
                return;
            }
        }

    }
}
