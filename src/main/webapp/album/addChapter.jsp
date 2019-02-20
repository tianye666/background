<%@page pageEncoding="UTF-8" %>

<script>
    $(function(){
        $("#addChapterTitle").validatebox({
            required:true,
            validType:"length[2,8]"
        });
        $("#addChapterDuration").validatebox({
            required:true,
            validType:"length[2,8]"
        });

        $("#addChapterUrl").filebox({
            buttonText: "选择文件",
            buttonAlign: "right"
        });

        $("#addAlbumId").combobox({
            valueField:"id",
            textField:"title",
            url:"${pageContext.request.contextPath}/album/queryAlbum",
            onLoadSuccess:function(data){
                $("#addAlbumId").combobox("select",data[0].id);
            }
        });

        $.extend($.fn.validatebox.defaults.rules,{
            isNumber:{
                validator:function(value){
                    return !isNaN(value);
                },
                message:"必须为纯数字"

            }

        });


        $("#submitChapter").linkbutton({
            text:"确认添加",
            onClick:function(){
                $("#addChapterForm").form("submit",{
                    url:"${pageContext.request.contextPath }/chapter/insertChapter",
                    onSubmit:function(){
                        return $("#addChapterForm").form("validate");
                    },
                    success:function(){
                        $.messager.show({
                            title:"信息",
                            msg:"添加成功"
                        });
                        $("#addChapterDialog").dialog("close");
                        $("#album").treegrid("load");
                    }
                });
            }


        });


    });

</script>


<form id="addChapterForm" method="post" enctype="multipart/form-data">
    <table align="center">
        <tr><td>标题</td><td><input id="addChapterTitle" name="title" /></td></tr>
        <tr><td>上传音频</td><td><input id="addChapterUrl" name="file"/></td></tr>
        <tr><td>所属专辑</td><td><input id="addAlbumId" name="albumId"/></td></tr>
        <tr align="center"><td colspan="2">
            <a id="submitChapter"></a>
        </td></tr>
    </table>



</form>

