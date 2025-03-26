package edu.canisius.csc213.project1;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class FileWrite extends UniqueHands{
    public static void main(String[] args) {
        String filePath = "unique_hands_result.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Deck Size,Hand Size,Trial,Attempts,Time (sec)");
            int[] deckSizes = {24, 28}; // Deck sizes to test
            int[] handSizes = {6, 7}; // Hand sizes to test
            int trials = 5; // Number of trials per deck-hand combination
            int attempts = 0;
            long duration = 0;
            long startTime;
            long endTime;

            for (int d : deckSizes){
                for (int h : handSizes){
                    for (int i=0; i < trials; ++i){
                        startTime = System.nanoTime();
                        attempts = countAttemptsToSeeAllHands(d, h);
                        endTime = System.nanoTime();
                        duration = endTime- startTime;
                        System.out.print("Deck Size: "+d+" | "+" Hand Size: "+h+" | Trial "+i);
                        writer.newLine();
                        writer.write(""+d+","+h+","+(i+1)+","+attempts+","+duration);
                        System.out.println(" | Attempts: "+attempts+" | Time: "+duration);
                        System.out.println();
                    }
                }
            }

            writer.newLine();
            System.out.println("CSV file created successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
