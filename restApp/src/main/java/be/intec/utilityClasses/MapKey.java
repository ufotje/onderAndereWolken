package be.intec.utilityClasses;

//import java.time.LocalDateTime;
import java.time.LocalDateTime;

public class MapKey {
    private int id;
    private LocalDateTime latestEdit;
    private int occurances;

    public MapKey(int id, LocalDateTime latestEdit, int occurances) {
        this.id = id;
        this.latestEdit = latestEdit;
        this.occurances = occurances;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLatestEdit() {
        return latestEdit;
    }

    public void setLatestEdit(LocalDateTime latestEdit) {
        this.latestEdit = latestEdit;
    }

    public int getOccurances() {
        return occurances;
    }

    public void setOccurances(int occurances) {
        this.occurances = occurances;
    }

    public void occurancesIncrement() {
        this.occurances++;
    }

    @Override
    public String toString() {
        return "MapKey{" +
                "id=" + id +
                ", latestEdit=" + latestEdit +
                ", occurances=" + occurances +
                '}';
    }
}
