package com.example;

/**
 * Created by dev on 3/16/16.
 */
public class Joke {

    private String jokeID;
    private String jokeQuestion;
    private String jokeAnswer;

    public Joke(){

    }

    public Joke(String jokeID) {
        this.jokeID = jokeID;
    }

    public Joke(String jokeID, String jokeQuestion, String jokeAnswer) {
        this.jokeID = jokeID;
        this.jokeQuestion = jokeQuestion;
        this.jokeAnswer = jokeAnswer;
    }



    public String getJokeID() {
        return jokeID;
    }

    public void setJokeID(String jokeID) {
        this.jokeID = jokeID;
    }

    public String getJokeQuestion() {
        return jokeQuestion;
    }

    public void setJokeQuestion(String jokeQuestion) {
        this.jokeQuestion = jokeQuestion;
    }

    public String getJokeAnswer() {
        return jokeAnswer;
    }

    public void setJokeAnswer(String jokeAnswer) {
        this.jokeAnswer = jokeAnswer;
    }
}
