<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <%@ include file="../layout/header.jsp" %>
<div class="container py-3 bg-white">
    <div class="p-3">
        <hr>
            <h2 style="text-align: center;">유저 목록</h2>
        <hr>
    </div>
    <div class="container m-3">
        <table class="table table-striped">
            <thead class="my-bg-color-default">
                <tr class="text-center text-black">
                    <th style="width: 10%;">번호</th>
                    <th style="width: 20%;">이름</th>
                    <th style="width: 20%;">등급</th>
                    <th style="width: 20%;">상태</th>
                    <th style="width: 20%;">가입일</th>
                </tr>
            </thead>
            <tbody>
                <!-- 반복문 -->
                <c:forEach items="${userList}" var="user" varStatus="status">
                    <tr onclick="location.href='admin/user/${user.id}'">
                        <td class="text-center">${user.id}</td>
                        <td class="text-center">${user.name}</td>
                        <td class="text-center">${user.ratename}</td>
                        <td class="text-center">${user.status}</td>
                        <td class="text-center">${user.createdAt}</td>
                        <td><button type="button" id="block-${user.id}" class="badge bg-danger my-border-color-default"
                            onclick="blockUser(${user.id})">차단</span></td>
                    </tr>
                </c:forEach>
                <!-- 반복문종료 -->
            </tbody>
        </table>
    </div>
</div>

<script>
function blockUser(id) {
  $.ajax({
    url: "/admin/user",
    type: "put",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify({
      id: id
    }),
    dataType: "json"
    }).done((res) => {
        alert("해당 유저를 차단했습니다.");
        location.reload();
    }).fail((err) => {
        alert(err.responseJSON.message);
    });
}
</script>

    <%@ include file="../layout/footer.jsp" %>