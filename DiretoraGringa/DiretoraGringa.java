import java.util.Scanner;

class Main {

    public static String get_last_name(String str) {
        char[] c = new char[str.length()];
        int i = str.length();
        i--;
        int j2 = 0;
        int j = 0;
        while (c[j2] != ' ' && i >= 0) {

            c[j] = str.charAt(i);
            j++;
            j2 = j - 1;
            i--;
        }
        if (i == -1) {
            j2++;
        }
        char[] c2 = new char[j2];
        j2 = j2 - 1;
        for (int k = 0; k <= j2; k++) {
            c2[k] = c[j2 - k];
        }
        String rtn = String.valueOf(c2);
        return rtn;
    }

    public static String get_first_name(String str) {
        String aux = new String();
        aux = get_last_name(str);
        if (aux.length() == str.length()) {
            return "";
        }
        char[] c = new char[str.length() - aux.length() - 1];
        int j = 0;
        for (j = 0; j < str.length() - aux.length() - 1; j++) {
            c[j] = str.charAt(j);
        }
        char[] c2 = new char[j];
        for (int k = 0; k < j; k++) {
            c2[k] = c[k];
        }
        String rtn = String.valueOf(c2);
        return rtn;
    }

    public static String[] insert(String[] str, String cpy, int pos, int tamanho) {

        if (str[pos] == "VAZIO") {
            str[pos] = String.copyValueOf(cpy.toCharArray());
            return str;
        } else {
            for (int i = tamanho; i > pos; i--) {
                str[i] = String.copyValueOf(str[i - 1].toCharArray());
            }
            str[pos] = String.copyValueOf(cpy.toCharArray());
            return str;
        }
    }

    public static int find_pos(String[] str, String str2, int tamanho) {
        String str_ln = new String();
        String str2_ln = new String();
        String str_fn = new String();
        String str2_fn = new String();
        str2_ln = get_last_name(str2);
        for (int i = 0; i < tamanho; i++) {
            str_ln = get_last_name(str[i]);
            if (str2_ln.charAt(0) > str_ln.charAt(0)) {
                continue;
            } else if (str2_ln.charAt(0) < str_ln.charAt(0)) {
                return i;
            } else if (str_ln.length() == str2_ln.length()) {
                if (str_ln.equals(str2_ln)) {
                    str_fn = get_first_name(str[i]);
                    str2_fn = get_first_name(str2);
                    if (str2_fn == "") {
                        continue;
                    }
                    if(str_fn==""){
                        return i;
                    }
                    int j = 0;
                    if (str2_fn.length() < str_fn.length()) {
                        while (str_fn.charAt(j) == str2_fn.charAt(j) && j < str2_fn.length() - 1) {
                            j++;
                        }
                        if (str2_fn.charAt(j) > str_fn.charAt(j)) {
                            return i;
                        }
                    } else {
                        while (str_fn.charAt(j) == str2_fn.charAt(j) && j < str_fn.length() - 1) {
                            j++;
                        }
                        if (str2_fn.charAt(j) > str_fn.charAt(j)) {
                            return i;
                        }
                        if (j == str2_fn.length() - 1 && str_fn.charAt(j) == str2_fn.charAt(j)) {
                            return i;
                        }
                    }
                } else {
                    int j = 0;
                    while (str_ln.charAt(j) == str2_ln.charAt(j) && j < str2_ln.length() - 1) {
                        j++;
                    }
                    if (str2_ln.charAt(j) < str_ln.charAt(j)) {
                        return i;
                    }
                }
            } else if (str_ln.length() > str2_ln.length()) {
                int j = 0;
                while (str_ln.charAt(j) == str2_ln.charAt(j) && j < str2_ln.length() - 1) {
                    j++;
                }
                if (str2_ln.charAt(j) < str_ln.charAt(j)) {
                    return i;
                }

            } else if (str_ln.length() < str2_ln.length()) {
                int j = 0;
                while (str2_ln.charAt(j) == str_ln.charAt(j) && j < str_ln.length() - 1) {
                    j++;
                }
                if (str2_ln.charAt(j) < str_ln.charAt(j)) {
                    return i;
                }

            }
        }
        return tamanho;
    }

    public static void main(String args[]) {
        Scanner entrada = new Scanner(System.in);
        int k = entrada.nextInt();
        String[] str = new String[k];
        String[] arg = new String[k];
        String aux = new String();
        int posicao;
        int tamanho = 0;
        for (int i = 0; i < k; i++) {
            arg[i] = "VAZIO";
        }
        entrada.nextLine();
        for (int i = 0; i < k; i++) {
            str[i] = entrada.nextLine();
        }
        for (int i = 0; i < k; i++) {
            aux = String.copyValueOf(str[i].toCharArray());
            posicao = find_pos(arg, aux, tamanho);
            insert(arg, aux, posicao, tamanho);
            tamanho++;
        }
        for (int i = 0; i < k; i++) {
            System.out.println("" + arg[i]);
        }

        entrada.close();
    }
}
