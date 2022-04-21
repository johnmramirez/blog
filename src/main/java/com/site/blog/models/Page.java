package com.site.blog.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pages")
public class Page {

    public String id;
    public String postDate;
    public String postTeaserContent;
    public String pageSubtitle;
    public String contentTitle;
    public String contentText;

    public Page() {}

    public Page( String postDate, String pageSubTitle, String contentTitle, String pageContentText, String postTeaserContent){
        this.postDate = postDate;
        this.pageSubtitle = pageSubTitle;
        this.contentTitle = contentTitle;
        this.contentText = pageContentText;
        this.postTeaserContent = postTeaserContent;
    }

    @Override
    public String toString(){
        return String.format(
                "Page[id=%s,postDate=%s,pageSubTitle='%s',contentTitle='%s',contentText='%s', postTeaserContent='%s']",
                id, postDate, pageSubtitle, contentTitle, contentText, postTeaserContent
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostTeaserContent() {
        return postTeaserContent;
    }

    public void setPostTeaserContent(String postTeaserContent) {
        this.postTeaserContent = postTeaserContent;
    }

    public String getPageSubtitle() {
        return pageSubtitle;
    }

    public void setPageSubtitle(String pageSubtitle) {
        this.pageSubtitle = pageSubtitle;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
