# Donut-Market

<h1 align="center">
  <br>
  <br>
  <img width="300" alt="image" src="https://user-images.githubusercontent.com/93416157/236750407-79b47b37-e145-481f-b178-52d713077963.png">
  <br>
  <br>
  <br>
</h1>

# 👨‍👨‍👧 서비스 소개
> 사고 싶은 제품이 있는데, 2+1이라구요?
> 우리 동네에서 쉽게 공동구매를 하고 싶다구요?
><br> 그런 분들을 위해 기획한 서비스입니다.
><br> 부담없이 사용할 수 있는 우리 동네 공동구매 플랫폼 도넛마켓입니다.

<br>

# 데모영상



<br>

# 발표자료


<br>

## Contact

### Back-End
|                                                 성호석(팀장)                                                  |                                                 강민호                                                  |
| :-----------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------: | 
| <img src="https://avatars.githubusercontent.com/u/93416157?v=4" alt="profile" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/122412968?v=4" alt="profile" width="100" height="100"> | 
|                                   [@Hoseok-Seong](https://github.com/Hoseok-Seong)                                   |                                 [@K-Minho](https://github.com/K-Minho)                                 |

<br>

### Front-End
|                                                 성소정(팀장)                                                  |                                                 이현석                                                  |
| :-----------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------: | 
| <img src="https://avatars.githubusercontent.com/u/80329856?v=4" alt="profile" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/122357103?v=4" alt="profile" width="100" height="100"> | 
|                                   [@S-Sojung](https://github.com/S-Sojung)                                   |                                 [@hyunseok9037](https://github.com/hyunseok9037)                                 |

<br>

# ⏰ 개발 기간
- 2023.04.10~2023.05.10(5주)

<br>

# ⚙️ 기술스택
<img width="800" alt="image" src="https://github.com/group-buying/group-buying-backend/assets/93416157/8367b206-467b-4f9c-af7d-0b166b065f75">

<br>

# 📋  아키텍처

![erd](https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/c516be4b-474b-41df-a871-29b3d23bd6b9)

<br>

# 📋  ERD-Diagram

![erd](https://user-images.githubusercontent.com/93416157/236793451-d393b37b-09c3-469e-bb8a-4caabfa86001.png)

<br>

# Coding-Convention
<details>
  <summary>Camel Case</summary>
  <ul>
  <br>
    <li>폴더명, 패키지명, 메서드명, 변수명은 첫 단어는 소문자로, 그 뒤는 대문자로</li>
    <li>단어와 단어 사이는 붙이는 것을 원칙으로 함.</li>
    <li>단, 클래스명은 파스칼 표기법으로 함.</li>
  <br>
  </ul>
</details>
<details>
  <summary>주석 규칙</summary>
  <ul>
  <br>
    <li>한줄을 "//"로 적고, 그 이상은 "/* */"로 함.</li>
  <br>
  </ul>
</details>
<details>
  <summary>데이터베이스 명명 규칙</summary>
  <ul>
  <br>
    <li>데이터베이스 명은 영어 소문자로 구성. 대문자는 _로 표현한다.</li>
    <li>테이블은 영어 소문자로 구성, 대문자는 _로 표현한다. 대분류_의미있는 테이블 명 형태로 작성한다.</li>
    <li>컬럼은 영어 소문자로 구성, 대문자는 _로 표현한다. 의미있는 컬럼명_접미사 형태로 작성한다.</li>
    <li>컬럼의 성질을 나타내는 접미사를 사용한다. (사용하는 데이터의 타입을 나타내는 것이 아님에 유의)</li>
    <li>단어는 언더바.</li>
  <br>
  </ul>
</details>
<details>
  <summary>변수명 명명 Recommend</summary>
  <ul>
  <br>
    <li>배열이 들어가는 변수면 마지막에 List.</li>
    <li>통신이 쓰인 변수는 data.</li>
    <li>함수는 동사가 제일 먼저 오고, 뒤에 명사가 붙는다.</li>
  <br>
  </ul>
</details>
<details>
  <summary>CSS 명명 규칙</summary>
  <ul>
  <br>
    <li>소문자_소문자의 형태로 표기.</li>
    <li>부트스트랩이 아닌 직접 만든 CSS는 맨 앞에 my_를 붙인다. (본인이 커스텀한 CSS는 my_이름 영문이니셜 2자_CSS명을 붙인다)</li>
    <li>동사가 제일 먼저 오고 뒤에 명사가 붙는다.</li>
  <br>
  </ul>
</details>

<br>

# 브랜치 전략
<details>
  <summary>Branch 중심 운영</summary>
  <ul>
  <br>
    <li>master - 실제 올라가는 Branch</li>
    <li>develop - 중간 Branch</li>
    <li>topic - 기능 개발용 Branch</li>
  <br>
  </ul>
</details>
<details>
  <summary>Merge 규칙</summary>
  <ul>
  <br>
    <li>Pull Request 날리기</li>
    <li>코드 작성자가 리뷰를 한 후 Merge</li>
  <br>
  </ul>
</details>
<details>
  <summary>Commit Message</summary>
  <ul>
  <br>
    <li>Feat: 새로운 기능 구현</li>
    <li>Refactor: 원래 있던 코드의 수정(기능도 변경될 경우)</li>
    <li>Style: 원래 있던 코드의 수정(기능이 안 변경될 경우)</li>
    <li>Docs: 문서 변경</li>
    <li>Fix: 오류 수정</li>
    <li>Test: 테스트 코드</li>
    <li>한글로 커밋하기. 커밋은 이해하기 편한 단위로 나누기</li>
  <br>
  </ul>
</details>

<br>

# 📝 기능정리
## Back-End
<details>
  <summary>로그인</summary>
  <ul>
  <br>
    <li>로그인 기능</li>
    <li>아이디 중복 체크 및 Password 중복 검사</li>
    <li>비밀번호 HASH256, Salt 암호화</li>
    <li>로그인 시 아이디 기억하기 기능(Cookie)</li>
    <li>로그인 시 최초 접속 페이지 유지하기 기능</li>
  <br>
  </ul>
</details>
<details>
  <summary>회원가입</summary>
  <ul>
  <br>
    <li>회원가입 기능</li>
    <li>회원가입 시 기술스택 선택 기능</li>
    <li>회원정보 수정 기능</li>
  <br>
  </ul>
</details>
<details>
  <summary>이력서</summary>
  <ul>
  <br>
    <li>이력서 등록 기능</li>
    <li>이력서 수정 기능</li>
    <li>이력서 삭제 기능</li>
    <li>이력서 선택해서 채용공고 지원하기, 지원 취소하기 기능</li>
  <br>
  </ul>
</details>
<details>
  <summary>채용공고</summary>
  <ul>
  <br>
    <li>채용공고 등록 기능</li>
    <li>채용공고 수정 기능</li>
    <li>채용공고 삭제 기능</li>
    <li>채용공고 제목, 내용으로 검색 기능</li>
    <li>채용공고 카테고리별 검색 기능</li>
    <li>마감일자 기능</li>
    <li>공고 등록시 기술스택 선택 기능</li>
  <br>
  </ul>
</details>
<details>
  <summary>북마크</summary>
  <ul>
  <br>
    <li>북마크하기 기능</li>
    <li>북마크 취소하기 기능</li>
  <br>
  </ul>
</details>
<details>
  <summary>기업 마이페이지</summary>
  <ul>
  <br>
    <li>나의 기업 정보</li>
    <li>지원자 현황 보기(지원자 개인정보와 이력서 열람 기능)</li>
    <li>지원자 합격/불합격 처리하기(마이페이지 동기화 기능)</li>
    <li>나를 북마크한 회원 보기</li>
    <li>기술스택 기준 구직자 매칭서비스</li>
  <br>
  </ul>
</details>
<details>
  <summary>개인 마이페이지</summary>
  <ul>
  <br>
    <li>나의 회원 정보</li>
    <li>나의 지원 현황 보기/지원 취소하기 기능</li>
    <li>내가 북마크한 기업 보기</li>
    <li>기술스택 기준 기업 매칭서비스</li>
  <br>
  </ul>
</details>
<details>
  <summary>알림 기능</summary>
  <ul>
  <br>
    <li>SSE 통신으로 서류 발표 알림 기능</li>
  <br>
  </ul>
</details>
<details>
  <summary>Redis</summary>
  <ul>
  <br>
    <li>Redis 세션 DB 구현</li>
  <br>
  </ul>
</details>

<br>

# 📺 유저 시나리오
