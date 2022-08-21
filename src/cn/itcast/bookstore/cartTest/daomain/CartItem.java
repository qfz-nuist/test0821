package cn.itcast.bookstore.cartTest.daomain;

import cn.itcast.bookstore.bookTest.daomain.Book;

import java.math.BigDecimal;

public class CartItem {

    private Book book;
    private int count;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getCartItemTotal(){
        BigDecimal bigDecimal = new BigDecimal(count);
        BigDecimal bigDecimal1 = new BigDecimal(book.getPrice());

        return bigDecimal.multiply(bigDecimal1).doubleValue();
    }
}


