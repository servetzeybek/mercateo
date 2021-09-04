package mercateo.service;

import mercateo.domain.Combination;
import mercateo.domain.Item;

import java.util.ArrayList;
import java.util.List;

public interface Packing {

    void packIt();

    void OutputPackedItemsToConsole();

    /*
    ive got this from google, generating combinations is not easy, somebody's already done it,  so..
     */
    default void helper(List<Combination> combinations, Item[] data, int start, int end, int index, List<Item> itemsTobePacked) {
        if (index == data.length) {
            Item[] combination = data.clone();
            combinations.add(new Combination(combination));
        } else if (start <= end) {
            data[index] = itemsTobePacked.get(start);
            helper(combinations, data, start + 1, end, index + 1, itemsTobePacked);
            helper(combinations, data, start + 1, end, index, itemsTobePacked);
        }
    }
    /*
    0,1,2,3,4,5 ...
    combinations
    0-1,0-2,0-3,0-4 ...
    0-1-2,0-1-3,0-1-4 ...
    ....
     */

    default List<Combination> generatePossibleCombinations(List<Item> itemsTobePacked) {
        List<Combination> allCombinations = new ArrayList<>();
        for (int i = 2; i <= itemsTobePacked.size(); i++) {
            List<Combination> singleCombinations = new ArrayList<>();
            helper(singleCombinations, new Item[i], 0, itemsTobePacked.size() - 1, 0, itemsTobePacked);
            allCombinations.addAll(singleCombinations);
        }
        return allCombinations;
    }
}
