package com.rameysoft.streamline.main.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlRootElement(name = "book")
@XmlType(name = "book")
public class Book {


    @Id
    protected String isbn;
    protected String title;
    protected String author;
    protected String description;


    public String getDescription(){
       return description;
    }

    public void setDescription(String description){
       this.description = description;
    }


    public String getAuthor(){
       return author;
    }

    public void setAuthor(String author){
       this.author = author;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author
				+ ", description=" + description + "]";
	}
    
    
}
