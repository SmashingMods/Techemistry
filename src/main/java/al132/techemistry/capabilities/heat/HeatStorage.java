package al132.techemistry.capabilities.heat;

public class HeatStorage implements IHeatStorage {

    protected double heat;

    public HeatStorage(double heat) {
        this.heat = heat;
    }

    @Override
    public double getHeatStored() {
        return this.heat;
    }

    @Override
    public double extractHeat(double maxExtract, boolean simulate) {
        if (!canExtract()) return 0;
        if (heat >= maxExtract) {
            if (!simulate) {
                heat -= maxExtract;
            }
            return maxExtract;
        } else {
            if (!simulate) heat = 0.0;
            return heat;
        }
    }

    @Override
    public double receiveHeat(double maxReceive, boolean simulate) {
        if (!canReceive()) return 0;
        if (!simulate) heat += maxReceive;
        return maxReceive;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    /*public void setHeat(double newHeat) {
        this.heat = newHeat;
    }

     */
}
