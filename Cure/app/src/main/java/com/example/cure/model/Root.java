package com.example.cure.model;

import java.util.List;

public class Root {

    private final int from;
    private final int to;
    private final int count;
    private Hit[] hits;
    private Link self;
    private Link next;


    /* public Root(){

    }

     */

    public Root(int from, int to, int count, Link self, Link next, Hit[] hits) {
        this.from = from;
        this.to = to;
        this.count = count;
        this.self = self;
        this.next = next;
        this.hits = hits;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCount() {
        return count;
    }

    public Link getSelf() {
        return self;
    }

    public Link getNext() {
        return next;
    }

    public Hit[] getHits() {
        return hits;
    }
}
