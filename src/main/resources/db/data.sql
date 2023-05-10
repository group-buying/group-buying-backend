insert into user_tb(username, nickname, password, email, profile, rate_id, role, provider, provider_id, status_code, created_at) values('ssar@naver.com', 'ssar','$2a$10$.qQ7kvdAo1qtk75HkCUG4O/5SKE0Y6Acf9.ZaXvTCxMZipSEC9lTW', 'ssar@naver.com', null, 1,  'ROLE_USER', '', '', 100, now());
insert into user_tb(username, nickname, password, email, profile, rate_id, role, provider, provider_id, status_code, created_at) values('kim@naver.com', 'kim','$2a$10$.qQ7kvdAo1qtk75HkCUG4O/5SKE0Y6Acf9.ZaXvTCxMZipSEC9lTW', 'kim@naver.com', null, 1,  'ROLE_USER', 'naver', '', 100, now());
insert into user_tb(username, nickname, password, email, profile, rate_id, role, provider, provider_id, status_code, created_at) values('park@naver.com', 'park','$2a$10$.qQ7kvdAo1qtk75HkCUG4O/5SKE0Y6Acf9.ZaXvTCxMZipSEC9lTW', 'park@naver.com', null, 3,  'ROLE_USER', '', '', 100, now());
insert into user_tb(username, nickname, password, email, profile, rate_id, role, provider, provider_id, status_code, created_at) values('lee@naver.com', 'lee', '$2a$10$.qQ7kvdAo1qtk75HkCUG4O/5SKE0Y6Acf9.ZaXvTCxMZipSEC9lTW', 'lee@naver.com', null, 1,  'ROLE_USER', 'naver', '', 100, now());
insert into user_tb(username, nickname, password, email, profile, rate_id, role, provider, provider_id, status_code, created_at) values('young@naver.com', 'young','$2a$10$.qQ7kvdAo1qtk75HkCUG4O/5SKE0Y6Acf9.ZaXvTCxMZipSEC9lTW', 'young@naver.com', null, 1,  'ROLE_USER', 'naver', '', 100, now());
insert into user_tb(username, nickname, password, email, profile, rate_id, role, provider, provider_id, status_code, created_at) values('style@naver.com', 'style','$2a$10$.qQ7kvdAo1qtk75HkCUG4O/5SKE0Y6Acf9.ZaXvTCxMZipSEC9lTW', 'style@naver.com', null, 1,  'ROLE_USER', 'naver', '', 100, now());
insert into user_tb(username, nickname, password, email, profile, rate_id, role, provider, provider_id, status_code, created_at) values('pro@naver.com', 'pro','$2a$10$.qQ7kvdAo1qtk75HkCUG4O/5SKE0Y6Acf9.ZaXvTCxMZipSEC9lTW', 'pro@naver.com', null, 1,  'ROLE_USER', 'naver', '', 100, now());
insert into user_tb(username, nickname, password, email, profile, rate_id, role, provider, provider_id, status_code, created_at) values('naver_1', 'naver_1','$2a$10$.qQ7kvdAo1qtk75HkCUG4O/5SKE0Y6Acf9.ZaXvTCxMZipSEC9lTW', 'zzxc@naver.com', null, 2, 'ROLE_USER', 'naver', '1', 103, now());
insert into user_tb(username, nickname, password, email, profile, rate_id, role, provider, provider_id, status_code, created_at) values('admin@naver.com', 'admin','$2a$10$.qQ7kvdAo1qtk75HkCUG4O/5SKE0Y6Acf9.ZaXvTCxMZipSEC9lTW', 'admin@naver.com', null, 2, 'ROLE_ADMIN', '', '', 100, now());

insert into rate(rate_name, created_at) values('글레이즈드', now());
insert into rate(rate_name, created_at) values('딸기도넛', now());
insert into rate(rate_name, created_at) values('초코도넛', now());
insert into rate(rate_name, created_at) values('별사탕도넛', now());

insert into my_location(user_id, state, city, town, created_at) values(1, '부산광역시', '부산진구', '부전동', now());

insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(6, '삼각김밥 1+1 같이 사실분?', 1, '서면 역사내 편의점에서 삼각김밥 같이 사실분 구합니다. 직거래 우선이요', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%ED%8E%B8%EC%9D%98%EC%A0%90%EB%94%94%ED%8F%B4%ED%8A%B8.jpg', 1, 200, 32, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(6, '편의점 할인 행사 같이 사실분', 2, '서면 1번출구 편의점에서 같이 사실분 구합니다. 직거래 우선이요', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%9D%8C%EB%A3%8C.jpeg', 2, 200, 23, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(6, '컵라면 박스 삽니다', 1, '직거래만 받습니다. 박스 1+1 나눠 가지실분', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%BB%B5%EB%9D%BC%EB%A9%B4.jpeg', 3, 200, 50, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(6, '대용량 음료 1+1 나눠가지실분', 3, 'CU 아메리카노 대용량 1+1중인데 2개다 들고가기 곤란합니다 반값만 받을게요', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%95%84%EB%A9%94%EB%A6%AC%EC%B9%B4%EB%85%B8.jpeg', 4, 200, 33, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(6, '베라 행사 같이 사실분', 5, '베라 굿즈 행사 사려하는데 커피랑 굿즈 제가 살테니 아이스크림 값만 내주시면 되요', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EB%B2%A0%EB%9D%BC.jpeg', 5, 200, 45, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(6, '글레이즈드 도넛 더즌 1+1 같이 사실분 구해요', 4, '쿠폰사용하려는데 양이 너무 많아요 할인한 가격에서 절반해서 기존보다 더 싸게 드릴게요', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EB%8F%84%EB%84%9B.jpeg', 6, 200, 18, 0, '부산광역시', '부산진구', '부전동', now());

insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(7, '이니스프리 할인 행사 같이 사실분', 5, '이니스프리 지하상가점에서 스킨케어 사실분 구합니다.', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%9D%B4%EB%8B%88%EC%8A%A4%ED%94%84%EB%A6%AC.jpg', 7, 200, 73, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(7, '올리브영 행사 같이 사실분', 3, '쥬디스태화점에서 토너 같이 사실분 구합니다.', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%98%AC%EB%A6%AC%EB%B8%8C%EC%98%81.jpeg', 8, 200, 18, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(7, '네일아트 잘아시는 분', 6, '네일아트 받을려는데 같이 조언해주실분 구해요', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EB%84%A4%EC%9D%BC%EC%95%84%ED%8A%B8.jpeg', 9, 200, 91, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(7, '헤어 같이 하실분', 7, '헤어 친구끼리 같이 하면 할인폭이 진짜 쎄서 같이 헤어 하실분 구해요 여성분 우선', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%ED%97%A4%EC%96%B4.jpg', 10, 200, 73, 0, '부산광역시', '부산진구', '부전동', now());

insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(2, '필라테스 같이 하실분', 2, '필라테스 이용권 3개월 같이 하실분 구해요 여성분 우선', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%ED%95%84%EB%9D%BC%ED%85%8C%EC%8A%A4.jpeg', 11, 200, 43, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(2, '볼링 상대 구합니다', 1, '볼링 상대분 구합니다 250대 실력분정도 생각하고 있습니다', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EB%B3%BC%EB%A7%81.jpeg', 12, 200, 138, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(2, '다트 한판 하실분', 5, '다트 연습중입니다 처음 하시는분도 괜찮습니다 같이 해주시면 되요', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EB%8B%A4%ED%8A%B8.jpeg', 13, 45, 73, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(2, '배팅 가르쳐주실분 구합니다', 8, '야구 잘하시는분 배팅 자세나 타이밍 연습중입니다 같이 배우거나 가르쳐주실분 구해요', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%8A%A4%ED%8F%AC%EC%B8%A0%EB%94%94%ED%8F%B4%ED%8A%B8.jpg', 14, 200, 123, 0, '부산광역시', '부산진구', '부전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(2, '실탄 사격 동호회 모임원 구합니다', 3, '사격을 취미로 하는 모임입니다. 같이 모여서 스트레스도 풀고 가는 사격 동호회 모임원분들 구합니다', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%82%AC%EA%B2%A9.jpeg', 15, 200, 78, 0, '부산광역시', '부산진구', '부전동', now());

insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(3, '전자상가 부품 구합니다', 3, '베어링과 연결핀 같이사실분 구합니다.', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%9E%A1%ED%99%94%EB%94%94%ED%8F%B4%ED%8A%B8.jpg', 16, 200, 45, 0, '부산광역시', '부산진구', '부전동', now());

insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(6, '감전시장 건어물 같이 사실분 구합니다', 5, '마른 멸치 새우랑 국물용 뒤포리삽니다', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EA%B1%B4%EC%96%B4%EB%AC%BC.jpeg', 17, 200, 19, 0, '부산광역시', '사상구', '감전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(6, '감전시장 먹거리 투어 가이드분 구합니다', 5, '야시장에서 뭐가 좋은지 알려주실분 구매 비용은 제가 지불할게요', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EA%B1%B4%EC%96%B4%EB%AC%BC.jpeg', 18, 200, 19, 0, '부산광역시', '사상구', '감전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(5, '아웃렛 행사 같이 가실분?', 6, '아웃렛 시즌 마감 세일 같이 패션에 대해 관심 많으신분들 환영', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%9D%98%EB%A5%98%EB%94%94%ED%8F%B4%ED%8A%B8.jpg', 19, 200, 26, 0, '부산광역시', '사상구', '감전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(2, '골프 상대 구합니다', 7, '명문 골프 연습장에서 골프하실분 구합니다 실력에 상관없이 즐기실분', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EA%B3%A8%ED%94%84.jpeg', 20, 200, 14, 0, '부산광역시', '사상구', '감전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(3, '청과류 할인 합니다', 8, '부산바나나 할인중이에요 같이 사실분', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%8B%9D%ED%92%88%EB%94%94%ED%8F%B4%ED%8A%B8.jpg', 21, 200, 15, 0, '부산광역시', '사상구', '감전동', now());
insert into board(category_id, title, organizer_id, content, img, event_id, status_code, views, recommend, state, city, town, created_at) values(3, '감전초 학년별 준비물 공동구매', 8, '탑 매직에서 공동구매 합니다 이번주 준비물 2학년 지점토 5학년 조각칼', 'https://groupbuying.s3.ap-northeast-2.amazonaws.com/%EC%9E%A1%ED%99%94%EB%94%94%ED%8F%B4%ED%8A%B8.jpg', 15, 200, 15, 0, '부산광역시', '사상구', '감전동', now());

insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.157744, 129.059418, 1, '직거래', now(), now(), 900, now());
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.155489, 129.058910, 2, '직거래', now(), now(), 3000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.155489, 129.058910, 1, '직거래', now(), now(), 4000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.155489, 129.058910, 1, '직거래', now(), now(), 2500, now());
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.153903, 129.059409, 1, '직거래', now(), now(), 6000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.156948, 129.056689, 1, '직거래', now(), now(), 8000, now()); 

insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.156494, 129.059256, 5, '직거래', now(), now(), 20000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.155495, 129.059544, 2, '직거래', now(), now(), 15000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.155448, 129.058037, 1, '직거래', now(), now(), 30000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.154581, 129.060314, 1, '직거래', now(), now(), 50000, now()); 

insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.154039, 129.059639, 1, '직거래', now(), now(), 50000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.156647, 129.062089, 3, '직거래', now(), now(), 20000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.155176, 129.060478, 2, '직거래', now(), now(), 7000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.154372, 129.058601, 2, '직거래', now(), now(), 5000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.153063, 129.057661, 5, '직거래', now(), now(), 100000, now()); 

insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.156327, 129.063973, 10, '직거래', now(), now(), 3000, now()); 

insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.155809, 128.984530, 4, '직거래', now(), now(), 8000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.155808, 128.984531, 4, '직거래', now(), now(), 100, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.162967, 128.983367, 2, '직거래', now(), now(), 50000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.160091, 128.982292, 1, '직거래', now(), now(), 70000, now()); 
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.158737, 128.985634, 3, '직거래', now(), now(), 6000, now());
insert into event(latitude, longtitude, qty, payment_type, start_at, end_at, price, created_at) values(35.156124, 128.990721, 60, '직거래', now(), now(), 3500, now());

insert into tag(board_id, comment, created_at) values(1, '편의점', now());
insert into tag(board_id, comment, created_at) values(1, '1+1', now());
insert into tag(board_id, comment, created_at) values(2, '편의점', now());
insert into tag(board_id, comment, created_at) values(2, '1+1', now());
insert into tag(board_id, comment, created_at) values(3, '1+1', now());
insert into tag(board_id, comment, created_at) values(4, '편의점', now());
insert into tag(board_id, comment, created_at) values(4, '1+1', now());
insert into tag(board_id, comment, created_at) values(5, '베라', now());
insert into tag(board_id, comment, created_at) values(6, '1+1', now());

insert into tag(board_id, comment, created_at) values(7, '이니스프리', now());
insert into tag(board_id, comment, created_at) values(7, '화장품', now());
insert into tag(board_id, comment, created_at) values(7, '미용', now());
insert into tag(board_id, comment, created_at) values(8, '올리브영', now());
insert into tag(board_id, comment, created_at) values(8, '화장품', now());
insert into tag(board_id, comment, created_at) values(8, '미용', now());
insert into tag(board_id, comment, created_at) values(9, '네일', now());
insert into tag(board_id, comment, created_at) values(9, '네일아트', now());
insert into tag(board_id, comment, created_at) values(10, '헤어', now());

insert into tag(board_id, comment, created_at) values(11, '필라테스', now());
insert into tag(board_id, comment, created_at) values(11, '정기권', now());
insert into tag(board_id, comment, created_at) values(12, '볼링', now());
insert into tag(board_id, comment, created_at) values(12, '운동상대', now());
insert into tag(board_id, comment, created_at) values(13, '다트', now());
insert into tag(board_id, comment, created_at) values(13, '연습', now());
insert into tag(board_id, comment, created_at) values(14, '야구', now());
insert into tag(board_id, comment, created_at) values(14, '강의', now());
insert into tag(board_id, comment, created_at) values(14, '연습', now());
insert into tag(board_id, comment, created_at) values(15, '사격', now());
insert into tag(board_id, comment, created_at) values(15, '실탄', now());
insert into tag(board_id, comment, created_at) values(15, '동호회', now());
insert into tag(board_id, comment, created_at) values(15, '모임', now());
insert into tag(board_id, comment, created_at) values(15, '취미', now());

insert into tag(board_id, comment, created_at) values(16, '베어링', now());

insert into tag(board_id, comment, created_at) values(17, '건어물', now());
insert into tag(board_id, comment, created_at) values(17, '감전시장', now());
insert into tag(board_id, comment, created_at) values(18, '먹거리', now());
insert into tag(board_id, comment, created_at) values(18, '투어', now());
insert into tag(board_id, comment, created_at) values(18, '감전시장', now());
insert into tag(board_id, comment, created_at) values(19, '오프시즌', now());
insert into tag(board_id, comment, created_at) values(19, '할인', now());
insert into tag(board_id, comment, created_at) values(20, '골프', now());
insert into tag(board_id, comment, created_at) values(20, '운동상대', now());
insert into tag(board_id, comment, created_at) values(21, '바나나', now());
insert into tag(board_id, comment, created_at) values(21, '청과', now());
insert into tag(board_id, comment, created_at) values(22, '감전초', now());
insert into tag(board_id, comment, created_at) values(22, '준비물', now());

insert into participant(event_id, user_id, qty, created_at, status_code) values(1, 2, 1, now(), 300);
insert into participant(event_id, user_id, qty, created_at, status_code) values(1, 3, 1, now(), 300);

insert into payment(event_id, user_id, payment_info_id, payment_type, status_code, confirmed, created_at) values(1, 1, 1, '직거래', 401, 0, now());

insert into wishlist(user_id, board_id, created_at) values(1, 1, now());

insert into my_category(user_id, category_id, created_at) values(1, 1, now());
insert into my_category(user_id, category_id, created_at) values(1, 2, now());
insert into my_category(user_id, category_id, created_at) values(1, 3, now());
insert into my_category(user_id, category_id, created_at) values(1, 4, now());

insert into report(reporter_id, reported_id, board_id, title, content, report_type, status_code, created_at) values(2, 1, 1, '노쇼했어요', '삼각김밥 1+1 하자는데 어디에있는지 연락도 안되고 노쇼 했어요', '뇨쇼', 600, now());

insert into blacklist(user_id, blocked_user_id, created_at) values(1, 2, now());

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
insert into category(name, created_at) values('티켓 및 교환권', now());

commit;