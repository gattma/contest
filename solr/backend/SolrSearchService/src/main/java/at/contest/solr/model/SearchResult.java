package at.contest.solr.model;

public class SearchResult {
    
    String title;
    String subject;

    public SearchResult() {
        super();
    }

    public SearchResult(String title, String subject) {
        this.title = title;
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
