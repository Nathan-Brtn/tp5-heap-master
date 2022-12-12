package tas;

import java.util.Objects;

public class SommetDist implements Comparable<SommetDist>{
    public int i;
    public double d;

    public SommetDist(int i, double d) {
        this.i = i;
        this.d = d;
    }

    public int compareTo(SommetDist s){
        if (d < s.d)
            return -1;
        if( d > s.d)
            return 1;
        return 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SommetDist that = (SommetDist) o;
        return i == that.i; //attention ne surtout pas mettre la distance
    }

    @Override
    public int hashCode() {
        return Objects.hash(i);
    }

}
