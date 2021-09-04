package mercateo.service;

import mercateo.domain.MPackage;

import java.util.List;

public interface Validation {
    void checkIfMaxPackageWeightExceeded(List<MPackage> mp, final float maxAllowedPackageWeight);

    void checkIfMaxItemCountExceeded(List<MPackage> mp, final int maxAllowedItemCount);

    void checkIfMaxItemWeightExceeded(List<MPackage> mp, final float maxAllowedItemWeight);

    void checkIfMaxItemCostExceeded(List<MPackage> mp, final int maxAllowedItemCost);
}
