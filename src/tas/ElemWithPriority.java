package tas;

import java.util.Objects;

public class ElemWithPriority<T> implements Comparable<ElemWithPriority<T>>{
    private final T e;
    public double priority;

    public ElemWithPriority(T e, double priority) {
        this.e = e;
        this.priority = priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public T getElem(){
        return e;
    }

    public double getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElemWithPriority<?> that = (ElemWithPriority<?>) o;
        return e.equals(that.e) && priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(e);
    }


    @Override
    public int compareTo(ElemWithPriority<T> e) {
        return Double.compare(priority, e.priority);
    }
}
