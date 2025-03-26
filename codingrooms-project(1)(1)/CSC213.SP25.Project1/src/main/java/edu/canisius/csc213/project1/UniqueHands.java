package edu.canisius.csc213.project1;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * UniqueHands class to analyze how long it takes to see every possible hand 
 * for different deck sizes and hand sizes.
 */
public class UniqueHands {
    public static void main(String[] args) {
        int[] deckSizes = {24, 28}; // Deck sizes to test
        int[] handSizes = {6, 7}; // Hand sizes to test
        int trials = 5; // Number of trials per deck-hand combination
        int attempts = 0;
        long duration = 0;
        long startTime;
        long endTime;

        System.out.println("🃏 Deck Simulation: How long to see every possible hand?");
        System.out.println("------------------------------------------------------");

        for (int d : deckSizes){
            for (int h : handSizes){
                for (int i=0; i < trials; ++i){
                    startTime = System.nanoTime();
                    attempts = countAttemptsToSeeAllHands(d, h);
                    endTime = System.nanoTime();
                    duration = endTime- startTime;
                    double duration2 = (double) duration / 10000000;
                    duration2 = duration2 / 100;
                    String durationF = String.format("%.2f", duration2);
                    System.out.print("Deck Size: "+d+" | "+" Hand Size: "+h+" | Trial "+i);
                    System.out.println(" | Attempts: "+attempts+" | Time: "+durationF);
                    System.out.println();
                }
            }
        }

    }

    public static int countAttemptsToSeeAllHands(int d, int h) {
        Deck deck = new Deck(d);

        Set<List<Card>> uniqueHands = new HashSet<>();

        int attempts = 0;
        double coverage;

        int totalUniqueHands = calculateTotalUniqueHands(d, h);

        while (uniqueHands.size() != totalUniqueHands){
            attempts++;

            deck.shuffle();

            List<Card> hand = new ArrayList<Card>(h);

            for (int i = 0; i <= h; ++i){
                Card card = deck.draw();
                hand.add(card);
                deck.add(card);
            }

            uniqueHands.add(hand);

            coverage = ((double) uniqueHands.size() / (double) totalUniqueHands) * 100;

            if (attempts % 10000 == 0){
                System.out.print("Progress: ");
                System.out.printf("%.2f", coverage);
                System.out.print("% coverage after "+ attempts + " attempts");
                System.out.println(" (Unique Hands: " + uniqueHands.size() + " / " + totalUniqueHands + " | Needed: " + (totalUniqueHands - uniqueHands.size()) + ")");
            }
        }

        return attempts;
    }

    public static int calculateTotalUniqueHands(int i, int j) {
        if (j > i){
            return 0;
        }
        if ((j == 0) || (j == i)){
            return 1;
        }
        int result = 1;
        for (int k = 1; k <= j; ++k){
            result = result * (i - (j - k)) / k;
        }
        return result;
    }

}