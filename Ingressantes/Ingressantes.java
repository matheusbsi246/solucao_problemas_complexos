import java.util.*;

class Arvore {

    public static int compareString(String a, String b) { // debolve -1 pra menor e 1 pra maior 0 pra igual
        int i = -1;
        int j = -1;
        while (a.charAt(++i) == b.charAt(++j) && i < a.length() - 1)
            ;
        if (a.charAt(i) < b.charAt(i)) {
            return -1;
        } else if (a.charAt(i) > b.charAt(i)) {
            return 1;
        } else {
            return 0;
        }
    }

    Node raiz;

    public Arvore() {
        raiz = null;
    }

    class Node {
        String id;
        int repeticoes;

        Node esq, dir;

        public Node() {
            this.esq = null;
            this.dir = null;
        }

        public Node(String id) {
            this.id = id;
            this.repeticoes = 1;

            this.esq = null;
            this.dir = null;
        }
    }

    void put(String id) {
        raiz = put(raiz, id);
    }

    int compareTo(int x, int y) {
        return x - y;
    }

    // custo? operação principal é "compareTo"
    // pior caso é N: quando a arvore está degenerada
    // em uma lista encadeada
    Node put(Node node, String id) {
        if (node == null)
            return new Node(id);

        int cmp = compareString(node.id, id);

        if (cmp == 0) {
            node.id = id;
        } else if (cmp < 0) {
            // então devemos inserir no ponteiro da direita
            // do node atual
            node.dir = put(node.dir, id);
        } else // if (cmp > 0)
        {
            node.esq = put(node.esq, id);
        }

        return node;
    }

    Node get(String id) {
        return get(raiz, id);
    }

    Node get(Node node, String id) {
        if (node == null)
            return null;

        int cmp = compareString(node.id, id);

        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            // então devemos inserir no ponteiro da direita
            // do node atual
            return get(node.dir, id);
        } else // if (cmp > 0)
        {
            return get(node.esq, id);
        }
    }
}

class Main {
    public static String getId(String str) {
        String[] parts = str.split(" ");
        Arrays.sort(parts);
        String id = parts[0] + parts[1] + parts[2] + parts[3] + parts[4];
        return id;
    }

    public static void main(String args[]) throws Exception {

        String[] combinacoes = new String[10000];
        int tamanhoString = 0;

        Scanner entrada = new Scanner(System.in);

        int en = entrada.nextInt();
        entrada.nextLine();

        String aux = new String();
        String id;

        int maiorCombinacao = 1;
        int resultado = 0;

        while (en != 0) {
            Arvore a = new Arvore();
            Arvore b = new Arvore();

            for (int i = 0; i < en; i++) {
                aux = entrada.nextLine();
                id = getId(aux);
                if (a.get(id) == null) {
                    combinacoes[tamanhoString] = id;
                    tamanhoString++;

                    a.put(id);
                } else {
                    b.raiz = a.get(id);
                    b.raiz.repeticoes += 1;
                    if (b.raiz.repeticoes > maiorCombinacao) {
                        maiorCombinacao = b.raiz.repeticoes;
                    }
                }

            }
            for (int i = 0; i < tamanhoString; i++) {
                if (a.get(combinacoes[i]).repeticoes == maiorCombinacao) {
                    resultado = maiorCombinacao + resultado;
                }
            }
            System.out.println(resultado);
            tamanhoString = 0;
            maiorCombinacao = 1;
            resultado = 0;
            en = entrada.nextInt();
            entrada.nextLine();
        }
        entrada.close();
    }
}
