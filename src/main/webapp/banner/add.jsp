<%@page pageEncoding="UTF-8" %>

<script>
    $(function(){
        $("#addTitle").validatebox({
            required:true,
            validType:"length[1,8]"
        });
        $("#addDesc").validatebox({
            required:true,
            validType:"isLength[100]"
        });
        $("#addImg").filebox({
            buttonText: "选择文件",
            buttonAlign: "right"
        });


        $("#addStatus").combobox({
            valueField:"status",
            textField:"statusName",
            data:[{
                statusName:"是",
                status:"Y"
            },{
                statusName:"否",
                status:"N"
            }
            ],
            onLoadSuccess:function(data){
                $("#addStatus").combobox("select",data[0].status);
            }
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


        $("#submitBanner").linkbutton({
            text:"确认添加",
            onClick:function(){
                $("#addBannerForm").form("submit",{
                    url:"${pageContext.request.contextPath }/banner/insertBanner",
                    onSubmit:function(){
                        return $("#addBannerForm").form("validate");
                    },
                    success:function(){
                        $.messager.show({
                            title:"信息",
                            msg:"添加成功"
                        });
                        $("#addBannerDialog").dialog("close");
                        $("#dg").edatagrid("load");
                    }
                });
            }


        });


    });

</script>

<form id="addBannerForm" method="post" enctype="multipart/form-data">
    <table>
        <tr><td>标题</td><td><input id="addTitle" name="title" /></td></tr>
        <tr><td>上传图片</td><td><input id="addImg" name="file" /></td></tr>
        <tr><td>是否显示到首页</td><td><input id="addStatus" name="status"/></td></tr>
        <tr><td>描述</td><td><input id="addDesc" name="des"/></td></tr>
        <tr align="center"><td colspan="2">
            <a id="submitBanner"></a>
        </td></tr>
    </table>
</form>