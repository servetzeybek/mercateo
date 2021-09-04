package mercateo.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Combination implements Comparable<Combination>{
    private Item[] items;

    public Item[] getItems() {
        return items;
    }

    public Combination(Item[] items) {
        this.items = items;
    }

    @Override
    public int compareTo(Combination o) {
        if (getTotalCostOfItems() == o.getTotalCostOfItems()) {
            return compareWeights(o);
        } else if (getTotalCostOfItems() > o.getTotalCostOfItems()){
            return 1;
        } else {
            return -1;
        }
    }

    private int compareWeights(Combination o) {
        if (getTotalWeightOfItems() < o.getTotalWeightOfItems()) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getTotalCostOfItems() {
        return Arrays.stream(items).map(Item::getCostInEuro).collect(Collectors.summingInt(Integer::intValue));
    }

    public float getTotalWeightOfItems() {
        return  Arrays.stream(items).map(item -> (double)item.getWeight()).collect(Collectors.summingDouble(Double::doubleValue)).floatValue();
    }
}
