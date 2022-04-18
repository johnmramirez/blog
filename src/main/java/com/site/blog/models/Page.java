package com.site.blog.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pages")
public class Page {

    public String id;

    public String postId;
    public String postDate;
    public String postTeaserContent;
    public String pageSubtitle;
    public String contentTitle;
    public String contentText;

    public Page() {}

    public Page(String postId, String postDate, String pageSubTitle, String contentTitle, String pageContentText, String postTeaserContent){
        this.postId = postId;
        this.postDate = postDate;
        this.pageSubtitle = pageSubTitle;
        this.contentTitle = contentTitle;
        this.contentText = pageContentText;
        this.postTeaserContent = postTeaserContent;
    }

    @Override
    public String toString(){
        return String.format(
                "Page[id=%s,postId=%s,postDate=%s,pageSubTitle='%s',contentTitle='%s',contentText='%s', postTeaserContent='%s']",
                id, postId, postDate, pageSubtitle, contentTitle, contentText, postTeaserContent
        );
    }
}
