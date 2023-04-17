insert into user_tb(username, password, email, name, profile, rate_id, type, role, status_code, created_at) values('ssar', '1234', 'ssar@naver.com', 'ssar', null, 1, 'naver', 'user', 1, now());
insert into user_tb(username, password, email, name, profile, rate_id, type, role, status_code, created_at) values('zzxc', '1234', 'zzxc@naver.com', 'zzxc', null, 2, 'kakao', 'user', 1, now());
insert into user_tb(username, password, email, name, profile, rate_id, type, role, status_code, created_at) values('qqwe', '1234', 'qqwe@naver.com', 'qqwe', null, 3, 'naver', 'user', 1, now());
insert into user_tb(username, password, email, name, profile, rate_id, type, role, status_code, created_at) values('ppoi', '1234', 'ppoi@naver.com', 'ppoi', null, 4, null, 'user', 1, now());

insert into rate(user_id, rate_name, rate_point, rate_img) values(1, '글레이즈드', 30, 'temp');
insert into rate(user_id, rate_name, rate_point, rate_img) values(2, '딸기도넛', 70, 'temp');
insert into rate(user_id, rate_name, rate_point, rate_img) values(3, '초코도넛', 40,'temp');
insert into rate(user_id, rate_name, rate_point, rate_img) values(4, '별사탕도넛', 120, 'temp');

insert into my_location(user_id, state, city, town) values(1, '부산광역시', '부산진구', '부전동');

insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(3, '삼각김밥 1+1 같이 사실분?', 1, '서면 1번출구 편의점에서 삼각김밥 같이 사실분 구합니다. 직거래 우선이요', null, 1, 200, 50, 0, '부산', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(3, '편의점 할인 행사 같이 사실분', 1, '서면 1번출구 편의점에서 같이 사실분 구합니다. 직거래 우선이요', null, 2, 200, 50, 0, '부산', '부산진구', '부전동', now());

insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, status_code, price) values(35.156834, 129.058987, 1, '직거래', now(), now(), 200, 900);
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, status_code, price) values(35.156834, 129.058987, 1, '직거래', now(), now(), 200, 900);

insert into tag(board_id, comment) values(1, '편의점');
insert into tag(board_id, comment) values(1, '1+1');

insert into participant(event_id, user_id, qty, created_at, status_code) values(1, 2, 1, now(), 300);
insert into participant(event_id, user_id, qty, created_at, status_code) values(1, 3, 1, now(), 300);

insert into payment(participant_id, payment_type, status_code, confirmed, created_at) values(1, '직거래', 401, 0, now());

insert into wishlist(user_id, board_id) values(2, 1);

insert into my_category(user_id, category_id) values(1, 1);

insert into report(reporter_id, reported_id, board_id, title, content, report_type, status_code, created_at) values(4, 1, 1, '노쇼했어요', '삼각김밥 1+1 하자는데 어디에있는지 연락도 안되고 노쇼 했어요', '뇨쇼', 600, now());

insert into blacklist(user_id, blocked_user_id, created_at) values(1, 4, now());

insert into chatroom(status_code, created_at) values(500, now());

insert into chatter_list(chatroom_id, user_id) values(1, 1);
insert into chatter_list(chatroom_id, user_id) values(1, 2);
insert into chatter_list(chatroom_id, user_id) values(1, 3);

insert into review(reviewer_id, reviewed_id, score, comment, created_at) values(2, 1, 5, '빠른 거래 좋아요', now());

insert into category(name) values('생활가전');
insert into category(name) values('유아물품');
insert into category(name) values('식품');
insert into category(name) values('스포츠');
insert into category(name) values('잡화');
insert into category(name) values('의류');
insert into category(name) values('미용');
insert into category(name) values('반려동물 물품');
insert into category(name) values('식물');
insert into category(name) values('문구');
insert into category(name) values('티켓/교환권');
insert into category(name) values('편의점');
insert into category(name) values('홈쇼핑');
insert into category(name) values('도매상');

insert into status_code(id, type, status) values(100, 'user', '활성화');
insert into status_code(id, type, status) values(101, 'user', '휴면');
insert into status_code(id, type, status) values(102, 'user', '탈퇴');
insert into status_code(id, type, status) values(200, 'board', '진행중');
insert into status_code(id, type, status) values(201, 'board', '거래완료');
insert into status_code(id, type, status) values(202, 'board', '마감');
insert into status_code(id, type, status) values(203, 'board', '삭제');
insert into status_code(id, type, status) values(300, 'participant', '참가');
insert into status_code(id, type, status) values(301, 'participant', '미채택');
insert into status_code(id, type, status) values(302, 'participant', '채택');
insert into status_code(id, type, status) values(303, 'participant', '참가 취소');
insert into status_code(id, type, status) values(400, 'payment', '결제전');
insert into status_code(id, type, status) values(401, 'payment', '결제완료');
insert into status_code(id, type, status) values(402, 'payment', '구매확정');
insert into status_code(id, type, status) values(500, 'chatroom', '활성화');
insert into status_code(id, type, status) values(501, 'chatroom', '비활성화');
insert into status_code(id, type, status) values(502, 'chatroom', '삭제');
insert into status_code(id, type, status) values(600, 'report', '대기');
insert into status_code(id, type, status) values(601, 'report', '처리중');
insert into status_code(id, type, status) values(602, 'report', '처리완료');

commit;