# MagicPrediction
Java project that simulates a playing cards trick

    FIRST CARD
        Given a deck of playing cards, after shuffling it, a random card is extracted.

    6 PARTITIONS    
        After you see the card, keep in mind a few rules:
                Aces have the value of 1
                Jacks have the value of 11
                Queens have the value of 12
                Kings have the value of 13
                The rest of them have the value shown on the card
                
        Depending on the value, from the bottom of the deck (cards being upside down), 13 - value cards are extracted.
        After that, the very next card is shown, and begins another partition of cards. The process repeats until there
        are 6 partitions of cards.
        
    3 TURNED PARTITIONS
        The partitions are no more upside down and the magician can see them. However, for the trick to work, there are
        further steps to take. 3 more randomly selected partitions are, again turned upside down. The ones that haven't
        been turned are taken back into the deck.
        
    2 EXTRACTIONS (AND ONE FOR THE TRICK TO WORK)
        From the 3 left outside the deck partitions, the magician will select randomly 2 of which he/she can see the first
        card. After seeing both cards, their values are remembered.
        From the deck, there will be extracted 10 cards.
        Afterwards, there will be extracted an equal number with the value of the first card shown.
        Then, the same extraction takes place for the value of the second card shown.
        
    PREDICTION TIME
        The trick should predict how many cards are left in the deck. Counting the remaining cards will give the same
        number as the value of the card from the last upside down turned partition.
