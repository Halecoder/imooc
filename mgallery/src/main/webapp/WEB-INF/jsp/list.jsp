

<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>油画列表</title>
	<script src="js/jquery-3.4.1.min.js" type="text/javascript"></script>
	<script src="js/sweetalert2.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="css\list.css">
	<script type="text/javascript">
		// 在<head></head>中，增加一个自定义函数；
		function showPreview(previewObj){  //形参数previewObj就是【预览】超链接的对象；previewObj是一个HTML原生的的Dom对象
			// 利用jQuery的${previewObj}：可以将previewObj这个HTML原生的Dom对象进行扩展，让其变成一个JQuery对象；
			// 只有previewObj变成jQuery对象之后，才能利用jQUery的attr()方法，对里面的自定义属性进行提取；
			var preview = $(previewObj).attr("data-preview");
			var pname = $(previewObj).attr("data-pname");
			Swal.fire({     // Swal是SweetAlert组件的核心对象；通过调用.fire()方法，在当前页面弹出一个指定样式的对话框
				title:pname,  // 对话框的标题
				// 对话框主体部分,显示的html文本是什么；这儿使用<img>让其显示图片；
				html:"<img src='"+preview+"' style='width:361px;height:240px'>",
				showCloseButton:true,  // 是否在对话框右上方显示叉号；
				showConfirmButton:false  // 是否显示确认按钮，这儿设置的是不出现；
			})
		}

		function del(delObj){
			var id = $(delObj).attr("data-id");
			var pname = $(delObj).attr("data-pname");
			var preview = $(delObj).attr("data-preview");
			Swal.fire({
				title:"确定要删除["+pname+"]这幅油画吗？",
				html:"<img src='"+preview+"' style='width:361px;height:240px'>",
				showCancelButton:true,
				confirmButtonText:"是",
				cancelButtonText:"否"
			}).then(function(result){
						if(result.value==true){
							$.ajax({
								url:"/management?method=delete&id="+id,
								type:"get",
								dataType:"json",
								success:function(json){
									if(json.result=="ok"){
										window.location.reload();
									}else{
										Swal.fire({
											title:json.result
										})
									}
								}
							})
						}
					}
			)
		}
	</script>
</head>
<body>
<div class="container">
	<fieldset>
		<legend>油画列表</legend>
		<div style="height: 40px">
			<a href="/management?method=show_create" class="btn-button">新增</a>
		</div>
		<!-- 油画列表 -->
		<table cellspacing="0px">
			<thead>
			<tr style="width: 150px;">
				<th style="width: 100px">分类</th>
				<th style="width: 150px;">名称</th>
				<th style="width: 100px;">价格</th>
				<th style="width: 400px">描述</th>
				<th style="width: 100px">操作</th>
			</tr>
			</thead>
			<c:forEach items="${pageModel.pageData}" var="painting">
				<tr>
					<c:choose>
						<c:when test="${painting.category == 1}">
							<td>现实主义</td>
						</c:when>
						<c:when test="${painting.category == 2}">
							<td>抽象主义</td>
						</c:when>
						<c:otherwise>
							<td>未分类别</td>
						</c:otherwise>
					</c:choose>
					<td>${painting.pname}</td>
					<td><fmt:formatNumber pattern="￥0.00" value="${painting.price}"></fmt:formatNumber></td>
					<td>${painting.description }</td>
					<td>
						<a class="oplink" data-preview="${painting.preview}" data-pname="${painting.pname}" href="javascript:void(0)" onclick="showPreview(this)">预览</a>
						<a class="oplink" href="/management?method=show_update&id=${painting.id}">修改</a>
						<a class="oplink" href="javascript:void(0)" data-id="${painting.id}" data-pname="${painting.pname}" data-preview="${painting.preview}" onclick="del(this)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 分页组件 -->
		<ul class="page">
			<li><a href="/management?method=list&p=1">首页</a></li>
			<li><a href="/management?method=list&p=${pageModel.hasPreviousPage?pageModel.page-1:1 }">上页</a></li>
			<c:forEach begin="1" end="${pageModel.totalPages}" var="pno" step="1">
				<li ${pno==pageModel.page?"class='active'":""}><span>
    						<a href="/management?method=list&p=${pno}">
									${pno}
							</a>
    					</span></li>
				<!-- 	<li class='active'><a href="#">1</a></li>
				<li ><a href="#">2</a></li> -->
			</c:forEach>
			<li><a href="/management?method=list&p=${pageModel.hasNextPage?pageModel.page+1:pageModel.totalPages}">下页</a></li>
			<li><a href="/management?method=list&p=${pageModel.totalPages}">尾页</a></li>
		</ul>
	</fieldset>
</div>

</body>
</html>
