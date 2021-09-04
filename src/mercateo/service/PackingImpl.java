package mercateo.service;

import mercateo.domain.Combination;
import mercateo.domain.Item;
import mercateo.domain.MPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PackingImpl implements Packing {

    private final List<MPackage> itemsTobePacked;
    private List<List<Item>> itemsPacked;


    public PackingImpl(List<MPackage> itemsTobePacked) {
        this.itemsTobePacked = itemsTobePacked;
    }

    @Override
    public void packIt() {
        if (itemsTobePacked.isEmpty()) {
            System.out.println("nothing to pack");
            return;
        }
        itemsPacked = new ArrayList<>();
        itemsTobePacked.forEach(this::packSingleLine);

    }

    private void packSingleLine(MPackage mp) {
        List<Combination> combinations = generatePossibleCombinations(mp.getItems());
        combinations.addAll(makeCombinationFromSingleElements(mp.getItems()));
        List<Combination> combinationsFilteredTooHeavy = filterTooHeavyCombinations(mp, combinations);
        sortCombinationsByCostAndWeight(combinationsFilteredTooHeavy);
        List<Item> highestCostCombinationItems = extractHighestCostCombinationItems(combinationsFilteredTooHeavy);
        itemsPacked.add(highestCostCombinationItems);
    }

    /*
    every item is also a combination on its own

     */
    private List<Combination> makeCombinationFromSingleElements(List<Item> items) {
        return items.parallelStream().map(item -> new Combination(new Item[] {item})).collect(Collectors.toList());
    }

    private void sortCombinationsByCostAndWeight(List<Combination> combinationsFilteredTooHeavy) {
        combinationsFilteredTooHeavy.sort(Collections.reverseOrder());
    }

    private List<Item> extractHighestCostCombinationItems(List<Combination> combinationsFilteredTooHeavy) {

        if (!combinationsFilteredTooHeavy.isEmpty()) {
            return getHighestCostCombination(combinationsFilteredTooHeavy);
        } else {
            return Collections.emptyList();
        }
    }

    private List<Item> getHighestCostCombination(List<Combination> combinations) {
        return new ArrayList<>(Arrays.asList(combinations.get(0).getItems()));
    }

    private List<Combination> filterTooHeavyCombinations(MPackage mp, List<Combination> combinations) {
        final float maxWeight = mp.getMaxWeight();
        return combinations.parallelStream().filter(combination ->
                combination.getTotalWeightOfItems() < maxWeight).collect(Collectors.toList());
    }

    @Override
    public void OutputPackedItemsToConsole() {
        if (itemsPacked.isEmpty()) {
            System.out.println("nothing packed");
            return;
        }
        itemsPacked.forEach(items -> {
            StringBuilder strOut = new StringBuilder();

            for (Item item: items) {
                strOut.append(strOut.length() > 0 ? "," : "").append(item.getIndx());
            }
            if (strOut.length() == 0) {
                strOut.append("-");
            }
            System.out.println(strOut);
        });
    }
}
