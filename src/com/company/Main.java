package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	//Welcome message
        System.out.println("Welcome the to Blackjack");
    //Creating the deck

        Deck playingdeck=new Deck();
        playingdeck.GenerateFullDeck();
        playingdeck.shuffle();
        playingdeck.shuffle();
        playingdeck.shuffle();
        //Player deck
        Deck playerDeck=new Deck();
        //Dealer deck
        Deck dealerDeck=new Deck();
        double playerMoney=100.00;

        Scanner scanner=new Scanner(System.in);

        //Game loop
        while(playerMoney>0){
            //While player still have money
            //Take player bet

            System.out.println("You have "+playerMoney+"how much do you wanna bet?");
            double playerbet=scanner.nextDouble();
            if(playerbet>playerMoney){
                System.out.println("You cannot bet more than your money you loser");
                break;
            }
            boolean endRound=false;
            //Dealing
            //Player gets two cards
            playerDeck.draw(playingdeck);
            playerDeck.draw(playingdeck);

            //Dealer gets two cards
            dealerDeck.draw(playingdeck);
            dealerDeck.draw(playingdeck);

            while(true){
                System.out.println("Your hand");
                System.out.println(playerDeck.toString());
                System.out.println("Your decks value is "+playerDeck.cardsValue());

                System.out.println("Dealer deck"+dealerDeck.getCard(0).toString()+"and a closed one");

                System.out.println("What is your move gonna be? (1) Draw or (2) Stay");
                int response=scanner.nextInt();
                if(response==1){
                    playerDeck.draw(playingdeck);
                    System.out.println("You drew a:" +playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    //checking if it is > 21;
                    if(playerDeck.cardsValue()>21){
                        System.out.println("You busted. Your cards value is "+playerDeck.cardsValue());
                        playerMoney-=playerbet;
                        endRound=true;
                        break;

                    }
                }
                if(response==2){
                    break;
                }
            }
            //Reveal dealer cards
            System.out.println("Dealer cards: "+ dealerDeck.toString());
            if(dealerDeck.cardsValue()>playerDeck.cardsValue()&&endRound==false){
                System.out.println("I beat you ");
                playerMoney-=playerbet;
                endRound=true;
            }
            //dealer draws at 16, stand at 17
            while(dealerDeck.cardsValue()<17 && endRound==false){
                dealerDeck.draw(playingdeck);
                System.out.println("Dealer draws "+dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            //display total value of dealer
            System.out.println("Dealers Hand's value is "+dealerDeck.cardsValue());

            //Checking if dealer busted

            if(dealerDeck.cardsValue()>21 && endRound==false){
                System.out.println("You win");
                playerMoney+=playerbet;
                endRound=true;
            }
            //Checking if push
            if(dealerDeck.cardsValue()== playerDeck.cardsValue()&&endRound==false){
                System.out.println("Push!");
                endRound=true;
            }
            if(playerDeck.cardsValue()> dealerDeck.cardsValue()&&endRound==false){
                System.out.println("You won");
                playerMoney+=playerbet;
                endRound=true;
            }
            else if(endRound==false){
                System.out.println("You lost the hand");
                playerMoney-=playerbet;
                endRound=true;
            }
            playerDeck.moveAlltoDeck(playingdeck);
            dealerDeck.moveAlltoDeck(playingdeck);
            System.out.println("End of the hand");
        }
        System.out.println("You lost all of your money. Get out!");

    }
}
