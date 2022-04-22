package com.example.cure.model.data;

public class Root{

    private final int from;
    private final int to;
    private final int count;
    private Links links;
    private Hit[] hits;


    public Root(int from, int to, int count, Links links, Hit[] hits) {
        this.from = from;
        this.to = to;
        this.count = count;
        this.links = links;
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

    public Links getLinks() { return links; }

    public Hit[] getHits() {
        return hits;
    }
}
