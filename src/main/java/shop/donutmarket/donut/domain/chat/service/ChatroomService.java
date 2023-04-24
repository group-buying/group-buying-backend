package shop.donutmarket.donut.domain.chat.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.chat.repository.ChatroomRepository;
import shop.donutmarket.donut.domain.chat.repository.ChatterListRepository;

@Service
@RequiredArgsConstructor
public class ChatroomService {

    private final ChatroomRepository chatroomRepository;
    private final ChatterListRepository chatterListRepository;
    
}
