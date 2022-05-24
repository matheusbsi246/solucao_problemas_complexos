import java.util.*;

class Arvore {

    Node raiz;

    public Arvore() {
        raiz = null;
    }

    class Node {
        String cidade;
        int valor;
        String conecta1 = null;
        String conecta2;

        Node esq, dir;

        public Node() {
            this.esq = null;
            this.dir = null;
        }

        public Node(String cidade, int valor) {
            this.cidade = cidade;
            this.valor = valor;

            this.esq = null;
            this.dir = null;
        }
    }

    void put(String cidade, int valor) {
        raiz = put(raiz, cidade, valor);
    }

    int compareTo(int x, int y) {
        return x - y;
    }

    // custo? operação principal é "compareTo"
    // pior caso é N: quando a arvore está degenerada
    // em uma lista encadeada
    Node put(Node node, String cidade, int valor) {
        if (node == null)
            return new Node(cidade, valor);

        int cmp = compareTo(node.valor, valor);

        if (cmp == 0) {
            node.cidade = cidade;
        } else if (cmp < 0) {
            // então devemos inserir no ponteiro da direita
            // do node atual
            node.dir = put(node.dir, cidade, valor);
        } else // if (cmp > 0)
        {
            node.esq = put(node.esq, cidade, valor);
        }

        return node;
    }

    Node get(int valor) {
        return get(raiz, valor);
    }

    Node get(Node node, int valor) {
        if (node == null)
            return null;

        int cmp = compareTo(node.valor, valor);

        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            // então devemos inserir no ponteiro da direita
            // do node atual
            return get(node.dir, valor);
        } else // if (cmp > 0)
        {
            return get(node.esq, valor);
        }
    }
}

class Main {

    public static void main(String args[]) throws Exception {

        Arvore a = new Arvore();
        Arvore b = new Arvore();

        Scanner entrada = new Scanner(System.in);

        int cEntradas = entrada.nextInt();
        entrada.nextLine();
        String destino = entrada.nextLine();
        String capital = entrada.nextLine();
        String res[] = new String[1000];

        a.put(capital, 0);

        for (int i = 0; i < cEntradas - 1; i++) {

            String str = new String();
            str = entrada.nextLine();
            String[] cidades = str.split(" - ");
            a.put(cidades[0], i);
            b.raiz = a.get(i);
            b.raiz.conecta1 = cidades[1];

        }
        String str = destino;
        String str2 = new String();
        int k = 0;
        int i = 0;
        while (!Objects.equals(str, capital)) {
            i = 0;
            while (!Objects.equals(str, str2)) {
                b.raiz = a.get(i);
                str2 = b.raiz.cidade;
                i++;
            }
            res[k] = str;
            k++;
            str = b.raiz.conecta1;
        }
        System.out.println("" + capital);
        for (int c = k - 1; c > 0; c--) {
            System.out.println("" + res[c]);
        }
        System.out.println("" + destino);

        entrada.close();
    }

}
