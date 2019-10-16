package al132.techemistry.capabilities.heat;

public interface IHeatStorage {

    double getHeatStored();

    double extractHeat(double maxExtract, boolean simulate);

    double receiveHeat(double maxReceive, boolean simulate);

    boolean canExtract();

    boolean canReceive();
}