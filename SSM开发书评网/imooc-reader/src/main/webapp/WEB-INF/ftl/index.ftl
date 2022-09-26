<!DOCTYPE html>
<html lang="en"><head>
    <meta name="referrer" content="no-referrer" />
    <meta charset="UTF-8">
    <title>慕课书评网</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="./resources/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="./resources/raty/lib/jquery.raty.css">
    <script src="./resources/jquery.3.3.1.min.js"></script>
    <script src="./resources/bootstrap/bootstrap.min.js"></script>
    <script src="./resources/art-template.js"></script>
    <script src="./resources/raty/lib/jquery.raty.js"></script>

    <style>
        .highlight {
            color: red !important;
        }
        a:active{
            text-decoration: none!important;
        }
    </style>


    <style>
        .container {
            padding: 0px;
            margin: 0px;
        }

        .row {
            padding: 0px;
            margin: 0px;
        }

        .col- * {
            padding: 0px;
        }
    </style>
    <!--定义模板-->
    <script type="text/html" id="tpl">
        <a href="/book/{{bookId}}" style="color: inherit">
            <div class="row mt-2 book">
                <div class="col-4 mb-2 pr-2">
                    <img class="img-fluid" src="{{cover}}">
                </div>
                <div class="col-8  mb-2 pl-0">
                    <h5 class="text-truncate">{{bookName}}</h5>
                    <div class="mb-2 bg-light small  p-2 w-100 text-truncate">{{author}}</div>
                    <div class="mb-2 w-100">{{subTitle}}</div>
                    <p>
                        <span class="stars" data-score="{{evaluationScore}}" title="gorgeous"></span>
                        <span class="mt-2 ml-2">{{evaluationScore}}</span>
                        <span class="mt-2 ml-2">{{evaluationQuantity}}</span>
                    </p>
                </div>
            </div>
        </a>
        <hr>
    </script>
    <script>
        $.fn.raty.defaults.path = "./resources/raty/lib/images"

        //loadMore():加载更多数据；
        //isReset参数设置为true时：代表查询第一个数据；否则按nextPage查询后续页；
        function loadMore(isReset) {
            if (isReset == true) {
                $("#bookList").html("");
                $("#nextPage").val(1);
            }
            var nextPage = $("#nextPage").val();//首先获取下一页页号
            var categoryId = $("#categoryId").val();//获取保存在隐藏域中的，图书分类编号；
            var order = $("#order").val();//获取保存在隐藏域中的，按热度|按评分的排序规则；
            $.ajax({
                "url" : "/books",
                "data" : {p:nextPage,categoryId:categoryId,order:order},
                "type" : "get",
                "dataType" :"json",
                success : function (json) {
                    console.log(json);
                    var list = json.records;
                    for (var i = 0; i < list.length; i++) {
                        var book = json.records[i];
                        // var html = "<li>" + book.bookName+ "</li>";
                        var html = template("tpl" , book);
                        console.log(html);
                        $("#bookList").append(html);
                    }
                    $(".stars").raty({readOnly:true});//显示星型评价组件

                    if (json.current < json.pages) { //如果当前页小于总页数，说明还有数据：
                        $("#nextPage").val(parseInt(json.current) + 1);//把下一页的隐藏域的值，增加1
                        $("#btnMore").show();//【点击显示更多按钮】按钮应该继续显示；
                        $("#divNoMore").hide();//【没有其他数据了】不应该显示；
                    } else {   //否则，如果当前页不小于总页数，说明已经是最后一页了：
                        $("#btnMore").hide();//【点击显示更多按钮】按钮不应该继续显示了；
                        $("#divNoMore").show();//【没有其他数据了】应该显示;
                    }
                }
            })
        }
        //这个页面就绪函数，用于首页加载时，默认显示第一页数据；
        $(function () {
            loadMore(true);
            // $.ajax({
            //     "url" : "/books",
            //     "data" : {p:1},
            //     "type" : "get",
            //     "dataType" :"json",
            //     success : function (json) {
            //         console.log(json);
            //         var list = json.records;
            //         for (var i = 0; i < list.length; i++) {
            //             var book = json.records[i];
            //             // var html = "<li>" + book.bookName+ "</li>";
            //             var html = template("tpl" , book);
            //             console.log(html);
            //             $("#bookList").append(html);
            //         }
            //         $(".stars").raty({readOnly:true});//显示星型评价组件
            //     }
            // })
        })

        //这个页面就绪函数，用于绑定【点击加载更多】按钮的点击事件
        $(function () {
            //这个函数，用于绑定【点击加载更多】按钮的点击事件
            $("#btnMore").click(function () {  //click()中增加一个匿名函数，用于事件处理
                loadMore();
            })

            //这个函数，用于设置分类超链接，高亮与否的设置
            $(".category").click(function () {
                $(".category").removeClass("highlight");//先移除所有的category分类超链接上的“highlight”高亮css；
                $(".category").addClass("text-black-50")//然后，将所有的category分类标签分类超链接的颜色，设置为灰色；
                $(this).addClass("highlight");//捕获当前点击的分类超链接，并添加上“highlight”高亮css；
                var categoryId = $(this).data("category");
                $("#categoryId").val(categoryId);
                loadMore(true);//每次点击图书类别后，则全部重新查询
            })
            //这个函数，用于设置按热度|按评分，高亮与否的设置
            $(".order").click(function () {
                $(".order").removeClass("highlight");//先移除所有的category分类超链接上的“highlight”高亮css；
                $(".order").addClass("text-black-50")//然后，将所有的category分类标签分类超链接的颜色，设置为灰色；
                $(this).addClass("highlight");//捕获当前点击的分类超链接，并添加上“highlight”高亮css；
                var order = $(this).data("order");
                $("#order").val(order);
                loadMore(true);//每次点击按热度|按评分后，则全部重新查询
            })
        })
    </script>

