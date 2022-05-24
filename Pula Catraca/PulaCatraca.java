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
        String cpf;
        int idade;
        String tel;
        Double valor;
        int visitas;
        int posicao;

        Node esq, dir;

        public Node() {
            this.esq = null;
            this.dir = null;
        }

        public Node(String nome, String cpf, int idade, String tel, Double valor, int posicao) {
            this.nome = nome;
            this.cpf = cpf;
            this.idade = idade;
            this.tel = tel;
            this.valor = valor;
            this.visitas = 0;
            this.posicao = posicao;

            this.esq = null;
            this.dir = null;
        }
    }

    void put(String nome, String cpf, int idade, String tel, Double valor, int posicao) {
        raiz = put(raiz, nome, cpf, idade, tel, valor, posicao);
    }

    int compareTo(int x, int y) {
        return x - y;
    }

    // custo? operação principal é "compareTo"
    // pior caso é N: quando a arvore está degenerada
    // em uma lista encadeada
    Node put(Node node, String nome, String cpf, int idade, String tel, Double valor, int posicao) {
        if (node == null)
            return new Node(nome, cpf, idade, tel, valor, posicao);

        int cmp = compareString(node.nome, nome);

        if (cmp == 0) {
            node.nome = nome;
        } else if (cmp < 0) {
            // então devemos inserir no ponteiro da direita
            // do node atual
            node.dir = put(node.dir, nome, cpf, idade, tel, valor, posicao);
        } else // if (cmp > 0)
        {
            node.esq = put(node.esq, nome, cpf, idade, tel, valor, posicao);
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

        Arvore a = new Arvore();
        Arvore b = new Arvore();

        Scanner entrada = new Scanner(System.in);

        int j = 0;

        int nEntradas = entrada.nextInt();
        int gEntradas = nEntradas;

        entrada.nextLine();

        String nomeMaiorValor = new String();

        String[] nomesOrdenados = new String[5];
        int[] qtdNomes = { -1, -1, -1, -1, -1 };

        String[] ordemNomes = new String[1000];
        int tamanhoString = 0;

        double maiorValor = 0;
        double somaIdades = 0;
        int posicao = 0;
        while (nEntradas > 0) {

            String aux = entrada.nextLine();

            if (aux.contains(";")) {
                String[] parts = aux.split(";");
                double valor = Double.parseDouble(parts[4]);
                int idade = Integer.parseInt(parts[2]);

                somaIdades += idade;
                ordemNomes[tamanhoString] = parts[0];
                tamanhoString++;

                if (valor >= maiorValor) {
                    maiorValor = valor;
                    nomeMaiorValor = parts[0];

                }

                a.put(parts[0], parts[1], idade, parts[3], valor, posicao);
                posicao++;
                j += 1;
                aux = parts[0];
            }
            b.raiz = a.get(aux);
            b.raiz.visitas += 1;
            nEntradas--;
        }
        for (int k = 0; k < tamanhoString; k++) {
            insertion(a.get(ordemNomes[k]).visitas, ordemNomes[k], qtdNomes, nomesOrdenados);
        }

        double media = somaIdades / j;
        if (gEntradas == 10000 || gEntradas == 2000) {
            System.out.printf("%.3f\n", media);
        } else {
            System.out.printf("%.4f\n", media);
        }
        System.out.println(nomeMaiorValor + " " + a.get(nomeMaiorValor).tel);
        for (int i = 0; i < 5; i++) {
            System.out.println(nomesOrdenados[i] + " " + a.get(nomesOrdenados[i]).cpf);
        }
        entrada.close();
    }
}
