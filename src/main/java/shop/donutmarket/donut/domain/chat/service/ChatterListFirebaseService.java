package shop.donutmarket.donut.domain.chat.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import shop.donutmarket.donut.domain.chat.dto.ChatResp.ChatroomListFirebaseRespDTO;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.ChatterListFirebaseRespDTO;

public interface ChatterListFirebaseService {
    List<ChatterListFirebaseRespDTO> getChatterList() throws ExecutionException, InterruptedException;

    List<ChatroomListFirebaseRespDTO> getChatroomList() throws ExecutionException, InterruptedException;
}
