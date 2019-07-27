<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/my.js">
	
</script>
<script type="text/javascript">
    function searchName() {
        //得到搜索框对象
        var searchElement = document.getElementById("name");
        //得到div元素
        var div = document.getElementById("content");

        searchElement.onkeyup = function () {
            //获取文本框的值
			var name = this.value;
			if (name == "") {
			    div.style.display = "none";
			    return;
            }

			//获取xhr对象
			var xhr = getXMLHttpRequest();

            //处理结果
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        var ss = eval("(" + xhr.responseText + ")");
						var childDivs = "";

						for (var i = 0; i < ss.length; i++) {
							childDivs += "<div onmouseover = 'changeBackground_over(this)' " +
									"onmouseout='changeBackground_out(this)'" +
									"onclick='writerText(this)'>"
									
									+ ss[i] +"</div>";
						}

						div.innerHTML = childDivs;
                        div.style.display = "block";
					}
				}
			}
			xhr.open("get","${pageContext.request.contextPath}/SearchBookAJAXServlet?name="+name+"&time="+new Date().getTime());
			xhr.send(null);

		}
	}

	function changeBackground_over(div) {
		div.style.backgroundColor = "gray";
	}

	function changeBackground_out(div) {
		div.style.backgroundColor = "";
	}
	
	function writerText(div) {
		document.getElementById("name").value = div.innerHTML;
		div.parentNode.style.display = "none"; // 隐藏contentdiv
	}
</script>

<div id="divmenu">
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=文学">文学</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=生活">生活</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=计算机">计算机</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=外语">外语</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=经营">经管</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=励志">励志</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=社科">社科</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=学术">学术</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=少儿">少儿</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=艺术">艺术</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=原版">原版</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=科技">科技</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=考试">考试</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=生活百科">生活百科</a>
	<a href="${pageContext.request.contextPath}/showProductByPage"
		style="color:#FFFF00">全部商品目录</a>
</div>
<div id="divsearch">
	<form action="${pageContext.request.contextPath}/findProductBySearch"
		method="post">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td style="text-align:right; padding-right:220px">
				Search <input
					type="text" name="name" class="inputtable" onkeyup="searchName()"
					id="name" /> 
					<input type="image" src="images/serchbutton.gif"
					border="0" style="margin-bottom:-4px">
				</td>
			</tr>
		</table>

	</form>
</div>

<div id="content"
	style="border:1px solid red; background-color: white; width: 130px;
	position: absolute; left: 956px; top: 133px; ">
</div>