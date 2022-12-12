package tas;

import java.util.ArrayList;
import java.util.Collections;

public class Heap<T extends Comparable<T>> {


    private ArrayList<T> t;
    private final boolean isMaxHeap;
    /*
    si isMaxHeap, alors c'est un max heap, au sens où si e1 ancêtre de e2 dans l'arbre, alors e1 >= e2
    (avec e1 >= e2 notation pour e1.compareTo(e2) >= 0)
    sinon, c'est un min heap, et si e1 ancêtre de e2 dans l'arbre, alors e1 <= e2

    Pour unifier max/min heap dans le code, on utilisera isSup (ci dessous), et on assurera dans deux cas que
    si e1 ancêtre de e2, alors e1.isSup(e2)*/


    public Heap(boolean isMaxHeap) {
        t = new ArrayList<>();
        this.isMaxHeap = isMaxHeap;
    }

    public Heap(boolean isMaxHeap, ArrayList<T> tt) {
        this(isMaxHeap);
        t.addAll(tt);
        for(int i=tt.size()-1;i>=0;i--){
            heapifyDown(i);
        }
    }


    //////////////////////////////////////////////
    ///// méthodes basiques
    //////////////////////////////////////////////

    public void print(int i) {
        //0  <= i
        //affiche les éléments de A_i, ne sert pas, juste montrée en cours
        //(donc si i >= size, A_i vide et n'affiche rien)
        if(i<size()){
            System.out.println(t.get(i)+ " ");
            print(left(i));
            print(right(i));
        }
    }


    public String toString() {
        return t.toString() + "\n";
    }

    public int size() {
        return t.size();
    }

    public int height() {
        return (int) Math.ceil(Math.log(size() + 1));
    } //autant le faire en O(1) plutôt qu'en récursif!

    private void swap(int i, int j) {
        // prérequis 0 <= i,j < t.size()
        // action échange t[i] et t[j], et met à jour hashMap si elle existe
        Collections.swap(t,i,j);
    }

    public boolean isSup(T e1, T e2) {
        return isMaxHeap ? e1.compareTo(e2) >= 0 : e2.compareTo(e1) >= 0;
    }

    private int left(int i) {
        return 2*i+1;
    }

    private int right(int i) {
        return 2*i+2;
    }

    private int father(int i) {
        return (i-1)/2;
    }


    //////////////////////////////////////////////
    ///// méthodes heapify* utiles à add et remove
    //////////////////////////////////////////////

    //les deux méthodes heapify* devraient être en private, elles ne sont en public que pour les tests
    public void heapifyUp(int i) {
        /*prérequis :
            - 0 <= i < t.size()
            - this est un tas, à ceci près que si il y a des relations d'ordre non respectées dans this, alors elles sont entre i et ses ascendants
          action :échange les éléments de this (en faisant "remonter l'élément en i") pour en refaire un tas en au plus height() échanges
            rmq : on conseille de le faire en récursif
        */
        if(i>0) {
            if (isSup(t.get(i), t.get(father(i)))) {
                swap(i, father(i));
                heapifyUp(father(i));
            }
        }
    }

    public void heapifyDown(int i) {


        if(left(i)<size()){
            int max=-1;
            if(right(i)>=size()){
                max=left(i);
            }
            else {
                max=isSup(t.get(left(i)),t.get(right(i)))?left(i):right(i);
            }
            if(!isSup(t.get(i),t.get(max))){
                swap(i,max);
                heapifyDown(max);
            }
        }
    }


    //////////////////////////////////////////////
    ///// méthodes principales (add, remove ..)
    //////////////////////////////////////////////

    public T getTop() {
        return t.get(0);
    }

    public T removeTop() {
        // supprime l'élément d'indice 0 (et le retourne), tout en maintenant la propriété que this est un tas
        T res = getTop();
        swap(0,size()-1);
        t.remove(size()-1);
        heapifyDown(0);
        return res;

    }

    public T remove(int i) {
        //prérequis 0 <= i < size()
        //supprime le ième élément (et le retourne), tout en maintenant la propriété que this est un tas

        T res = getTop();
        swap(0,size()-i);
        t.remove(size()-i);
        heapifyDown(i);
        return res;
    }


    public void add(T e) {
        //pas de prérequis
        //ajoute e à this, en maintenant la propriété que this est un tas

       t.add(e);
       heapifyUp(t.size()-1);
    }


    //////////////////////////////////////////////
    ///// méthodes pour le tri par tas
    //////////////////////////////////////////////

    public ArrayList<T> toSortedArray() {
        //action : retourne un tableau trié (au sens où l'ArrayList retournée res vérifie res.get(i).isSup(res.get(i+1)).
        // Cette méthode peut modifier this.

        ArrayList<T> res = new ArrayList<>();
        while(size()>0){
            res.add(removeTop());
        }

        return res;
    }
    //////////////////////////////////////////////
    ///// méthodes utiles seulement pour les tests (vous ne devez pas les utiliser)
    //////////////////////////////////////////////

    public ArrayList<T> getT() {
        return t;
    }

    public void setT(ArrayList<T> t) {
        this.t = t;
    }

}
