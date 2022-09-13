//轮播图特效
//IIFE
(function(){
    // 得到元素
    var carousel_list = document.getElementById("carousel_list");
    var left_btn = document.getElementById("left_btn");
    var right_btn = document.getElementById("right_btn");

    //克隆第一张li 孤儿节点
    var clone_li = carousel_list.firstElementChild.cloneNode(true);
    // 需要上树
    carousel_list.appendChild(clone_li);
    // 右按钮添加监听

})()