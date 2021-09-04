package mercateo;

import mercateo.exceptions.AppStartException;
import mercateo.service.*;

public class Packaging {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw  new AppStartException("missing file name");
        }
        ItemLoader itemLoader = new ItemLoaderImpl();
        Validation validation = new ValidationImpl();
        PackingApp packingApp = new PackingApp(itemLoader, validation);
        packingApp.runPackingApp(args[0]);
    }

}
