package shop.donutmarket.donut.domain.mycategory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import shop.donutmarket.donut.domain.myCategory.model.MyCategory;
import shop.donutmarket.donut.domain.myCategory.repository.MyCategoryRepository;
import shop.donutmarket.donut.domain.user.UserConst;

@DataJpaTest
@Transactional
public class MyCategoryRepositoryTest {
    
    @Autowired
    private MyCategoryRepository myCategoryRepository;

    @BeforeEach
    void setUp(){
        MyCategory myCategory = MyCategory.builder().id(1L).user(new UserConst()).category(new CategoryConst()).build();
        myCategoryRepository.save(myCategory);
    }
    
    @Test
    void findById_Test(){
        // given
        Long id = 1L;
        
        // when
        Optional<MyCategory> myCategory = myCategoryRepository.findById(id);
        
        // then
        assertNotNull(myCategory);
    }

    @Test
    void save_Test() {
        // given
        Long id = 2L;
        MyCategory myCategory = MyCategory.builder().id(id).user(new UserConst()).category(new CategoryConst()).build();

        // when
        myCategoryRepository.save(myCategory);

        // then
        assertNotNull(myCategoryRepository.findById(id));
    }

    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;
        
        // when
        myCategoryRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), myCategoryRepository.findById(id));
    }
}
