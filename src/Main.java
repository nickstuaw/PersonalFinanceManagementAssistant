import objects.PFreqType;
import objects.PaymentFrequency;
import objects.Subscription;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Written by Nick S. G. W. (nsgwick)
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static DecimalFormat df = new DecimalFormat("0.00");

    private static String askString(String q) {
        System.out.println(q + ": ");
        return scanner.nextLine();
    }
    private static double askDouble(String q) {
        System.out.println(q + ": ");
        return scanner.nextDouble();
    }

    public static void main(String[] args) throws ParseException {
        String subscriptionName = "test";//askString("Subscription name");
        String subscriptionFreq = "weeks";//askString("Subscription frequency (days/weeks/months/years)");
        String subscriptionStart = "07/10/2022";// askString("Subscription start (dd-MM-yyyy)");
        double charge = 5;//askDouble("Subscription charge");
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(subscriptionStart);
        Subscription subscription = new Subscription(charge, date.toInstant().toEpochMilli(),
                new PaymentFrequency(1, PFreqType.valueOf(subscriptionFreq.toUpperCase())),
                false);
        System.out.println("Subscription details:");
        System.out.println("Charged until now: £" + df.format(subscription.getTotalCharge()));
    }
}