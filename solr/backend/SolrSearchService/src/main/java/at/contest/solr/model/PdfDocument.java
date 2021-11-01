package at.contest.solr.model;

import org.apache.solr.client.solrj.beans.Field;

public class PdfDocument {

    Long id;
    String title;
    String subject;
    String text;

    public Long getId() {
        return id;
    }

    @Field("id")
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Field("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    @Field("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    @Field("text")
    public void setText(String text) {
        this.text = text;
    }

}
