package objects;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Subscription {

    private long start, end;

    private boolean cancelled = false;

    private PaymentFrequency frequency;

    private double charge;

    public Subscription(double charge, long start, PaymentFrequency frequency, boolean cancelled) {
        this.charge = charge;
        this.start = start;
        this.frequency = frequency;
        this.cancelled = cancelled;
    }

    private LocalDate longToDate(long l) {
        return Date.from(Instant.ofEpochMilli(l)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private long daysFromStartToNow() {
        return TimeUnit.DAYS.convert((new Date()).toInstant().toEpochMilli() - start, TimeUnit.MILLISECONDS);
    }

    public double getTotalCharge() {
        if(cancelled) {
            Period period = Period.between(longToDate(start), longToDate(end));
            PFreqType pFType = frequency.getPFreqType();
            if(pFType == PFreqType.DAYS) {
                return charge * period.getDays();
            } else if (pFType == PFreqType.WEEKS) {
                return charge * Math.floor(period.getDays() / 7.0);
            } else if (pFType == PFreqType.MONTHS) {
                return charge * period.getMonths();
            } else if (pFType == PFreqType.YEARS) {
                return charge * period.getYears();
            }
        }
        return charge * Math.floor(daysFromStartToNow() / 7.0);
    }

    public void setCharge(long charge) {
        this.charge = charge;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setFrequency(PaymentFrequency frequency) {
        this.frequency = frequency;
    }
}
