package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards =new ArrayList<Card>();
    }

    @Override
    public String toString() {
        String output="";
        int i=0;
        for(Card aCard : this.cards){
            output+="\n"+i+"-"+aCard.toString();
            i++;
        }
        return output;
    }
    public void shuffle(){
        //Generating a temp deck
        ArrayList<Card> tmpDeck=new ArrayList<Card>();
        //Random class
        Random random=new Random();
        int randominx=0;
        int originalsize=this.cards.size();
        for(int i=0;i<originalsize;i++){
            //Generate random numbers rand.NextInt(max-min)+1+min
            randominx=random.nextInt((this.cards.size()-1-0)+1)+0;
            tmpDeck.add(this.cards.get(randominx));
            this.cards.remove(randominx);
        }
        this.cards=tmpDeck;
    }
    public void GenerateFullDeck(){
        //Generating cards
        for(Suit cardSuit: Suit.values()){
            for(Value cardValue : Value.values()){
                this.cards.add(new Card(cardSuit,cardValue));
            }
        }

    }
    public void removeCard(int i){
        cards.remove(i);
    }
    public Card getCard(int i){
        return this.cards.get(i);
    }
    public void addCard(Card addcard){
        this.cards.add(addcard);
    }
    public void draw(Deck comingdeck){
        this.cards.add(comingdeck.getCard(0));
        comingdeck.removeCard(0);
    }
    public void moveAlltoDeck(Deck moveTo){
        int thisDeckSize=this.cards.size();
        //Moving all cards to the deck
        for(int i=0;i<thisDeckSize;i++){
            moveTo.addCard(this.getCard(i));
        }
        for(int i=0;i<thisDeckSize;i++){
            this.removeCard(0);
        }
    }
    public  int deckSize(){
        return this.cards.size();
    }
    //return cards value
    public int cardsValue(){
    int total=0;
    int acecount=0;

    for(Card aCard: this.cards){
        switch (aCard.getValue()){
            case TWO: total+=2; break;
            case THREE: total+=3; break;
            case FOUR : total+=4; break;
            case FIVE: total+=5; break;
            case SIX: total+=6; break;
            case SEVEN: total+=7; break;
            case EIGHT: total+=8; break;
            case NINE: total+=9; break;
            case TEN: total+=10; break;
            case KING: total+=10; break;
            case JACK: total+=10; break;
            case QUEEN: total+=10; break;
            case ACE    :acecount+=1; break;
        }
        for(int i=0;i<acecount;i++){
            if(total>10){
                total+=1;
            }
            else{
                total+=11;
            }
        }
    }
    return total;
    }
}
