package shop.donutmarket.donut.domain.chat.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.ChatroomListFirebaseRespDTO;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.ChatterListFirebaseRespDTO;
import shop.donutmarket.donut.domain.chat.repository.ChatDao;

@Service
@RequiredArgsConstructor
public class ChatterListServiceImpl implements ChatterListFirebaseService { // 구현 클래스

    private final ChatDao chatDao;

    @Override
    public List<ChatterListFirebaseRespDTO> getChatterList() throws ExecutionException, InterruptedException {
        return chatDao.getChatterList();
    }

    @Override
    public List<ChatroomListFirebaseRespDTO> getChatroomList() throws ExecutionException, InterruptedException {
        return chatDao.getChatroomList();
    }

}
