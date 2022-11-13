package objects;

public class PaymentFrequency {

    private PFreqType type;

    private double charge;

    public PaymentFrequency(int charge, PFreqType units) {
        this.charge = charge;
        this.type = units;
    }

    public PFreqType getPFreqType() {
        return this.type;
    }

    public double getRawCharge() {
        return this.charge;
    }

}
