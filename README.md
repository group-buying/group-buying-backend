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

[![youtube](http://img.youtube.com/vi/Gd66Bzkedrg/0.jpg)](https://www.youtube.com/watch?v=Gd66Bzkedrg&t=145s)

<br>

# 발표자료

[파이널프로젝트_1조.pdf](https://docs.google.com/presentation/d/1ZwtBliqMjhPp73ujDBZ4X2SpTJdiNdd6/edit?usp=share_link&ouid=112404147420761909864&rtpof=true&sd=true)

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

# 아키텍처

![erd](https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/c516be4b-474b-41df-a871-29b3d23bd6b9)

<br>

# ERD-Diagram

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
  <summary>REST API 서버 구축</summary>
  <ul>
  <br>
    <li>URL Rules(명사, 복수형을 통한 리소스 식별)</li>
    <li>Content-Type JSON</li>
    <li>Use HTTP methods(POST, GET, PUT, DELETE)</li>
    <li>Use HTTP status(HTTP status로 상태 에러 식별)</li>
  <br>
  </ul>
</details>
<details>
  <summary>Spring Security</summary>
  <ul>
  <br>
    <li>인증</li>
    <li>인가</li>
    <li>보안</li>
    <li>JWT 커스텀 필터 적용</li>
  <br>
  </ul>
</details>
<details>
  <summary>JWT</summary>
  <ul>
  <br>
    <li>Stateless 서버 구축</li>
  <br>
  </ul>
</details>
<details>
  <summary>OAuth 2.0</summary>
  <ul>
  <br>
    <li>네이버 로그인 구현</li>
  <br>
  </ul>
</details>
<details>
  <summary>부트페이 API</summary>
  <ul>
  <br>
    <li>PG 전자 결제 서비스 구현</li>
  <br>
  </ul>
</details>
<details>
  <summary>Sentry</summary>
  <ul>
  <br>
    <li>에러 트래킹 및 모니터링</li>
  <br>
  </ul>
</details>
<details>
  <summary>AWS S3</summary>
  <ul>
  <br>
    <li>이미지 서버 구축</li>
    <li>미디어 스트리밍</li>
    <li>데이터 백업 및 복원</li>
  <br>
  </ul>
</details>
<details>
  <summary>Spring Rest Docs</summary>
  <ul>
  <br>
    <li>API 문서 자동화</li>
  <br>
  </ul>
</details>
<details>
  <summary>CI/CD</summary>
  <ul>
  <br>
    <li>AWS Elastic Beanstalk기반 CI/CD Blue-Green 무중단 배포</li>
  <br>
  </ul>
</details>

<br>

# 유저 시나리오
<img width="900" alt="image" src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/05bb65dc-7875-45bd-9d88-6118f5652fc2">

<br>

# DEMO
<table style="border: 2px;">
<tr>
  <td align=center>스플래시 화면</td>
  <td align=center>회원가입</td>
</tr>
<tr>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/2a01cb76-fa09-47a9-83c3-9b75258a28a4"  width="256" height="455"/></td>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/32d444cc-1615-43a7-a21c-3aac5c56e724"  width="256" height="455"/></td>
</tr>
<tr>
  <td align=center>네이버 로그인</td>
  <td align=center>게시글 작성</td>
</tr>
<tr>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/d4af0bc4-0558-4e20-bb10-7e7e1cf0be1e"  width="256" height="455"/></td>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/18f2a282-4b58-4107-ab4f-b62f60c5cdc9"  width="256" height="455"/></td>
</tr>
<tr>
  <td align=center>계좌등록</td>
  <td align=center>위치기반 게시글</td>
</tr>
<tr>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/e393137f-3017-4928-a205-a2682c1ed827"  width="256" height="455"/></td>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/339d7e08-0a49-4ffe-9d1e-d7f86a0b7f44"  width="256" height="455"/></td>
</tr>
<tr>
  <td align=center>참가하기</td>
  <td align=center>참가자 결정</td>
</tr>
<tr>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/1e2d50b4-2134-4fea-96fc-d21f573944d2"  width="256" height="455"/></td>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/54e5adab-37ed-49c6-940d-4cee331cd41f"  width="256" height="455"/></td>
</tr>
<tr>
  <td align=center>채팅</td>
  <td align=center>결제</td>
</tr>
<tr>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/498bc465-ff86-4063-92cf-e57981c6b254"  width="256" height="455"/></td>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/1549f700-3876-4626-9964-46bbcc349432"  width="256" height="455"/></td>
</tr>
<tr>
  <td align=center>내 지역 수정</td>
  <td align=center>관심게시글</td>
</tr>
<tr>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/9d3400d3-858a-4abf-b90a-dee2218bd867"  width="256" height="455"/></td>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/38d91dec-bfd8-441b-a0fb-18cd83c1f145"  width="256" height="455"/></td>
</tr>
<tr>
  <td align=center>내 카테고리 수정</td>
  <td></td>
</tr>
<tr>
  <td><img src="https://github.com/Hoseok-Seong/Hoseok-Seong/assets/93416157/0695a961-ec39-414e-a513-60fbf138b325"  width="256" height="455"/></td>
  <td></td>
</tr>
</table>
