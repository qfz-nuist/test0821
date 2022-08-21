package cn.itcast.bookstore.userTest.service;

public class UserException extends Exception{

    public UserException(){
        super();
    }

    public UserException(String message){
        super(message);
    }
}
