import java.util.*;

class Main {
    public static String start(int v[], int a, int b) {
        String str;
        if(v.length == 1){
            if(v[0] > 0){
                str = "Maior sequência ganhadora é " + v[0] + ".";
            }
            else{
                str = "Sequência perdedora.";
            }
            return str;
        }
        for (int i = 0; i < v.length; i++) {
            if (a == -1 && v[i] > 0) {
                a = v[i];
                b = a;
                i++;
            }
            b = b + (v[i]);
            if (b > a) {
                a = b;
            }
            if (b < 0) {
                b = 0;
            }
        }
        if (a <= 0) {
             str = "Sequência perdedora.";
        } else {
             str = "Maior sequência ganhadora é " + a + ".";
        }
        return str;
    }

    public static void main(String args[]) {
        int nIn;
        Scanner entrada = new Scanner(System.in);
        nIn = entrada.nextInt();
        String[] strIn = new String[1000];
        int j=0;
        while (nIn != 0){
             int[] vIn = new int[nIn];
            for(int i = 0 ; i < nIn ; i++){
                vIn[i] = entrada.nextInt();
            }
               strIn[j] = start(vIn, -1, 0);
               j++;
               nIn = entrada.nextInt();
        }
           for(int aux = 0 ; aux < j ; aux++){
               System.out.println("" + strIn[aux]);
           } 
            entrada.close();
        }

}