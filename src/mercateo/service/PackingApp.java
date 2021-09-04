package mercateo.service;

import mercateo.domain.MPackage;

import java.util.List;

public class PackingApp {
    private static final int MAX_ALLOWED_PACKAGE_WEIGHT = 100;
    private static final int MAX_ALLOWED_ITEM_COUNT = 15;
    private static final int MAX_ALLOWED_ITEM_WEIGHT = 100;
    private static final int MAX_ALLOWED_ITEM_COST = 100;
    private final ItemLoader itemLoader;
    private final Validation validation;

    public PackingApp(ItemLoader itemLoader, Validation validation) {
        this.itemLoader = itemLoader;
        this.validation = validation;
    }

    public void runPackingApp(String path) {
        List<MPackage> mPackageList = itemLoader.loadFromFile(path);
        validatePackages(mPackageList);
        Packing packing = new PackingImpl(mPackageList);
        packing.packIt();
        packing.OutputPackedItemsToConsole();
    }

    private void validatePackages(List<MPackage> mPackageList) {
        validation.checkIfMaxPackageWeightExceeded(mPackageList, MAX_ALLOWED_PACKAGE_WEIGHT);
        validation.checkIfMaxItemCountExceeded(mPackageList, MAX_ALLOWED_ITEM_COUNT);
        validation.checkIfMaxItemWeightExceeded(mPackageList, MAX_ALLOWED_ITEM_WEIGHT);
        validation.checkIfMaxItemCostExceeded(mPackageList, MAX_ALLOWED_ITEM_COST);
    }
}
