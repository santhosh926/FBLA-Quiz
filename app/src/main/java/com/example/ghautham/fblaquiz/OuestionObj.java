package com.example.ghautham.fblaquiz;

class QuestionObj {

    private String q, one, two, three, four;
    private int correct;

    public QuestionObj(String q, String one, String two, String three, String four, int correct){
        this.q = q;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.correct = correct;
    }

    public String getQ() {
        return q;
    }

    public String getOne() {
        return one;
    }

    public String getTwo() {
        return two;
    }

    public String getThree() {
        return three;
    }

    public String getFour() {
        return four;
    }

    public int getCorrect(){
        return correct;
    }

}





