<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <%@ include file="../layout/header.jsp" %>
<div class="container py-3 bg-white">
    <div class="p-3">
        <hr>
            <h2 style="text-align: center;">신고 목록</h2>
        <hr>
    </div>
    <div class="container m-3">
        <table class="table table-striped">
            <thead class="my-bg-color-default">
                <tr class="text-center text-black">
                    <th style="width: 10%;">번호</th>
                    <th style="width: 10%;">분류</th>
                    <th style="width: 30%;">제목</th>
                    <th style="width: 15%;">신고자</th>
                    <th style="width: 15%;">피신고자</th>
                    <th style="width: 20%;">작성일</th>
                </tr>
            </thead>
            <tbody>
                <!-- 반복문 -->
                <c:forEach items="${reportList}" var="report" varStatus="status">
                    <tr onclick="location.href='admin/report/${report.id}'">
                        <td class="text-center">${report.id}</td>
                        <td class="text-center">${report.type}</td>
                        <td class="text-center">${report.title}</td>
                        <td class="text-center">${report.reporter}</td>
                        <td class="text-center">${report.reported}</td>
                        <td class="text-center">${report.createdAt}</td>
                        <td><button type="button" id="block-${report.id}" class="badge bg-danger my-border-color-default"
                            onclick="blockReport(${report.id})">관리</span></td>
                    </tr>
                </c:forEach>
                <!-- 반복문종료 -->
            </tbody>
        </table>
    </div>
</div>

    <%@ include file="../layout/footer.jsp" %>