</head>
<body>
<div class="container">
    <nav class="navbar navbar-light bg-white shadow mr-auto">
        <ul class="nav">
            <li class="nav-item">
                <a href="/">
                    <img src="https://m.imooc.com/static/wap/static/common/img/logo2.png" class="mt-1" style="width: 100px">
                </a>
            </li>

        </ul>
        <#if loginMember??>
            <h6 class="mt-1">
                <img style="width: 2rem;margin-top: -5px" class="mr-1" src="./images/user_icon.png">${loginMember.nickname}
            </h6>
        <#else>
            <a href="/login.html" class="btn btn-light btn-sm">
                <img style="width: 2rem;margin-top: -5px" class="mr-1" src="./images/user_icon.png">登录
            </a>
        </#if>

    </nav>
    <div class="row mt-2">


        <div class="col-8 mt-2">
            <h4>热评好书推荐</h4>
        </div>

        <div class="col-8 mt-2">
            <span data-category="-1" style="cursor: pointer" class="highlight  font-weight-bold category">全部</span>
            |
            <#list categoryList as category>
                <a style="cursor: pointer" data-category="${category.categoryId}" class="text-black-50 font-weight-bold category">${category.categoryName}</a>
                <#if category_has_next>
                    |
                </#if>
            </#list>
        </div>

        <div class="col-8 mt-2">
            <span data-order="quantity" style="cursor: pointer" class="order highlight  font-weight-bold mr-3">按热度</span>

            <span data-order="score" style="cursor: pointer" class="order text-black-50 mr-3 font-weight-bold">按评分</span>
        </div>
    </div>
    <div class="d-none">
        <input type="hidden" id="nextPage" value="2">
        <input type="hidden" id="categoryId" value="-1">
        <input type="hidden" id="order" value="quantity">
    </div>

    <div id="bookList">
        <!--这个div，是显示图书的容器-->
    </div>
    <button type="button" id="btnMore" data-next-page="1" class="mb-5 btn btn-outline-primary btn-lg btn-block">
        点击加载更多...
    </button>
    <div id="divNoMore" class="text-center text-black-50 mb-5" style="display: none;">没有其他数据了</div>
</div>

</body></html>