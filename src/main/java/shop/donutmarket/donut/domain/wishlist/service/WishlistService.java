package shop.donutmarket.donut.domain.wishlist.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistReq.WishListDeleteReqDTO;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistReq.WishListSaveReqDTO;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistResp.MyWishListRespDTO;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistResp.WishListSaveRespDTO;
import shop.donutmarket.donut.domain.wishlist.model.Wishlist;
import shop.donutmarket.donut.domain.wishlist.repository.WishlistRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception403;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public MyWishListRespDTO 내관심목록(MyUserDetails myUserDetails) {
        Optional<User> userOP = userRepository.findByIdJoinFetch(myUserDetails.getUser().getId());
        if (userOP.isEmpty()) {
            throw new Exception404("존재하지 않는 유저입니다");
        }

        try {
            User userPS = userOP.get();
            List<Wishlist> list = wishlistRepository.findAllByUserId(userPS.getId());
            MyWishListRespDTO wishListRespDTO = new MyWishListRespDTO(list);

            return wishListRespDTO;
        } catch (Exception e) {
            throw new Exception500("내 관심목록 보기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public WishListSaveRespDTO 관심등록(WishListSaveReqDTO wishListSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
//        User user = myUserDetails.getUser();
        Optional<User> userOP = userRepository.findByIdJoinFetch(myUserDetails.getUser().getId());
        if (userOP.isEmpty()) {
            throw new Exception404("존재하지 않는 유저입니다");
        }

        Optional<Board> boardOP = boardRepository.findByIdWithAll(wishListSaveReqDTO.getBoardId());

        if (boardOP.isEmpty()) {
            throw new Exception404("존재하지 않는 게시글입니다");
        }

        try {
            User userPS = userOP.get();

            Board boardPS = boardOP.get();

            Wishlist wishlist = Wishlist.builder().board(boardPS).user(userPS).createdAt(LocalDateTime.now()).build();
            wishlistRepository.save(wishlist);

            WishListSaveRespDTO saveRespDTO = new WishListSaveRespDTO(wishlist);
            return saveRespDTO;
        } catch (Exception e) {
            throw new Exception500("내 관심 등록하기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public void 관심등록제거(WishListDeleteReqDTO wishListDeleteReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<User> userOP = userRepository.findByIdJoinFetch(myUserDetails.getUser().getId());
        if (userOP.isEmpty()) {
            throw new Exception404("존재하지 않는 유저입니다");
        }

        Optional<Wishlist> wishlistOP = wishlistRepository.findById(wishListDeleteReqDTO.getWishlistId());
        if (wishlistOP.isEmpty()) {
            throw new Exception404("위시리스트에 존재하지 않습니다");
        }
        Wishlist wishlistPS = wishlistOP.get();
        User userPS = userOP.get();
        if (!(Objects.equals(wishlistPS.getUser().getId(), userPS.getId()))) {
            throw new Exception403("위시리스트에서 삭제할 권한이 없습니다");
        }
        try {
            wishlistRepository.deleteById(wishListDeleteReqDTO.getWishlistId());
        } catch (Exception e) {
            throw new Exception500("내 관심 등록 삭제하기 실패 : " + e.getMessage());
        }
    }
}
