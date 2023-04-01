public class Book extends Product {
    private String publisher;
    private int pagesNumber;
    private String hardcover;
    private String author;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setHardcover(String hardcover) {
        this.hardcover = hardcover;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", publisher: '" + publisher + '\'' +
                ", pagesNumber: " + pagesNumber +
                ", hardcover: '" + hardcover + '\'' +
                ", author: '" + author + '\'' ;
    }
}
