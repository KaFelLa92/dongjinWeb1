package web.model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.dto.ProductDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao extends Dao {

    // [1] 제품 등록
    public int createProduct(ProductDto productDto){
        // 1. 로그인상태 확인 후, 비 로그인이면 0 반환
        // 2. 제품정보 DB 처리
        try{
            String sql = "insert into product (pname , pprice , pcomment , plat , plng , mno) values (? , ? , ? , ? , ? , ?)";
            PreparedStatement ps = conn.prepareStatement(sql , PreparedStatement.RETURN_GENERATED_KEYS); // (1) PK값 리턴 받기
            ps.setString(1, productDto.getPname());
            ps.setInt(2, productDto.getPprice());
            ps.setString(3, productDto.getPcomment());
            ps.setDouble(4, productDto.getPlat());
            ps.setDouble(5, productDto.getPlng());
            ps.setInt(6, productDto.getMno());
            int count = ps.executeUpdate();
            if (count == 1){
                ResultSet rs = ps.getGeneratedKeys(); // (2) 등록된 레코드의 PK값 조회
                if (rs.next()) return rs.getInt(1); // (3) 등록된 레코드의 첫번째 pk값 반환(pno)
            }
            // 3. 업로드 파일이 존재하면 업로드 서비스 호출하여 업로드 처리
            // 4. 처리된 업로드 파일 DB 처리
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return 0;
    } // func end

    // [1-2] 제품 이미지 등록
    public boolean createProductImage(int pno , String fileName){
        try{
            String sql = "insert into productimg (pimgname, pno) values (? , ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fileName);
            ps.setInt(2, pno);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            System.out.println(e);
        } // catch end
        return false;
    } // func end

    
    // [2] 제품 전체 조회
    public List<ProductDto> getAllProduct() {
        List<ProductDto> list = new ArrayList<>();
        try{
            String sql = "select * from product";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while ((rs.next())) {
                ProductDto productDto = new ProductDto();
                productDto.setPno(rs.getInt("pno"));
                productDto.setPname(rs.getString("pname"));
                productDto.setPprice(rs.getInt("pprice"));
                productDto.setPcomment(rs.getString("pcomment"));
                productDto.setPdate(rs.getString("pdate"));
                productDto.setPlat(rs.getDouble("plat"));
                productDto.setPlng(rs.getDouble("plng"));
                productDto.setMno(rs.getInt("mno"));
                list.add(productDto);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return list;
    }
    
    // [2-2] 특정한 제품의 제품 이미지명 전체 조회
    public List<String> getProductImages(int pno) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "select * from productimg where pno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String pimgname = rs.getString("pimgname");
                list.add(pimgname);
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return list;
    }
    
    // [3] 제품 상세 조회
    public ProductDto getProduct(int pno){
        try{
            String sql = "select * from product where pno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                ProductDto productDto = new ProductDto();
                productDto.setPno(rs.getInt("pno"));
                productDto.setPname(rs.getString("pname"));
                productDto.setPprice(rs.getInt("pprice"));
                productDto.setPcomment(rs.getString("pcomment"));
                productDto.setPdate(rs.getString("pdate"));
                productDto.setPlat(rs.getDouble("plat"));
                productDto.setPlng(rs.getDouble("plng"));
                productDto.setMno(rs.getInt("mno"));
                return productDto;
            }

        } catch (Exception e) {
            System.out.println(e);
        } // catch end
        return null;
    } // func end

}
