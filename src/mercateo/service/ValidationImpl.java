package mercateo.service;

import mercateo.domain.MPackage;
import mercateo.exceptions.MaxAllowedItemCostExceedException;
import mercateo.exceptions.MaxAllowedItemCountExceedException;
import mercateo.exceptions.MaxAllowedItemWeightException;
import mercateo.exceptions.MaxAllowedPackageWeightExceedException;

import java.util.List;

public class ValidationImpl implements Validation {

    @Override
    public void checkIfMaxPackageWeightExceeded(List<MPackage> mp, final float maxAllowedPackageWeight) {
        if (mp.parallelStream().anyMatch(mPackage ->
                mPackage.getMaxWeight() > maxAllowedPackageWeight)) {
            throw new MaxAllowedPackageWeightExceedException("maxAllowedPackageWeight can not be greater than " + maxAllowedPackageWeight);
        }
    }

    @Override
    public void checkIfMaxItemCountExceeded(List<MPackage> mp, final int maxAllowedItemCount) {
        if (mp.parallelStream().anyMatch(mPackage -> mPackage.getItems().size() > maxAllowedItemCount)) {
            throw new MaxAllowedItemCountExceedException("maxAllowedItemCount can not be greater than " + maxAllowedItemCount);
        }
    }

    @Override
    public void checkIfMaxItemWeightExceeded(List<MPackage> mp, final float maxAllowedItemWeight) {
        if (mp.parallelStream().flatMap(mPackage -> mPackage.getItems().parallelStream())
                               .anyMatch(item -> item.getWeight() > maxAllowedItemWeight)) {
            throw new MaxAllowedItemWeightException("maxAllowedItemWeight can not be greater than " + maxAllowedItemWeight);
        }
    }

    @Override
    public void checkIfMaxItemCostExceeded(List<MPackage> mp, final int maxAllowedItemCost) {
        if (mp.parallelStream().flatMap(mPackage -> mPackage.getItems().parallelStream())
                .anyMatch(item -> item.getCostInEuro() > maxAllowedItemCost)) {
            throw new MaxAllowedItemCostExceedException("maxAllowedItemCost can not be greater than " + maxAllowedItemCost);
        }
    }
}
