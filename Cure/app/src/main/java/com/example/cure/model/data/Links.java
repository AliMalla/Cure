package com.example.cure.model.data;

public class Links {

    private final Link self;
    private final Link next;

    public Links(Link self, Link next) {
        this.self = self;
        this.next = next;

    }

    public Link getSelf() {
        return self;
    }

    public Link getNext() {
        return next;
    }

    public String getSelfHref() {
        return self.href;
    }

    public String getNextHref() { return next.href; }

    public String getSelfTitle() {
        return self.title;
    }

    public String getNextTitle() {
        return next.title;
    }



    private class Link {
        private final String href;
        private final String title;

        public Link(String href, String title) {
            this.href = href;
            this.title = title;
        }
    }

}