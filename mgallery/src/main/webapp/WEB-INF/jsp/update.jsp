
<%@page contentType="text/html;charset=utf-8"%>
<!-- 修改油画页面 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>作品更新</title>
	<link rel="stylesheet" type="text/css" href="css\create.css">
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/validation.js"></script>
	<script type="text/javascript">
		<!-- 提交前表单校验 -->
		function checkSubmit(){
			var result = true;
			var r1 = checkEmpty("#pname","#errPname");
			var r2 = checkCategory('#category', '#errCategory');
			var r3 = checkPrice('#price', '#errPrice');
			//var r4 = checkFile('#painting', '#errPainting');
			var r4 = null;
			// 如果这个值=1的话，说明前面新上传了一个图片文件
			if($("#isPreviewModified").val() == "1"){
				r4 = checkFile('#painting', '#errPainting');
			}else{
				// 如果没有上传新的文件，r4就=true，这样在客户端就可以省去再检查文件了；
				r4 = true;
			}
			var r5 = checkEmpty('#description', '#errDescription');
			if (r1 && r2 && r3 && r4 && r5){
				return true;
			}else{
				return false;
			}
		}

		// <!--jQUery页面就绪函数，作用是整个html被解析后，来执行这个函数的代码，相当于是一个页面初始化函数；；以前在学习jQuery的时候接触过-->
		$(function(){
			// $("#category"):是jQuery选择器；执行时机：在html被解释完了之后，才会被执行；然后，jQUery是JavaScript这个浏览器脚本语言的小框架，所以这个肯定是在浏览器上执行的；
			// ${painting.category}:是el表达式；执行时机：JSP渲染的时候，在服务器端生成的；
			// jQuery的val()方法：可以根据传入的值，根据传入的值，自动把与之匹配的选项进行选中；
			$("#category").val(${painting.category});
		})

		//
		function selectPreview(){
			checkFile("#painting","#errPainting");// 调用validation.js的方法，检测文件
			$("#preview").hide();//当选择一个新的图片后，需要把圆形的那个显示原先旧的图片的<img>给隐藏，这个是出于用户体验的优化
			$("#isPreviewModified").val(1);//即如果选择新的文件后，将这个的值设置1
		}
	</script>
</head>
<body>
<div class="container">
	<fieldset>
		<legend>作品更新</legend>
		<form action="[这里写更新URL]" method="post"
			  autocomplete="off" enctype="multipart/form-data"
			  onsubmit = "return checkSubmit()">
			<ul class="ulform">
				<li>
					<span>油画名称</span>
					<span id="errPname"></span>
					<input id="pname" name="pname" onblur="checkEmpty('#pname','#errPname')" value="${painting.pname}"/>
				</li>
				<li>
					<span>油画类型</span>
					<span id="errCategory"></span>
					<select id="category" name="category" onchange="checkCategory('#category','#errCategory')">
						<option value="-1">请选择油画类型</option>
						<option value="1">现实主义</option>
						<option value="2">抽象主义</option>
					</select>
				</li>
				<li>
					<span>油画价格</span>
					<span id="errPrice"></span>
					<input id="price" name="price" onblur="checkPrice('#price','#errPrice')" value="${painting.price}"/>
				</li>
				<li>
					<span>作品预览</span>
					<input type="hidden" id="isPreviewModified" name="isPreviewModified" value="0">
					<span id="errPainting"></span><br/>
					<img id="preview" src="${painting.preview }" style="width:361px;height:240px"/><br/>
					<input id="painting" name="painting" type="file" style="padding-left:0px;" accept="image/*" onchange="selectPreview()"/>
				</li>

				<li>
					<span>详细描述</span>
					<span id="errDescription"></span>
					<textarea
							id="description" name="description"
							onblur="checkEmpty('#description','#errDescription')"
					>${painting.description }</textarea>
				</li>
				<li style="text-align: center;">
					<input type="hidden" id="id" name="id" value="${paintint.id}">
					<button type="submit" class="btn-button">提交表单</button>
				</li>
			</ul>
		</form>
	</fieldset>
</div>

</body>
</html>
    