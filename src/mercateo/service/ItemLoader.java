package mercateo.service;
import mercateo.domain.MPackage;
import java.util.List;

public interface ItemLoader {
    List<MPackage> loadFromFile(String path);


}
