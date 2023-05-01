<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <%@ include file="../layout/header.jsp" %>

<div style="height: 100px;"></div>
<div class="container py-3 bg-white">
    <hr>
        <h2 style="text-align: center;">신고 처리</h2>
    <hr>
    <div class="mt-3">
        <div class="row">
            <div class=" col-sm-6 p-3">
                <div class="border border-primary rounded-1 m-3" style="height: 100%;">
                    <div class="col-12 p-3">
                            <div class="col-sm-5 m-3">
                                <h3>
                                    ${report.reportTitle}
                                </h3>
                            </div>
                    </div>
                    <div class="row p-3">
                        <div class="col-sm-5 m-3 p-3">
                            <h3 class="mt-3">신고자</h3>
                            <hr class="bg-primary">
                            <ul class="nav nav-pills flex-column">
                                <li class="nav-item">
                                    ${report.reporter}
                                </li>
                            </ul>
                        </div>
                        <div class="col-sm-5 m-3 p-3">
                            <h3 class="mt-3">피신고자</h3>
                            <hr class="bg-primary">
                                <ul class="nav nav-pills flex-column">
                                <li class="nav-item">
                                    ${report.reported}
                                </li>
                            </ul>
                        </div>
                        </div>
                    <div class="col-10 m-3 p-3">
                        <h3 class="mt-3">신고 내용</h3>
                        <hr class="bg-primary">
                        <ul class="nav nav-pills flex-column">
                            <li class="nav-item">
                                ${report.reportContent}
                            </li>
                        </ul>
                    </div>
                    <div class="col-10 m-3 p-3">
                        <h3 class="mt-3">신고 일자</h3>
                        <hr class="bg-primary">
                        <ul class="nav nav-pills flex-column">
                            <li class="nav-item">
                                ${report.reportCreatedAt}
                            </li>
                        </ul>
                    </div>
                    <hr class="d-sm-none">
                </div>
            </div>
            <div class="col-sm-6 p-3 ">
                <div class="p-3 m-3 border border-primary rounded-1">
                    <h2 class="p-3 text-center"><b>피신고 게시글</b></h2>
                    <hr class="bg-primary">
                    <h5>
                        <c:choose>
                            <c:when test="${report.boardId == null}">
                                게시글의 신고가 아닙니다.
                            </c:when>
                            <c:otherwise>
                                작성자: ${report.reported}
                            </c:otherwise>
                        </c:choose>
                    </h5>
                    <p>
                        <c:choose>
                            <c:when test="${report.boardContent == null}">
                            </c:when>
                            <c:otherwise>
                                ${report.boardImg}
                                ${report.boardContent}
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
                   <c:choose>
                        <c:when test="${report.boardId == null}">
                            <div class="btn-group col-sm-12">
                                <button type="button" class="btn btn-outline-success m-3 p-3" onclick="processed(${report.id})">처리완료</button>
                                <button type="button" class="btn btn-outline-danger m-3 p-3" onclick="blockUser(${report.reportedId})">피신고 유저 블록</button>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="btn-group col-sm-12">
                                <button type="button" class="btn btn-outline-success m-3 p-3" onclick="processed(${report.id})">처리완료</button>
                                <button type="button" class="btn btn-outline-danger m-3 p-3" onclick="blockUser(${report.reportedId})">피신고 유저 블록</button>
                                <button type="button" class="btn btn-outline-danger m-3 p-3" onclick="blockBoard(${report.boardId})">게시글 블록</button>
                            </div>
                        </c:otherwise>
                    </c:choose>
            </div>
        </div>
    </div>
</div>

<script>
function processed(id) {
  $.ajax({
    url: "/admin/report",
    type: "put",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify({
      id: id
    }),
    dataType: "json"
    }).done((res) => {
        alert("해당 신고를 처리마감했습니다.");
        location.reload();
    }).fail((err) => {
        alert(err.responseJSON.message);
    });
}

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