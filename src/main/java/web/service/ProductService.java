package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ProductDao;
import web.model.dto.ProductDto;

import java.util.List;

@Service
public class ProductService {
    @Autowired private ProductDao productDao;

    // [1] 제품등록
    public int createProduct(ProductDto productDto) {
        int result = productDao.createProduct(productDto);
        return result;
    } // func end

    // [1-2] 제품이미지등록
    public boolean createProtuctImage(int pno , String fileName){
        return productDao.createProductImage(pno , fileName);
    }

    // [2] 전체 제품 정보 + 이미지 포함 조회
    public List<ProductDto> getAllProduct(){
        // 1. 모든 제품 정보 조회
        List<ProductDto> productDtos = productDao.getAllProduct();
        // 2. 모든 제품 이미지 조회
        for (ProductDto productDto : productDtos) { // 조회된 모든 제품 dto 반복
            List<String> images = 
            productDao.getProductImages(productDto.getPno()); // 특정 제품의 pno를 이용하여 모든 제품 이미지 조회
            productDto.setImages(images); // 조회한 모든 이미지명을 특정한 제품의 dto 포함
        }
        // 3. 리턴
        return productDtos;       
    }

    // [3] 특정 제품 정보 + 이미지 포함 조회
    public ProductDto getProduct(int pno){
        ProductDto productDto = productDao.getProduct(pno);
        productDao.getProductImages(productDto.getPno());
    }

} // class end
