<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <%@ include file="../layout/header.jsp" %>

<div class="container py-3 bg-white">
    <div class="p-3">
        <hr>
            <h2 style="text-align: center;">카테고리 목록</h2>
        <hr>
    </div>
    <div class="container m-3">
        <table class="table table-striped">
            <thead class="my-bg-color-default">
                <tr class="text-center text-black">
                    <th style="width: 20%;">번호</th>
                    <th style="width: 40%;">이름</th>
                    <th style="width: 20%;"></th>
                </tr>
            </thead>
            <tbody>
                <!-- 반복문 -->
                <c:forEach items="${categoryList}" var="category" varStatus="status">
                    <tr>
                        <td class="text-center">${category.id}</td>
                        <td class="text-center">${category.name}</td>
                        <td class="text-center"></td>
                        <td><button type="button" id="delete-${category.id}" class="badge bg-danger my-border-color-default"
                                onclick="delete(${category.id})">삭제</span></td>
                    </tr>
                </c:forEach>
                <!-- 반복문종료 -->
                    <tr>
                        <td></td>
                        <td class="text-center"><input id="categoryname" type="text" placeholder="이름"/></td>
                        <td></td>
                        <td><button type="button" id="add" class="badge bg-success my-border-color-default"
                                onclick="add()">추가</span></td>
                    </tr>
            </tbody>
        </table>
    </div>
</div>

<script>

function add() {
    var categoryName = $('#categoryname').val();
  $.ajax({
    url: "/admin/category",
    type: "PUT",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify({
      name: categoryName
    }),
    dataType: "json"
    }).done((res) => {
        alert("카테고리를 추가했습니다.");
        location.reload();
    }).fail((err) => {
        alert(err.responseJSON.message);
    });
}

function delete(id) {
  $.ajax({
    url: "/admin/category",
    type: "DELETE",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify({
      id: id
    }),
    dataType: "json"
    }).done((res) => {
        alert("카테고리를 삭제했습니다.");
        location.reload();
    }).fail((err) => {
        alert(err.responseJSON.message);
    });
}
</script>

 <%@ include file="../layout/footer.jsp" %>