<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Danh sách project</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href='<%= request.getContextPath() %>/static/css/style.css'>
</head>

<body>

    <div class="d-flex justify-content-between">
        <!-- SIDE BAR -->
        <div id="side-bar">
            <ul class="list-group rounded-0">
                <li class="dashboard">DASHBOARD</li>
                <li>
                    <a href="<%= request.getContextPath() %>/home">
                        <i class="fa fa-home mr-2"></i> Trang chủ
                    </a>
                </li>
                <li>
                    <a href="<%= request.getContextPath() %>/project">
                        <i class="fa fa-tasks mr-2"></i> Quản lý dự án
                    </a>
                </li>
                <c:if test="${USER_LOGIN.roleId==1||USER_LOGIN.roleId==2 }">
                <li>
                    <a href="<%= request.getContextPath() %>/user">
                        <i class="fa fa-user mr-2"></i> Quản lý thành viên
                    </a>
                </li>
                </c:if>
                <c:if test="${USER_LOGIN.roleId==1 }">
                <li>
                    <a href="<%= request.getContextPath() %>/role">
                        <i class="fa fa-book mr-2"></i> Quản lý quyền
                    </a>
                </li>
                </c:if>
            </ul>
        </div>

        <div id="admin-wrapper">
            <!-- HEADER -->
            <nav class="navbar navbar-expand-sm navbar-light bg-light w-100">
                <a class="navbar-brand" onclick="return openOrCloseSideBar();" ><i class="fa fa-align-justify"></i></a>
                <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse"
                    data-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false"
                    aria-label="Toggle navigation"></button>
                <div class="collapse navbar-collapse" id="collapsibleNavId">
                    <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="dropdownId"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								${ sessionScope.USER_LOGIN.fullname } </a>
							<div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownId">
								<a class="dropdown-item" href="<%= request.getContextPath() %>/user/edit?id=${sessionScope.USER_LOGIN.userId}">Chỉnh sửa thông tin cá nhân</a>
								<a class="dropdown-item" href="<%= request.getContextPath() %>/logout">Thoát</a>
							</div></li>
					</ul>
                </div>
            </nav>

            <!-- CONTENT -->
            <section id="admin-content" class="p-3">
                <h3 class="mb-3">Danh sách Project</h3>
                <div class="row">
                    <div class="col-md-8">
                        <c:if test="${USER_LOGIN.roleId==1||USER_LOGIN.roleId==2 }">
                        	<a href="<%=request.getContextPath() %>/project/add" class="btn btn-primary">Thêm mới</a>
                        </c:if>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Tìm kiếm...">
                            <div class="input-group-append">
                                <span class="input-group-text" id="basic-addon2"><i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-bordered table-hover mt-3">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Tên Project</th>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                            <c:if test="${USER_LOGIN.roleId==1||USER_LOGIN.roleId==2 }">
                            <th>#</th>
                            </c:if>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${ projectList }" var="item">
	                        <tr>
	                            <td>${ item.projectId }</td>
	                            <td><a href="<%= request.getContextPath() %>/task?idP=${item.projectId}">${ item.projectName }</a></td>
	                            <td>${ item.startDate }</td>
	                            <td>${ item.endDate }</td>
	                            <c:if test="${USER_LOGIN.roleId==1||USER_LOGIN.roleId==2 }">
	                            <td>
	                            	
	                                <a href="<%= request.getContextPath() %>/project/edit?id=${item.projectId}" class="btn btn-sm btn-info">
	                                    <i class="fa fa-pencil-square-o"></i>
	                                </a>
	                                <a href="<%= request.getContextPath() %>/project/delete?id=${item.projectId}" class="btn btn-sm btn-danger">
	                                    <i class="fa fa-trash-o"></i>
	                                </a>
	                            </td>
	                            </c:if>
	                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
        </div>
    </div>
    <script>
	 	var clicked = true;
    	function openOrCloseSideBar() {
    		if (clicked){
    			document.getElementById("side-bar").style.width = "250px";
        		document.getElementById("admin-wrapper").style.marginLeft = "250px";
        		clicked = false;
    		}
    		else{
    			document.getElementById("side-bar").style.width = "0";
        		document.getElementById("admin-wrapper").style.marginLeft= "0";
        		clicked = true
    		}
    	}
    	
    </script>
    <script src="<%= request.getContextPath() %>/static/js/jquery.slim.min.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/popper.min.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/bootstrap.min.js"></script>
</body>

</html>