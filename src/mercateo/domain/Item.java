package mercateo.domain;

public class Item {
    private int indx;
    private float weight;
    private int costInEuro;

    public Item(int indx, float weight, int costInEuro) {
        this.indx = indx;
        this.weight = weight;
        this.costInEuro = costInEuro;
    }

    public int getIndx() {
        return indx;
    }

    public void setIndx(int indx) {
        this.indx = indx;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getCostInEuro() {
        return costInEuro;
    }

    public void setCostInEuro(int costInEuro) {
        this.costInEuro = costInEuro;
    }
}
