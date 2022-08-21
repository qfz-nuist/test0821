package cn.itcast.bookstore.categoryTest.daomain;
//根据书籍的类型进行查找
//例如历史类、文学类
public class Category {
    private String cid;
    private String cname;

    public void setCid(String cid){
        this.cid = cid;
    }


    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
