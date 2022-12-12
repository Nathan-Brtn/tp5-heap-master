package tas;

import java.util.Random;


public class PlusCourtChemin {

    public static void compare(Graph g, int n) {
        //lance n fois un calcul de plus court chemin entre s_i et t_i tirés au hasard (1 <= i <= n)
        //avec chaque version de Dijkstra, et compare le temps total


        long delta2 = 0;
        long delta3 = 0;
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int s = r.nextInt(g.getN());
            int t = (s + r.nextInt(g.getN() - 1)) % g.getN();

            long start = System.currentTimeMillis();
            g.dijkstraWithPriorityQ(s, t);
            long stop = System.currentTimeMillis();
            delta2 += stop - start;
            start = System.currentTimeMillis();
            g.dijkstraWithoutPriorityQ(s, t);
            stop = System.currentTimeMillis();
            delta3 += stop - start;

        }
        System.out.println("somme temps passé dans " + n + "calculs de plus courts chemins \\");
        System.out.println("avec heap             : " + delta2 + " ms");
        System.out.println("sans heap              : " + delta3 + " ms");
    }




    public static void main(String[] args) {

        System.out.println("début génération graphe ");
        Graph g = new Graph( 4000, true);
        System.out.println("fin génération du graphe");
       compare(g,30);
    }
}
