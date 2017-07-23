<%--
  Created by IntelliJ IDEA.
  User: 22670
  Date: 2017/7/23
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        <small>员工管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>基础信息管理</a></li>
        <li class="active">员工管理</li>
    </ol>
</section>
<!-- 这是内容 -->
<section class="content">
    <div class="col-md-12">
        <div class="box">
            <div class="box-header with-border">
                <h3 class="box-title">Bordered Table</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <table class="table table-bordered">
                    <tbody>
                    <tr>
                        <th style="width: 10px">姓名</th>
                        <th>性别</th>
                        <th>学历</th>
                        <th>毕业学校</th>
                        <th>电话号码</th>
                        <th>邮箱</th>
                        <th>地址</th>
                        <th>创建时间</th>
                        <th style="width: 40px">操作</th>
                    </tr>
                    <c:if test="${not empty employeeList}">
                        <c:forEach var="employee" items="${employeeList}">
                            <tr>
                                <td>${employee.name}</td>
                                <td>${employee.sex}</td>
                                <td>${employee.education}</td>
                                <td>${employee.graduationSchool}</td>
                                <td>${employee.phone}</td>
                                <td>${employee.email}</td>
                                <td>${employee.address}</td>
                                <td><fmt:formatDate value="${employee.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <td>ceshi</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <!-- /.box-body -->
            <c:if test="${not empty page.totalCount}">
                <div class="box-footer clearfix">
                    <ul id="page-btn-container" class="pagination pagination-sm no-margin pull-right"
                        data-totalCount="${page.totalCount}" data-page="${page.page}" data-pageSize="${page.pageSize}"
                    update-url="${pageContext.request.contextPath}/view/employee/">
                    </ul>
                </div>
            </c:if>
        </div>
        <!-- /.box -->
    </div>
</section>
<script src="../../js/page.js"></script>
