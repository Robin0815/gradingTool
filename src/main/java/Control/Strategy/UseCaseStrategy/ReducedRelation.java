package Control.Strategy.UseCaseStrategy;

import Model.Elements;

public class ReducedRelation {
    private Elements id;
    private Elements start;
    private Elements end;

    public Elements getId() {
        return id;
    }

    public void setId(Elements id) {
        this.id = id;
    }

    public Elements getStart() {
        return start;
    }

    public void setStart(Elements start) {
        this.start = start;
    }

    public Elements getEnd() {
        return end;
    }

    public void setEnd(Elements end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "ReducedRelation{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReducedRelation other = (ReducedRelation) obj;
        if (id != other.id || start != other.start || end != other.end) {
            return false;
        }
        return true;
    }
}
