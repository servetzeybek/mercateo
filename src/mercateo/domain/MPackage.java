package mercateo.domain;

import java.util.List;

public class MPackage {
    private int maxWeight;
    private List<Item> items;

    public MPackage(int maxWeight, List<Item> items) {
        this.maxWeight = maxWeight;
        this.items = items;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
