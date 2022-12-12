package tas;

import svg.*;

import java.math.BigInteger;
import java.util.*;

public class Graph {
    private final ArrayList<ArrayList<Couple<Integer,Double>>> l;

    //l code des listes  d'adjacence : dans l.get(i) il y a tous les voisins du sommet i avec la capacité des arêtes
    //par ex, si on a l'arête i-j de capacité c, alors dans la liste l.get(i) on trouvera le couple (j,c)
    //les capacités sont > 0

    private ArrayList<Couple<Integer,Integer>> coord; //si non null, alors sommet i aux coordonées coord.get(i),
    //qui est un couple (x,y) orienté comme en SVG (0,0) en haut à gauche, x vers la droite, y vers le bas

    ////////////////////////////////////////////////////////////
    // constructeurs et méthodes de base
    ////////////////////////////////////////////////////////////

    public Graph(int n){
        this(n,false);
    }
    public Graph(int n, boolean randomGraphWithCoord){
        //si randomGraphWIthCoord est faux, alors on construit un graphe vide à n sommets
        //si randomGraphWithCoord est vrai, alors on construit un graphe à x<= n sommets où
        // - chacun des x sommets a des coordonnées dans le plan (tirées au hasard), et les coordonnées sont 2 à 2 distinctes
        // - on génère au plus 2x arêtes tirées au hasard, et les poids sur ces arêtes correspondant à la distance euclidienne
        if(!randomGraphWithCoord) {
            l = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                l.add(new ArrayList<>());
            }
        }
        else{
            coord = new ArrayList<>();
            //alors on ajoute des coordonées tirées au hasard pour chaque point, et on créer un graphe planaire
            //dont les sommets sont ces points, avec au plus 2n arêtes tirées au hasard, et des poids sur les arêtes correspondant à la distance euclidienne
            Random r = new Random();
            HashSet<Couple<Integer,Integer>> set = new HashSet<>();
            for(int i = 0;i<n;i++){
                set.add(new Couple<Integer,Integer>(r.nextInt(n),r.nextInt(n)));
            }
            coord = new ArrayList<>(set);

            l = new ArrayList<>();
            for (int i = 0; i < coord.size(); i++) {
                l.add(new ArrayList<>());
            }

            generateE(getN()*2,true);
        }
    }



    public void addEdge(int i, int j, double c){
        //pre : 0 <= i, j < getN() et i!=j et l'arête {i,j} n'existe pas (c'est à dire l.get(i) ne contient pas (j,.) et l.get(j) ne contient pas (i,.))
        //action : ajoute d'arete i,j de poids c
        l.get(i).add(new Couple<>(j,c));
        l.get(j).add(new Couple<>(i,c));
    }


    public ArrayList<Integer> getVoisins(int i){
        ArrayList<Integer> res = new ArrayList<>();
        for(Couple<Integer,Double> c: l.get(i)){
            res.add(c.getElement1());
        }
        return res;
    }
    public void generateE(int m, boolean checkCross){
        //ajoute au plus m arêtes (tirées au hasard) au graphe. Si checkCross, alors le graphe généré est planaire

        ArrayList<Couple<Integer,Integer>> listeAretes = new ArrayList<Couple<Integer, Integer>>();
        for(int i=0;i<getN();i++){
            for(int j=i+1;j<getN();j++){
                listeAretes.add(new Couple<Integer,Integer>(i,j));
            }
        }
        Collections.shuffle(listeAretes);
        ArrayList<Couple<Integer,Integer>> edges = new ArrayList<>();
        int cptEdges = 0;
        Random r = new Random();
        for(Couple<Integer,Integer> edge : listeAretes){

            int i = edge.getElement1();
            int j = edge.getElement2();
            int xi = coord.get(i).getElement1();
            int yi = coord.get(i).getElement2();
            int xj = coord.get(j).getElement1();
            int yj = coord.get(j).getElement2();
            double dij = Math.sqrt((xj - xi) * (xj - xi) + (yj - yi) * (yj - yi));
            //double dij = r.nextInt(getN())+1;
            boolean cross = false;

            if(checkCross){
                int e=0;
                while (!cross && e < edges.size()) {
                    //on verifie si arete {i,j} crée un croisement avec aretes déjà existantes
                    int i2 = edges.get(e).getElement1();
                    int j2 = edges.get(e).getElement2();

                    cross = cross(coord.get(i).getElement1(), coord.get(i).getElement2(), coord.get(j).getElement1(), coord.get(j).getElement2(),
                            coord.get(i2).getElement1(), coord.get(i2).getElement2(), coord.get(j2).getElement1(), coord.get(j2).getElement2());
                    e++;

                }
            }
            if (!cross) {
                edges.add(new Couple<Integer, Integer>(i, j));
                addEdge(i, j, dij);
                cptEdges++;
            }
            if(cptEdges>=m){
                return;
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Graph)) return false;
        Graph graphe = (Graph) o;
        if (graphe.getN()!=getN()) return false;

        boolean ok = true;
        int i = 0;
        while(i < getN() && ok){
            ok = l.get(i).containsAll(graphe.l.get(i)) && graphe.l.get(i).containsAll(l.get(i)) ;
            i++;
        }
        return ok;
    }

    @Override
    public int hashCode() {
        return Objects.hash(l);
    }


    public int getN(){
        return l.size();
    }



    ////////////////////////////////////////////////////////////
    // méthodes pour afficher les graphes (terminal ou svg)
    ////////////////////////////////////////////////////////////



    public String toString(){
        String res = "";
        for(int i = 0;i<l.size();i++){
            res+="l["+i+"] : " + l.get(i)+"\n";
        }

        return res;
    }

    public SVG toSVGWithPath(int s, int t, ArrayList<Integer> path){
        //converti this en svg, avec le s-t chemin "path", ainsi que s et t, marqués en rouge
        double sizePathst = 0.3;
        if((getN()>=100)&&(getN()<=1000)) {
            sizePathst = 1;
        }
        if(getN()>1000)
            sizePathst = 2;
        SVG res = toSVG();
        Style s2 = new Style();
        s2.setFill("red");
        s2.setFillOpacity(1.0);
        s2.setStroke("red");
        s2.setStrokeWidth(sizePathst);

        Circle c = new Circle(coord.get(s).getElement1(),coord.get(s).getElement2(),sizePathst);
        c.setStyle(s2);
        res.add(c);
        c = new Circle(coord.get(t).getElement1(),coord.get(t).getElement2(),sizePathst);
        c.setStyle(s2);
        res.add(c);

        for(int x=1;x<path.size();x++) {
            int i =path.get(x-1);
            int j =path.get(x);
            Line edge = new Line(coord.get(i).getElement1(), coord.get(i).getElement2(), coord.get(j).getElement1(), coord.get(j).getElement2());
            edge.setStyle(s2);
            res.add(edge);
        }

        return res;
    }
    public SVG toSVG(){
        //converti this en svg

        double w =getN();
        double h=getN();
        SVG res = new SVG(w,h);

        Rectangle r = new Rectangle(0,0,w,h);
        Style s = new Style();
        s.setFill("white");
        s.setFillOpacity(1.0);
        r.setStyle(s);
        res.add(r);

        Style s2 = new Style();
        s2.setFill("black");
        s2.setFillOpacity(1.0);
        s2.setStroke("black");
        s2.setStrokeWidth(0.1);

        for(int i=0;i<getN();i++){
            Circle c = new Circle(coord.get(i).getElement1(),coord.get(i).getElement2(),0.2);
            c.setStyle(s2);
            res.add(c);

        }

        for(int i=0;i<getN();i++){
            for(Couple<Integer,Double> vois : l.get(i)){
                int j = vois.getElement1();
                Line edge = new Line(coord.get(i).getElement1(),coord.get(i).getElement2(),coord.get(j).getElement1(),coord.get(j).getElement2());
                edge.setStyle(s2);
                res.add(edge);
            }
        }
        return res;
    }












    public ArrayList<Integer> dijkstraWithPriorityQ(int s, int t) {
        //0 <= s,t < getN()
        //retourne un plus court chemin entre s et t en utilisant une min priority queue pour gérer l'ensemble "avoir".
        //Ici, la priorité d'un sommet v sera sa distance à s.
        //Un même sommet v se retrouvera parfois plusieurs fois dans avoir (avec différentes priorités).
        //En effet :
        // - on peut d'abord insérer (v,10) (au sens v avec la priorité 10) car on découvre v avec une distance de 10 depuis s,
        // - puis plus tard on insère (v,6) car on découvre un nouveau chemin pour aller de s à v de poids 6
        // - l'ensemble "avoir" contient donc deux fois l'élément v, avec 2 priorités différentes.
        // - comme c'est une "min priority queue", on retirera d'abord le couple (v,6), et en le retirant il faudra marquer "v" comme "traité"
        //   en se servant du tableau de booléen ci-dessous
        // - ainsi, lorsque l'on retirera le couple (v,10), on verra que v a déjà été traité, et on ignorera ce couple (v,10)
        //

        double[] dist = new double[getN()]; //dist[i] va contenir la plus petite distance actuellement trouvée pour aller de s à i
        int[] pred = new int[getN()];//pred[i] va contenir le prédécesseur de i dans le plus court chemin actuellement trouvé pour
        //aller de s à i
        boolean[] treated = new boolean[getN()]; //treated[i] vaut vrai ssi v a déjà été traité (traité = retiré du tas)

        for (int i = 0; i < getN(); i++) {
            dist[i] = Double.MAX_VALUE;
            pred[i] = -1;
            treated[i]=false;
        }
        dist[s] = 0;


        //PriorityQ<Integer> avoir = ...;
        throw new RuntimeException("méthode non implémentée");
    }
    public ArrayList<Integer> dijkstraWithoutPriorityQ(int s, int t) {
        //0 <= s,t < getN()
        //retourne un plus court chemin entre s et t en utilisant une arraylist pour gérer les sommets "avoir"

        double[] dist = new double[getN()]; //dist[i] va contenir la plus petite distance actuellement trouvée pour aller de s à i
        int[] pred = new int[getN()];//pred[i] va contenir le prédécesseur de i dans le plus court chemin actuellement trouvé pour
        //aller de s à i

        for (int i = 0; i < getN(); i++) {
            dist[i] = Double.MAX_VALUE;
            pred[i] = -1;
        }
        dist[s] = 0;

        //        ArrayList<Integer> avoir = ..;
        throw new RuntimeException("méthode non implémentée");
    }

    /////////////////////////////////////////////////////
    /// méthodes pour vérifier la planarité d'aretes
    /////////////////////////////////////////////////////

    public static boolean cross(int u1x, int u1y, int u2x, int u2y, int v1x, int v1y, int v2x, int v2y) {
        return bothSides(u1x,u1y,u2x,u2y,v1x,v1y,v2x,v2y) && bothSides(v1x,v1y,v2x,v2y,u1x,u1y,u2x,u2y);
    }

    public static boolean bothSides(int u1x, int u1y, int u2x, int u2y, int v1x, int v1y, int v2x, int v2y){
        //return true ssi segments u, v se croisent sur un point intérieur aux 2 segments

        BigInteger bu1x=BigInteger.valueOf(u1x);
        BigInteger bu1y=BigInteger.valueOf(u1y);
        BigInteger bu2x=BigInteger.valueOf(u2x);
        BigInteger bu2y=BigInteger.valueOf(u2y);
        BigInteger bv1x=BigInteger.valueOf(v1x);
        BigInteger bv1y=BigInteger.valueOf(v1y);
        BigInteger bv2x=BigInteger.valueOf(v2x);
        BigInteger bv2y=BigInteger.valueOf(v2y);

        //delta_u : vecteur de u
        int delta_ux = u2x-u1x;
        int delta_uy = u2y-u1y;
        BigInteger bdelta_ux = bu2x.subtract(bu1x);
        BigInteger bdelta_uy = bu2y.subtract(bu1y);

        //delta_v : vecteur de v
        int delta_vx = v2x-v1x;
        int delta_vy = v2y-v1y;
        BigInteger bdelta_vx = bv2x.subtract(bv1x);
        BigInteger bdelta_vy = bv2y.subtract(bv1y);

        //a : vecteur u1v1
        int delta_ax = v1x-u1x;
        int delta_ay = v1y-u1y;
        BigInteger bdelta_ax = bv1x.subtract(bu1x);
        BigInteger bdelta_ay = bv1y.subtract(bu1y);

        //b : vecteur u1v2
        int delta_bx = v2x-u1x;
        int delta_by = v2y-u1y;
        BigInteger bdelta_bx = bv2x.subtract(bu1x);
        BigInteger bdelta_by = bv2y.subtract(bu1y);

        //n_u : normale de u par rotation 90 à gauche
        int n_ux = -delta_uy;
        int n_uy = delta_ux;
        BigInteger bn_ux = bdelta_uy.negate();
        BigInteger bn_uy = bdelta_ux;

        //s1 : a.nu
        int s1 = delta_ax*n_ux+delta_ay*n_uy;
        //s2 : b.nu
        int s2 = delta_bx*n_ux+delta_by*n_uy;
        BigInteger bs1 = (bdelta_ax.multiply(bn_ux)).add(bdelta_ay.multiply(bn_uy));
        BigInteger bs2 = (bdelta_bx.multiply(bn_ux)).add(bdelta_by.multiply(bn_uy));


        //return (((s1>0)&&(s2<0))||((s1<0)&&(s2>0)));
        return bs1.signum()*bs2.signum()<0;
        //return s1*s2<0;

    }




}
