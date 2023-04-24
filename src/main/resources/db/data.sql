insert into user_tb(password, email, name, profile, rate_id, type, role, status_code, created_at) values('1234', 'ssar@naver.com', 'ssar', null, 1, true, 'user', 100, now());
insert into user_tb(password, email, name, profile, rate_id, type, role, status_code, created_at) values('1234', 'zzxc@naver.com', 'zzxc', null, 2, true, 'user', 100, now());
insert into user_tb(password, email, name, profile, rate_id, type, role, status_code, created_at) values('1234', 'qqwe@naver.com', 'qqwe', null, 3, true, 'user', 100, now());
insert into user_tb(password, email, name, profile, rate_id, type, role, status_code, created_at) values('1234', 'ppoi@naver.com', 'ppoi', null, 4, false, 'user', 100, now());

insert into rate(user_id, rate_name, rate_point, created_at) values(1, '글레이즈드', 30, now());
insert into rate(user_id, rate_name, rate_point, created_at) values(2, '딸기도넛', 70, now());
insert into rate(user_id, rate_name, rate_point, created_at) values(3, '초코도넛', 40, now());
insert into rate(user_id, rate_name, rate_point, created_at) values(4, '별사탕도넛', 120, now());

insert into my_location(user_id, state, city, town, created_at) values(1, '부산광역시', '부산진구', '부전동', now());

insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(3, '삼각김밥 1+1 같이 사실분?', 1, '서면 1번출구 편의점에서 삼각김밥 같이 사실분 구합니다. 직거래 우선이요', null, 1, 200, 50, 0, '부산', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(3, '편의점 할인 행사 같이 사실분', 1, '서면 1번출구 편의점에서 같이 사실분 구합니다. 직거래 우선이요', null, 2, 200, 50, 0, '부산', '부산진구', '부전동', now());

insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.156834, 129.058987, 1, '직거래', now(), now(), 900, now());
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.156834, 129.058987, 1, '직거래', now(), now(), 900, now());

insert into tag(board_id, comment, created_at) values(1, '편의점', now());
insert into tag(board_id, comment, created_at) values(1, '1+1', now());

insert into participant(event_id, user_id, qty, created_at, status_code) values(1, 2, 1, now(), 300);
insert into participant(event_id, user_id, qty, created_at, status_code) values(1, 3, 1, now(), 300);

insert into payment(event_id, user_id, payment_type, status_code, confirmed, created_at) values(1, 1, '직거래', 401, 0, now());

insert into wishlist(user_id, board_id, created_at) values(2, 1, now());

insert into my_category(user_id, category_id, created_at) values(1, 1, now());

insert into report(reporter_id, reported_id, board_id, title, content, report_type, status_code, created_at) values(4, 1, 1, '노쇼했어요', '삼각김밥 1+1 하자는데 어디에있는지 연락도 안되고 노쇼 했어요', '뇨쇼', 600, now());

insert into blacklist(user_id, blocked_user_id, created_at) values(1, 4, now());

insert into chatroom(event_id, status_code, created_at) values(1, 500, now());

insert into chatter_list(chatroom_id, user_id, status_code, created_at) values(1, 1, 700, now());
insert into chatter_list(chatroom_id, user_id, status_code, created_at) values(1, 2, 700, now());
insert into chatter_list(chatroom_id, user_id, status_code, created_at) values(1, 3, 701, now());

insert into review(reviewer_id, reviewed_id, score, comment, created_at) values(2, 1, 5, '빠른 거래 좋아요', now());

insert into category(name, created_at) values('생활가전', now());
insert into category(name, created_at) values('스포츠', now());
insert into category(name, created_at) values('잡화', now());
insert into category(name, created_at) values('유아물품', now());
insert into category(name, created_at) values('의류', now());
insert into category(name, created_at) values('식품', now());
insert into category(name, created_at) values('미용', now());
insert into category(name, created_at) values('반려동물 물품', now());
insert into category(name, created_at) values('식물', now());
insert into category(name, created_at) values('문구', now());
insert into category(name, created_at) values('티켓/교환권', now());
insert into category(name, created_at) values('편의점', now());
insert into category(name, created_at) values('홈쇼핑', now());
insert into category(name, created_at) values('도매상', now());

insert into status_code(id, type, status, created_at) values(100, 'user', '활성화', now());
insert into status_code(id, type, status, created_at) values(101, 'user', '휴면', now());
insert into status_code(id, type, status, created_at) values(102, 'user', '탈퇴', now());
insert into status_code(id, type, status, created_at) values(200, 'board', '진행중', now());
insert into status_code(id, type, status, created_at) values(201, 'board', '거래완료', now());
insert into status_code(id, type, status, created_at) values(202, 'board', '마감', now());
insert into status_code(id, type, status, created_at) values(203, 'board', '삭제', now());
insert into status_code(id, type, status, created_at) values(300, 'participant', '참가', now());
insert into status_code(id, type, status, created_at) values(301, 'participant', '미채택', now());
insert into status_code(id, type, status, created_at) values(302, 'participant', '채택', now());
insert into status_code(id, type, status, created_at) values(303, 'participant', '참가 취소', now());
insert into status_code(id, type, status, created_at) values(400, 'payment', '결제전', now());
insert into status_code(id, type, status, created_at) values(401, 'payment', '결제완료', now());
insert into status_code(id, type, status, created_at) values(402, 'payment', '구매확정', now());
insert into status_code(id, type, status, created_at) values(500, 'chatroom', '활성화', now());
insert into status_code(id, type, status, created_at) values(501, 'chatroom', '비활성화', now());
insert into status_code(id, type, status, created_at) values(502, 'chatroom', '삭제', now());
insert into status_code(id, type, status, created_at) values(600, 'report', '대기', now());
insert into status_code(id, type, status, created_at) values(601, 'report', '처리중', now());
insert into status_code(id, type, status, created_at) values(602, 'report', '처리완료', now());
insert into status_code(id, type, status, created_at) values(700, 'chatter', '참가', now());
insert into status_code(id, type, status, created_at) values(701, 'chatter', '나감', now());
insert into status_code(id, type, status, created_at) values(702, 'chatter', '강퇴당함', now());

commit;