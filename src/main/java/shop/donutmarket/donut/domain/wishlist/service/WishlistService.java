package shop.donutmarket.donut.domain.wishlist.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistReq.WishListSaveReqDTO;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistResp.WishListSaveRespDTO;
import shop.donutmarket.donut.domain.wishlist.model.Wishlist;
import shop.donutmarket.donut.domain.wishlist.repository.WishlistRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@Service
@RequiredArgsConstructor
public class WishlistService {
    
    private final WishlistRepository wishlistRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public WishListSaveRespDTO 관심등록(WishListSaveReqDTO wishListSaveReqDTO,  @AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = myUserDetails.getUser();
        Optional<Board> boardOP = boardRepository.findByIdWithAll(wishListSaveReqDTO.getBoardId());
        Board boardPS = boardOP.get();

        Wishlist wishlist = Wishlist.builder().board(boardPS).user(user).createdAt(LocalDateTime.now()).build();
        wishlistRepository.save(wishlist);

        WishListSaveRespDTO saveRespDTO = new WishListSaveRespDTO(boardPS.getTitle(), boardPS.getState(), boardPS.getCity(), LocalDateTime.now());
        return saveRespDTO;
    }

}
