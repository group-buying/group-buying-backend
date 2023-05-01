<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <%@ include file="../layout/header.jsp" %>
<div class="container py-3 bg-white">
    <div class="p-3">
        <hr>
            <h2 style="text-align: center;">공고 목록</h2>
        <hr>
    </div>
    <div class="container m-3">
        <table class="table table-striped">
            <thead class="my-bg-color-default">
                <tr class="text-center text-black">
                    <th style="width: 10%;">번호</th>
                    <th style="width: 20%;">제목</th>
                    <th style="width: 20%;">작성자</th>
                    <th style="width: 20%;">위치</th>
                    <th style="width: 20%;">작성일</th>
                </tr>
            </thead>
            <tbody>
                <!-- 반복문 -->
                <c:forEach items="${boardList}" var="board" varStatus="status">
                    <tr onclick="location.href='admin/board/${board.id}'">
                        <td class="text-center">${board.id}</td>
                        <td class="text-center">${board.title}</td>
                        <td class="text-center">${board.organizer}</td>
                        <td class="text-center">${board.location}</td>
                        <td class="text-center">${board.createdAt}</td>
                        <td><button type="button" id="block-${board.id}" class="badge bg-danger my-border-color-default"
                            onclick="blockBoard(${board.id})">비활성화</span></td>
                    </tr>
                </c:forEach>
                <!-- 반복문종료 -->
            </tbody>
        </table>
    </div>
</div>

<script>
function blockBoard(id) {
  $.ajax({
    url: "/admin/board",
    type: "put",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify({
      id: id
    }),
    dataType: "json"
    }).done((res) => {
        alert("해당 게시글을 차단했습니다.");
        location.reload();
    }).fail((err) => {
        alert(err.responseJSON.message);
    });
}
</script>

    <%@ include file="../layout/footer.jsp" %>