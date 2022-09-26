<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- 引入wangEditor -->
    <script src="/resources/wangEditor.min.js"></script>
</head>
<body>
<button id="btnRead">读取内容</button>
<button id="btnWrite">写入方法</button>
<div id="divEditor" style="width: 800px;height: 600px" ></div>
<script>
    var E = window.wangEditor;
    var editor = new E("#divEditor");//完成富文本编辑器的初始化
    editor.create();//创建富文本编辑器，显示在页面上

    document.getElementById("btnRead").onclick = function () {
        var content = editor.txt.html();//获取编辑器中，现有的HTML内容；
        alert(content);
    };

    document.getElementById("btnWrite").onclick = function () {
        var content = "<li style='color: red'>向富文本编辑器中添加的内容，<b>然后这儿是加粗的内容</b></li>";
        editor.txt.html(content);
    };
</script>
</body>
</html>