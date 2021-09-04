package mercateo.service;

import mercateo.domain.Item;
import mercateo.domain.MPackage;
import mercateo.exceptions.AppStartException;
import mercateo.exceptions.ItemsFileSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ItemLoaderImpl implements ItemLoader {

    @Override
    public List<MPackage> loadFromFile(String path)  {
        List<MPackage> mPackages = new ArrayList<>();
        Path p = Paths.get(path);
        try (BufferedReader br = Files.newBufferedReader(p)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    mPackages.add(convertLineToPackage(line));
                }

            }
        } catch (IOException e) {
            throw new AppStartException("cannot read file " + path);
        }
        return mPackages;
    }

    private  MPackage convertLineToPackage(String line) throws ItemsFileSyntaxException {

        StringTokenizer st = new StringTokenizer(line, " ");

        Integer maxWeight = extractMaxWeight(st, line);

        List<Item> itemList = extractItems(st, line);

        return new MPackage(maxWeight, itemList);
    }

    private List<Item> extractItems(StringTokenizer st, String line) {
        List<Item> items = new ArrayList<>();
        if (!st.hasMoreElements()) {
            throw new ItemsFileSyntaxException("no item given on " + line);
        }
        st.nextToken(); // passing ":"
        while (st.hasMoreElements()) {
            Item item = constructItem(st.nextToken().trim(), line);
            items.add(item);
        }
        return items;
    }

    private Item constructItem(String itemStr, String line) {
        itemStr = throwAwayParenthesis(itemStr);
        StringTokenizer st = new StringTokenizer(itemStr, ",");
        if (!st.hasMoreElements()) {
            throw new ItemsFileSyntaxException("no item defined  on " + line);
        }
        int indxItem = extractIndx(st.nextToken(), line);
        float weightItem = extractWeight(st.nextToken(), line);
        int costItem = extractCost(st.nextToken(), line);
        return new Item(indxItem, weightItem, costItem);
    }

    private String throwAwayParenthesis(String itemStr) {
        if (!itemStr.startsWith("(") || !itemStr.endsWith(")")) {
            throw new ItemsFileSyntaxException("missing parenthesis in   " + itemStr);
        }
        return itemStr.substring(1, itemStr.length() - 1);
    }

    private int extractCost(String nextToken, String line) {
        if (nextToken.length() == 0) {
            throw new ItemsFileSyntaxException("no item cost given on  " + line);
        }
        nextToken = nextToken.substring(1); // omit euro sign
        try {
            return Integer.parseInt(nextToken);
        } catch(NumberFormatException e) {
            throw new ItemsFileSyntaxException("item cost cannot be parsed on  " + line);
        }
    }

    private float extractWeight(String nextToken, String line) {
        if (nextToken.length() == 0) {
            throw new ItemsFileSyntaxException("no item weight given on  " + line);
        }
        try {
            return Float.parseFloat(nextToken);
        } catch(NumberFormatException e) {
            throw new ItemsFileSyntaxException("item weight cannot be parsed on  " + line);
        }
    }

    private int extractIndx(String nextToken, String line) {
        if (nextToken.length() == 0) {
            throw new ItemsFileSyntaxException("no item index given on  " + line);
        }
        try {
           return Integer.parseInt(nextToken);
        } catch(NumberFormatException e) {
            throw new ItemsFileSyntaxException("item index cannot be parsed on  " + line);
        }
    }

    private Integer extractMaxWeight(StringTokenizer st, String line)  {

        String maxWeightStr;
        if (st.hasMoreElements()) {
            maxWeightStr = st.nextToken();
        } else {
            throw new ItemsFileSyntaxException("first colon does not exists in " + line);
        }
        try {
            return Integer.parseInt(maxWeightStr);
        } catch(NumberFormatException e) {
            throw new ItemsFileSyntaxException("max weight not numeric" + line, e);
        }
    }
}
