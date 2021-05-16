package cargo;

public class CargoData {
    public CargoData(int cargoWeight, int baggageWeight, int totalWeight) {
        this.cargoWeight = cargoWeight;
        this.baggageWeight = baggageWeight;
        this.totalWeight = totalWeight;
    }

    @Override
    public String toString() {
        return "CargoData{" +
                "cargoWeight=" + cargoWeight + "kg" +
                ", baggageWeight=" + baggageWeight + "kg" +
                ", totalWeight=" + totalWeight + "kg" +
                '}';
    }

    public int cargoWeight;
    public int baggageWeight;
    public int totalWeight;
}
