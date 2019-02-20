<%@page pageEncoding="UTF-8" %>


<script>
    $(function () {
        $("#albumDetail").form("load",rowMessage);

        $("#coverImg").prop("src","${pageContext.request.contextPath}/image"+rowMessage.coverImg)

    })
</script>










<form id="albumDetail" method="post">
    <table align="center">
        <tr><td>标题</td><td><input id="title" name="title" /></td></tr>
        <tr><td>音频数量</td><td><input id="count" name="count"/></td></tr>
        <tr><td>作者</td><td><input id="author" name="author"/></td></tr>
        <tr><td>播音员</td><td><input id="broadcast" name="broadcast"/></td></tr>
        <tr><td>简介</td><td><input id="brief" name="brief"/></td></tr>
    </table>
    封面图片:<br/>
    <img id="coverImg" src="#">
</form>