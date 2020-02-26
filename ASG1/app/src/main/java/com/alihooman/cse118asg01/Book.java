package com.alihooman.cse118asg01;

import java.io.Serializable;
import java.util.Calendar;

import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.FALSE;

/**
 * Class for creating a storing a book object.
 */
class Book implements Serializable {

    // Fields
    private String title;
    private String author;
    private Integer year;
    private String description;
    private String isbn;
    private Boolean e_book;

    /**
     * Constructs a new book object.
     *
     * @param title Title of the book.
     * @param author Author of the book.
     * @param year Year the book was published.
     * @param description Description of the book.
     * @param isbn The book's ISBN.
     * @param e_book Is an electronic version available.
     */
    Book( String title, String author, Integer year, String description, String isbn,
                 Boolean e_book) {

        this.setTitle(title);
        this.setAuthor(author);
        this.setYear(year);
        this.setDescription(description);
        this.setIsbn(isbn);
        this.setE_book(e_book);
    }

    // Setters
    void setTitle(String title) {
        this.title = title;
    }
    void setAuthor(String author) {
        this.author = author;
    }
    void setYear(int year) {
        this.year = year;
    }
    void setDescription(String description) {
        this.description = description;
    }
    void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    void setE_book(Boolean e_book) {
        this.e_book = e_book;
    }

    // Getters
    String getTitle() {
        return this.title;
    }
    String getAuthor() {
        return this.author;
    }
    int getYear() {
        return this.year;
    }
    String getDescription() {
        return this.description;
    }
    String getIsbn() {
        return this.isbn;
    }
    Boolean getE_book() {
        return this.e_book;
    }

    /**
     * Checks if book information is both complete and valid. This is typically called
     * after inputs have been taken from the user and after an object is already made.
     * This is not as efficient as it can be but gets the job done for now.
     *
     * @return TRUE if book information is valid and complete.
     */
    Boolean hasValidData() {

        // Make sure strings aren't null or empty.
        String e = "";
        if(title == null || title.equals(e) || author == null || author.equals(e)
                || description == null || description.equals(e) || isbn == null || isbn.equals(e)
                || e_book == null || year.toString().equals(e) ) {
            return FALSE;
        }

        // Make sure year is a valid year.
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if(year == null || year > currentYear || year < 0) {
            return FALSE;
        }

        return TRUE;
    }

}
