package shop.donutmarket.donut.domain.chat.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.ChatroomListFirebaseRespDTO;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.ChatterListFirebaseRespDTO;

@Repository
@RequiredArgsConstructor
public class ChatDao {
    public static final String COLLECTION_NAME1 = "chatter_list";
    public static final String COLLECTION_NAME2 = "chatroom";  

    public List<ChatterListFirebaseRespDTO> getChatterList() throws ExecutionException, InterruptedException {
        List<ChatterListFirebaseRespDTO> list = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME1).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Long chatroomId = document.getLong("chatroomId");
            Long userId = document.getLong("userId");
            Long statusCode = document.getLong("statusCode");
            LocalDateTime createdAt = document.getTimestamp("createdAt").toSqlTimestamp().toLocalDateTime();
            ChatterListFirebaseRespDTO respDTO = new ChatterListFirebaseRespDTO(chatroomId, userId, statusCode, createdAt);
            list.add(respDTO);
        }
        return list;
    }

    public List<ChatroomListFirebaseRespDTO> getChatroomList() throws ExecutionException, InterruptedException {
        List<ChatroomListFirebaseRespDTO> list = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME2).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Long eventId = document.getLong("eventId");
            Long statusCode = document.getLong("statusCode");
            LocalDateTime createdAt = document.getTimestamp("createdAt").toSqlTimestamp().toLocalDateTime();
            ChatroomListFirebaseRespDTO respDTO = new ChatroomListFirebaseRespDTO(eventId, statusCode, createdAt);
            list.add(respDTO);
        }
        return list;
    }

}
