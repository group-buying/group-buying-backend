<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <%@ include file="../layout/header.jsp" %>
<div class="container py-3 bg-white">
    <div class="p-3">
        <hr>
            <h2 style="text-align: center;">결제 목록</h2>
        <hr>
    </div>
    <div class="container m-3">
        <table class="table table-striped">
            <thead class="my-bg-color-default">
                <tr class="text-center text-black">
                    <th style="width: 10%;">번호</th>
                    <th style="width: 10%;">유저이름</th>
                    <th style="width: 20%;">공고이름</th>
                    <th style="width: 10%;">결제수량</th>
                    <th style="width: 10%;">결제금액</th>
                    <th style="width: 20%;">결제일</th>
                </tr>
            </thead>
            <tbody>
                <!-- 반복문 -->
                <c:forEach items="${paymentList}" var="payment" varStatus="status">
                    <tr onclick="location.href='admin/payment/${payment.id}'">
                        <td class="text-center">${payment.id}</td>
                        <td class="text-center">${payment.username}</td>
                        <td class="text-center">${payment.boardname}</td>
                        <td class="text-center">${payment.qty}</td>
                        <td class="text-center">${payment.price}</td>
                        <td class="text-center">${payment.createdAt}</td>
                        <td><button type="button" id="cancel-${payment.id}" class="badge bg-danger my-border-color-default"
                            onclick="cancelPayment(${payment.id})">취소</span></td>
                    </tr>
                </c:forEach>
                <!-- 반복문종료 -->
            </tbody>
        </table>
    </div>
</div>

<script>
function cancelPayment(id) {
  $.ajax({
    url: "/admin/payment",
    type: "put",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify({
      id: id
    }),
    dataType: "json"
    }).done((res) => {
        alert("해당 결제를 취소했습니다.");
        location.reload();
    }).fail((err) => {
        alert(err.responseJSON.message);
    });
}
</script>

    <%@ include file="../layout/footer.jsp" %>