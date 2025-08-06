package example.실습.model.dao;

// 추후 @Component로 대체
public class BoardDao {
    // (*) 싱글톤
    private BoardDao(){}
    public static final BoardDao instance = new BoardDao();
    public static BoardDao getInstance(){
        return instance;
    }



} // class end

