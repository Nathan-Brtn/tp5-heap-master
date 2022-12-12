package tas;

public class PriorityQ<T> {
    //implémentation d'une priorit queue dans laquelle les doublons (au sens de equals du T) sont autorisés

    Heap<ElemWithPriority<T>> h;

    public PriorityQ(boolean isMaxPQ) {
        h = new Heap<ElemWithPriority<T>>(isMaxPQ);
    }

    public int size(){
        return h.size();
    }

    public void add(T e, double p) {
        //ajoute e avec la priorité p dans le tas h
        throw new RuntimeException("méthode non implémentée");
    }

    public T getTop() {
        //retourne un élément de priorité max (si l'on est une max priority queue) ou min sinon
        throw new RuntimeException("méthode non implémentée");
    }

    public T removeTop() {
        //retourne et retire un élément de priorité max (si l'on est une max priority queue) ou min sinon
        throw new RuntimeException("méthode non implémentée");
    }


}
