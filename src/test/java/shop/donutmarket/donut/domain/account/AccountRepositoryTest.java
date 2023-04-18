package shop.donutmarket.donut.domain.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import shop.donutmarket.donut.domain.account.model.MyAccount;
import shop.donutmarket.donut.domain.account.repository.MyAccountRepository;

@DataJpaTest
@Transactional
public class AccountRepositoryTest {
    
    @Autowired
    private MyAccountRepository myAccountRepository;

    @BeforeEach
    void setUp() {
        MyAccount myAccount = MyAccount.builder().id(1L).userId(1L).brand("신한").accountNumber("123-123-123456").build();
        myAccountRepository.save(myAccount);
    }

    @Test
    void findById_Test(){
        // given
        Long id = 1L;
        // when
        Optional<MyAccount> myAccount = myAccountRepository.findById(id);
        // then
        assertNotNull(myAccount);
    }

    @Test
    void save_Test(){
        // given
        Long id = 2L;
        MyAccount myAccount = MyAccount.builder().id(2L).userId(1L).brand("농협").accountNumber("1-2312-3-123456").build();
        
        // when
        myAccountRepository.save(myAccount);
        
        // then
        assertNotNull(myAccountRepository.findById(id));
    }

    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;

        // when
        myAccountRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), myAccountRepository.findById(id)); 
    }
    
}
