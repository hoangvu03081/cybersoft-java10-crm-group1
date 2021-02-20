<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Chỉnh sửa task</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bootstrap.min.css">
<link rel="stylesheet" href='<%=request.getContextPath()%>/static/css/style.css'>
</head>

<body>

	<div class="d-flex justify-content-between">
		<!-- SIDE BAR -->
		<div id="side-bar">
			<div class="logo">ADMIN PAGE</div>
			<ul class="list-group rounded-0">
				<li class="dashboard">DASHBOARD</li>
				<li>
					<a href="user-list.html">
						<i class="fa fa-user mr-2"></i>
						Quản lý thành viên
					</a>
				</li>
				<li>
					<a href="role-list.html">
						<i class="fa fa-book mr-2"></i>
						Quản lý quyền
					</a>
				</li>
				<li>
					<a href="#">
						<i class="fa fa-cogs mr-2"></i>
						Cấu hình hệ thống
					</a>
				</li>
				<li>
					<a href="#">
						<i class="fa fa-slack mr-2"></i>
						Thông tin khác
					</a>
				</li>
			</ul>
		</div>
		<!-- END SIDE BAR -->

		<div id="admin-wrapper">
			<!-- HEADER -->
			<nav class="navbar navbar-expand-sm navbar-light bg-light w-100">
				<a class="navbar-brand" href="#">
					<i class="fa fa-align-justify"></i>
				</a>
				<button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse"
					data-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false"
					aria-label="Toggle navigation"></button>
				<div class="collapse navbar-collapse" id="collapsibleNavId">
					<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="dropdownId" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> Cybersoft </a>
							<div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownId">
								<a class="dropdown-item" href="">Thông tin cá nhân</a>
								<a class="dropdown-item" href="#">Cài đặt</a>
								<a class="dropdown-item" href="#">Thoát</a>
							</div>
						</li>
					</ul>
				</div>
			</nav>
			<!-- END HEADER -->
		
	
			<!-- CONTENT -->
			<section id="admin-content" class="p-3">
				<h3 class="mb-4 text-center">Thêm mới quyền</h3>
				<p class="text-center text-danger">${ message }</p>
				<form method="post"
					action="<%=request.getContextPath()%>/task/edit?idP=<%=request.getAttribute("idP")%>">
					<div class="row">
						<div class="col-md-6 m-auto">
							<div class="form-group">
								<label>Tên task</label>
								<input type="text" name="taskName" class="form-control" value="${task.taskName }" required />
							</div>
							<div class="form-group">
								<label>Ngày bắt đầu</label>
								<input type="date" id="start" name="startDate" class="form-control"
									value="${task.startDate }" required />
							</div>
							<div class="form-group">
								<label>Ngày kết thúc</label>
								<input type="date" id="end" name="endDate" class="form-control" value="${task.endDate }"
									required />
							</div>
							<div class="form-group">
								<label>Status</label>
								<select class="form-control" name="statusId">
									<option value="1" <c:if test="${task.statusId==1 }">selected="selected"</c:if>>Done</option>
									<option value="2" <c:if test="${task.statusId==2 }">selected="selected"</c:if>>In
										progress</option>
								</select>

								</select>
							</div>
							<div class="form-group">
								<label>ID người dùng:</label>
								<input list="users" name="userId" class="form-control" required>

								<datalist id="users">
									<c:forEach items="${userList}" var="item">
										<option value="${item.userId }"
											<c:if test="${task.userId==item.userId }">selected="selected"</c:if>>${item.fullname}</option>
									</c:forEach>
									<option value="No name">
								</datalist>
							</div>

							<div class="form-group">
								<input type="hidden" name="taskId" value="${task.taskId }" />
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-success">Lưu lại</button>
								<a class="btn btn-secondary"
									href="<%=request.getContextPath()%>/task?idP=<%=request.getAttribute("idP")%>">Quay lại</a>
							</div>
						</div>
					</div>
				</form>
			</section>
		</div>
	</div>



	<script src="<%=request.getContextPath()%>/static/js/jquery.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>


</body>



</html>