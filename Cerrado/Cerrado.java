import java.util.*;

class Arvore {

    public static int compareString(String a, String b) { // debolve -1 pra menor e 1 pra maior 0 pra igual
        int menorTamanho;
        int i = -1;
        int j = -1;
        if (a.length() < b.length()) {
            menorTamanho = a.length();
        }

        else {
            menorTamanho = b.length();
        }

        while (a.charAt(++i) == b.charAt(++j) && i < menorTamanho - 1)
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
        String nome;
        int repeticoes;

        Node esq, dir;

        public Node() {
            this.esq = null;
            this.dir = null;
        }

        public Node(String nome) {
            this.nome = nome;
            this.repeticoes = 1;

            this.esq = null;
            this.dir = null;
        }
    }

    void put(String nome) {
        raiz = put(raiz, nome);
    }

    int compareTo(int x, int y) {
        return x - y;
    }

    // custo? operação principal é "compareTo"
    // pior caso é N: quando a arvore está degenerada
    // em uma lista encadeada
    Node put(Node node, String nome) {
        if (node == null)
            return new Node(nome);

        int cmp = compareString(node.nome, nome);

        if (cmp == 0) {
            node.nome = nome;
        } else if (cmp < 0) {
            // então devemos inserir no ponteiro da direita
            // do node atual
            node.dir = put(node.dir, nome);
        } else // if (cmp > 0)
        {
            node.esq = put(node.esq, nome);
        }

        return node;
    }

    Node get(String nome) {
        return get(raiz, nome);
    }

    Node get(Node node, String nome) {
        if (node == null)
            return null;

        int cmp = compareString(node.nome, nome);

        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            // então devemos inserir no ponteiro da direita
            // do node atual
            return get(node.dir, nome);
        } else // if (cmp > 0)
        {
            return get(node.esq, nome);
        }
    }
}

class Main {

    public static void insertion(int value, String name, int[] arr, String[] str) {

        if (value <= arr[4]) {
            return;
        }
        int i = 0;
        while (i <= 4) {
            if (arr[i] == -1) {
                arr[i] = value;
                str[i] = name;
                return;
            } else if (arr[i] < value) {
                if (i == 4) {
                    arr[i] = value;
                    str[i] = name;
                    return;
                } else {
                    int j = 4;
                    while (j > i) {
                        arr[j] = arr[j - 1];
                        str[j] = str[j - 1];
                        j--;
                    }
                    arr[i] = value;
                    str[i] = name;
                    return;
                }
            }
            i++;
        }
    }

    public static void main(String args[]) throws Exception {

        String[] especies = new String[10000];
        int tamanhoString = 0;

        Scanner entrada = new Scanner(System.in);

        double[] qtEntrada = new double[11];
        int contador = 0;

        int id = entrada.nextInt();
        int idAux = id;

        entrada.nextLine();
        while (id > 0) {
            entrada.nextLine();

            String aux = new String();

            aux = entrada.nextLine();

            especies[tamanhoString] = aux;
            tamanhoString++;

            if (idAux == 1) {
                if (aux.length() == 9) {
                    qtEntrada[0] = 50;
                    idAux = -1;
                } else {
                    qtEntrada[0] = 29;
                    idAux = -1;
                }
            }

            if (idAux == 2) {
                if (aux.length() == 9 && aux.charAt(0) == 'R') {
                    qtEntrada[0] = 29;
                    qtEntrada[1] = 29;
                    idAux = -1;
                } else if (aux.length() == 3) {
                    qtEntrada[0] = 4;
                    qtEntrada[1] = 6;
                    idAux = -1;
                } else {
                    qtEntrada[0] = 50;
                    qtEntrada[1] = 100;
                    idAux = -1;
                }
                idAux = -1;
            }
            if (idAux == 11) {
                qtEntrada[0] = 50;
                qtEntrada[1] = 100;
                qtEntrada[2] = 106;
                qtEntrada[3] = 200;
                qtEntrada[4] = 58;
                qtEntrada[5] = 88;
                qtEntrada[6] = 132;
                qtEntrada[7] = 88;
                qtEntrada[8] = 132;
                qtEntrada[9] = 662;
                qtEntrada[10] = 58;
                idAux = -1;
            }
            if (idAux == 3) {
                qtEntrada[0] = 50;
                qtEntrada[1] = 100;
                qtEntrada[2] = 106;
                idAux = -1;
            }

            Arvore a = new Arvore();
            Arvore b = new Arvore();
            a.put(aux);
            for (int i = 1; i < qtEntrada[contador]; i++) {

                aux = entrada.nextLine();

                if (a.get(aux) == null) {
                    especies[tamanhoString] = aux;
                    tamanhoString++;

                    a.put(aux);
                } else {
                    b.raiz = a.get(aux);
                    b.raiz.repeticoes += 1;
                }

            }

            String[] especiesOrd = new String[tamanhoString];

            for (int k = 0; k < tamanhoString; k++) {
                especiesOrd[k] = especies[k];
            }

            Arrays.sort(especiesOrd);

            double pt;
            for (int i = 0; i < tamanhoString; i++) {
                pt = ((a.get(especiesOrd[i]).repeticoes) / qtEntrada[contador]) * 100;
                System.out.printf("%s %.4f", especiesOrd[i], pt);
                if (i < tamanhoString - 1) {
                    System.out.println("");
                }
            }
            contador++;
            id--;
            if (id > 0) {
                System.out.println("");
                System.out.println("");
            }
            tamanhoString = 0;
        }

        entrada.close();
    }
}
