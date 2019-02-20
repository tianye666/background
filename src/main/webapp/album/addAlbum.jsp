<%@page pageEncoding="UTF-8" %>


<script>
    $(function(){
        $("#addAlbumTitle").validatebox({
            required:true,
            validType:"length[1,20]"
        });
        $("#addAlbumAuthor").validatebox({
            required:true,
            validType:"isLength[2,8]"
        });

        $("#addAlbumBroadcast").validatebox({
            required:true,
            validType:"length[2,8]"
        });

        $("#addAlbumBrief").validatebox({
            required:true,
            validType:"isLength[100]"
        });
        $("#addAlbumCoverImg").filebox({
            buttonText:"选择文件"
        });
        $("#addBookFile").filebox({
            buttonText:"选择文件"
        });


        $.extend($.fn.validatebox.defaults.rules,{
            isNumber:{
                validator:function(value){
                    return !isNaN(value);
                },
                message:"必须为纯数字"

            },
            isLength:{
                validator:function (value,param) {
                    return value.length<=param[0];
                },
                massage:"长度必须在{0}字以内"
            }

        });


        $("#submitAlbum").linkbutton({
            text:"确认添加",
            onClick:function(){
                $("#addAlbumForm").form("submit",{
                    url:"${pageContext.request.contextPath }/album/addIndex",
                    onSubmit:function(){
                        return $("#addAlbumForm").form("validate");
                    },
                    success:function(){
                        $.messager.show({
                            title:"信息",
                            msg:"添加成功"
                        });
                        $("#addAlbumDialog").dialog("close");
                        $("#album").treegrid("load");
                    }
                });
            }


        });


    });

</script>



<form id="addAlbumForm" method="post" enctype="multipart/form-data">
    <table align="center">
        <tr><td>标题</td><td><input id="addAlbumTitle" name="title" /></td></tr>
        <tr><td>作者</td><td><input id="addAlbumAuthor" name="author"/></td></tr>
        <tr><td>播音员</td><td><input id="addAlbumBroadcast" name="broadcast"/></td></tr>
        <tr><td>简介</td><td><input id="addAlbumBrief" name="brief"/></td></tr>
        <tr><td>上传封面</td><td><input id="addAlbumCoverImg" name="file"/></td></tr>
        <tr><td>上传书籍文件</td><td><input id="addBookFile" name="txtfile"/></td></tr>
        <tr align="center"><td colspan="2">
            <a id="submitAlbum"></a>
        </td></tr>

    </table>



</form>