package shop.donutmarket.donut.domain.chat.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import shop.donutmarket.donut.domain.chat.dto.ChatResp.ChatroomListFirebaseRespDTO;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.ChatterListFirebaseRespDTO;

// 추상화 인터페이스 : 삭제 가능하며 ChatterListServiceImpl 에서 바로 구현해도 됨
public interface ChatterListFirebaseService {

    List<ChatterListFirebaseRespDTO> getChatterList() throws ExecutionException, InterruptedException;

    List<ChatroomListFirebaseRespDTO> getChatroomList() throws ExecutionException, InterruptedException;
}
