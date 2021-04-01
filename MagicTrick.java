import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MagicTrick {
    private static final int NUMBEROFCARDS = 52;
    private static final int ACEVALUE = 1;
    private static final int TENVALUE = 10;
    private static final int JACKVALUE = 11;
    private static final int QUEENVALUE = 12;
    private static final int KINGVALUE = 13;
    private static final String[] cards = {"AC","2C","3C","4C","5C","6C","7C","8C","9C","TC","JC","QC","KC",
                                            "AD","2D","3D","4D","5D","6D","7D","8D","9D","TD","JD","QD","KD",
                                            "AH","2H","3H","4H","5H","6H","7H","8H","9H","TH","JH","QH","KH",
                                            "AS","2S","3S","4S","5S","6S","7S","8S","9S","TS","JS","QS","KS"};
    private static final int NUMBEROFPARTITIONS = 6;
    private static final int TURNEDPARTITIONS = 3;
    private static final int TURNEDFROMTURNED = 2;
    public static void main(String[] args) {

        List<String> defaultDeck = new ArrayList<>(Arrays.asList(cards).subList(0, NUMBEROFCARDS));

        /** Shuffle the deck */
        List<String> shuffledDeck = new ArrayList<>();
        while (defaultDeck.size() > 0) {
            int index = (int) (Math.random() * defaultDeck.size());
            shuffledDeck.add(defaultDeck.remove(index));
        }

        List<List<String>> partitions = new ArrayList<>();

        /** Choose a random card for the first of the 6 partitions */
        Random chosenCard = new Random();
        final int chosenCardPosition = chosenCard.nextInt(shuffledDeck.size());
        System.out.println(shuffledDeck.get(chosenCardPosition));
        String removedCard = shuffledDeck.get(chosenCardPosition);
        shuffledDeck.remove(chosenCardPosition);

        /** Get the first (13 - firstCardChosenValue) cards */
        int decreaser = 0;
        if (removedCard.charAt(0) == 'A') {
            decreaser = decreaser + ACEVALUE;
        } else if (removedCard.charAt(0) == 'T') {
            decreaser = decreaser + TENVALUE;
        } else if (removedCard.charAt(0) == 'J') {
            decreaser = decreaser + JACKVALUE;
        } else if (removedCard.charAt(0) == 'Q') {
            decreaser = decreaser + QUEENVALUE;
        } else if (removedCard.charAt(0) == 'K') {
            decreaser = decreaser + KINGVALUE;
        } else {
            decreaser = decreaser + Integer.parseInt(removedCard.substring(0, 1));
        }

        List<String> partition = new ArrayList<>();
        partition.add(removedCard);
        int partitionDimension = 1;

        for (int j = 0; j < KINGVALUE - decreaser; j++) {
            partition.add(shuffledDeck.get(shuffledDeck.size() - j - 1));
            partitionDimension++;
        }

        partitions.add(partition);
        System.out.println("Partition 1: " + partition);
        System.out.println(shuffledDeck.get(shuffledDeck.size() - partitionDimension));

        /** Repeat the process for 5 more partitions */
        for (int i = 0; i < NUMBEROFPARTITIONS - 1; i++) {
            int currentPartitionDimension = 0;
            List<String> partition1 = new ArrayList<>();
            currentPartitionDimension += partitionDimension;
            decreaser = 0;
            if (shuffledDeck.get(shuffledDeck.size() - currentPartitionDimension).charAt(0) == 'A') {
                decreaser = decreaser + ACEVALUE;
            } else if (shuffledDeck.get(shuffledDeck.size() - currentPartitionDimension).charAt(0) == 'T') {
                decreaser = decreaser + TENVALUE;
            } else if (shuffledDeck.get(shuffledDeck.size() - currentPartitionDimension).charAt(0) == 'J') {
                decreaser = decreaser + JACKVALUE;
            } else if (shuffledDeck.get(shuffledDeck.size() - currentPartitionDimension).charAt(0) == 'Q') {
                decreaser = decreaser + QUEENVALUE;
            } else if (shuffledDeck.get(shuffledDeck.size() - currentPartitionDimension).charAt(0) == 'K') {
                decreaser = decreaser + KINGVALUE;
            } else {
                decreaser = decreaser + Integer.parseInt(shuffledDeck.get(shuffledDeck.size() - currentPartitionDimension).substring(0, 1));
            }

            partition1.add(shuffledDeck.get(shuffledDeck.size() - currentPartitionDimension));
            for (int j = 0; j < KINGVALUE - decreaser; j++) {
                partition1.add(shuffledDeck.get(shuffledDeck.size() - currentPartitionDimension - j - 1));
                partitionDimension++;
            }
            partitions.add(partition1);
            System.out.println("Partition " + (i + 2) + ": " + partition1);
            partitionDimension++;
            if (i != NUMBEROFPARTITIONS - 2) {
                System.out.println(shuffledDeck.get(shuffledDeck.size() - partitionDimension));
            }
        }

        List<List<String>> remainingCards = new ArrayList<>();

        /** Choose 3 partitions to turn upside down and take the rest back into the deck */
        int mergedDeckSize = 0;
        for (int i = 0; i < TURNEDPARTITIONS; i++) {
            int partitionIndex = (int) (Math.random() * partitions.size());
            mergedDeckSize += partitions.get(partitionIndex).size();
            remainingCards.add(partitions.remove(partitionIndex));
        }

        List<String> remainingCardsInTheDeck = new ArrayList<>();
        for (int i = 0; i < NUMBEROFCARDS - partitionDimension; i++) {
            remainingCardsInTheDeck.add(shuffledDeck.get(i));
        }
        System.out.println("Remaining unturned partitions: " + remainingCards);
        remainingCards.add(remainingCardsInTheDeck);
        mergedDeckSize += remainingCardsInTheDeck.size();
        List<List<String>> remainingPartitions = new ArrayList<>();
        while (partitions.size() > 0) {
            remainingPartitions.add(partitions.remove(0));
        }
        System.out.println("Cards left in the deck before the last 3 extractions: " + mergedDeckSize);

        /** Choose 2 partitions from which you will show only the first card */
        List<List<String>> remainingTurnedPartitions = new ArrayList<>();
        for (int i = 0; i < TURNEDFROMTURNED; i++) {
            int finalTurnIndex = (int) (Math.random() * remainingPartitions.size());
            remainingTurnedPartitions.add(remainingPartitions.remove(finalTurnIndex));
        }

        /** Extract 10 cards from the deck, then as many as indicated from the values shown by the 2 cards
         * turned from the 3 partitions that are not in the deck */
        int value0 = 10;
        int value1 = 0;
        System.out.println("The first card of the first partition that was turned upside down is: " +
                remainingTurnedPartitions.get(0).get(0));
        int value2 = 0;
        System.out.println("The first card of the second partition that was turned upside down is: " +
                remainingTurnedPartitions.get(1).get(0));
        for (int i = 0; i < TURNEDFROMTURNED; i++) {
            if (i == 0) {
                if (remainingTurnedPartitions.get(i).get(0).charAt(0) == 'A') {
                    value1 = value1 + ACEVALUE;
                } else if (remainingTurnedPartitions.get(i).get(0).charAt(0) == 'T') {
                    value1 = value1 + TENVALUE;
                } else if (remainingTurnedPartitions.get(i).get(0).charAt(0) == 'J') {
                    value1 = value1 + JACKVALUE;
                } else if (remainingTurnedPartitions.get(i).get(0).charAt(0) == 'Q') {
                    value1 = value1 + QUEENVALUE;
                } else if (remainingTurnedPartitions.get(i).get(0).charAt(0) == 'K') {
                    value1 = value1 + KINGVALUE;
                } else {
                    value1 = value1 + Integer.parseInt(remainingTurnedPartitions.get(i).get(0).substring(0,1));
                }
            }

            if (i == 1) {
                if (remainingTurnedPartitions.get(i).get(0).charAt(0) == 'A') {
                    value2 = value2 + ACEVALUE;
                } else if (remainingTurnedPartitions.get(i).get(0).charAt(0) == 'T') {
                    value2 = value2 + TENVALUE;
                } else if (remainingTurnedPartitions.get(i).get(0).charAt(0) == 'J') {
                    value2 = value2 + JACKVALUE;
                } else if (remainingTurnedPartitions.get(i).get(0).charAt(0) == 'Q') {
                    value2 = value2 + QUEENVALUE;
                } else if (remainingTurnedPartitions.get(i).get(0).charAt(0) == 'K') {
                    value2 = value2 + KINGVALUE;
                } else {
                    value2 = value2 + Integer.parseInt(remainingTurnedPartitions.get(i).get(0).substring(0,1));
                }
            }
        }

        /** Prediction time */
        mergedDeckSize = mergedDeckSize - value0 - value1 - value2;
        System.out.println("Cards left in the deck after the last 3 extractions: " + mergedDeckSize);
        System.out.println("The value of the card from the last partition should predict how many cards are left in the deck.");
        System.out.println("The first card in the last partition that was turned upside down is: " +
                remainingPartitions.get(0).get(0));
    }
}
