<%@page pageEncoding="UTF-8" %>

<script>
    var rowMessage;
    var toolbar = [{
        iconCls: 'icon-add',
        text: "专辑详情",
        handler: function () {
            var row = $("#album").treegrid("getSelected");
            if(row==null){
                alert("请先选中行");
            } else if(row.children!=undefined) {
                rowMessage= row;
                $("#albumDetail").dialog("open");
                $("#albumDetail").form("load",row);
            }else{
                alert("请先选中专辑行");
            }

        }
    }, '-', {
        text: "添加专辑",
        iconCls: 'icon-edit',
        handler: function () {
            //获取选中行
            $("#addAlbumDialog").dialog("open");
        }
    }, '-', {
        text: "添加音频",
        iconCls: 'icon-remove',
        handler: function () {
            $("#addChapterDialog").dialog("open");
        }
    }, '-', {
        text: "音频下载",
        iconCls: 'icon-save',
        handler: function () {
            var row = $("#album").treegrid("getSelected");
            if(row==null){
                alert("请先选中行");
            } else if(row.children==undefined) {
                location.href= "${pageContext.request.contextPath}/chapter/download?name="+row.url;
            }else{
                alert("请先选中音频行");
            }

        }
    }, '-', {
        text: "导出表格",
        iconCls: 'icon-save',
        handler: function () {
            location.href= "${pageContext.request.contextPath}/album/exportExl";
        }
    }, '-', {
        text: "导入表格",
        iconCls: 'icon-save',
        handler: function () {
            $("#addXlsDialog").dialog("open");
        }
    }]
    $(function () {


        $('#album').treegrid({
            method:"get",
            url:'${pageContext.request.contextPath}/album/queryAllAlbum',
            idField:'id',
            treeField:'title',
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            state:"closed",
            columns:[[
                {field:'title',title:'名字',width:40},
                {field:'duration',title:'时长',width:40},
                {field:'size',title:'大小',width:40},
                {field:'url',title:'试听',width:80,formatter:myOpt},
                {field:'xxx',title:'下载链接',width:80,formatter:myOpt2}
            ]],
            fit:true,
            fitColumns:true,
            toolbar:toolbar,
            <%--onLoadSuccess:function (row ,data) {--%>
                <%--$.each(data.rows,function(i,n){--%>
                    <%--$.ajax({--%>
                        <%--type:"post",--%>
                        <%--url:"${pageContext.request.contextPath}/chapter/queryChapterByAlbumId",--%>
                        <%--data:"id="+n.id,--%>
                        <%--success:function(result){--%>
                            <%--$('#album').treegrid("append",{--%>
                                <%--parent:n.id,--%>
                                <%--data:result--%>
                            <%--});--%>
                        <%--}--%>
                    <%--});--%>
                <%--});--%>
            <%--}--%>

        });
        
        $(document).on("click","#downUrl",function () {
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/chapter/download",
                data:"name="+$("#downUrl").prop("text"),
                success:function () {}
            })
        });

        $("#albumDetail").dialog({
            title:"请输入添加小组信息",
            width:500,
            height:500,
            closed:true,
            modal:true,
            cache:false,
            href:"${pageContext.request.contextPath}/album/albumDetail.jsp"
        });

        $("#addAlbumDialog").dialog({
            title:"请输入添加专辑信息",
            width:500,
            height:500,
            closed:true,
            modal:true,
            cache:false,
            href:"${pageContext.request.contextPath}/album/addAlbum.jsp"
        });
        $("#addChapterDialog").dialog({
            title:"请输入添加音频信息",
            width:500,
            height:500,
            closed:true,
            modal:true,
            cache:false,
            href:"${pageContext.request.contextPath}/album/addChapter.jsp"
        });
        $("#submitXls").linkbutton({
            text:"确认添加",
            onClick:function(){
                $("#addXlsForm").form("submit",{
                    url:"${pageContext.request.contextPath }/album/importExl",
                    onSubmit:function(){
                        return $("#addXlsForm").form("validate");
                    },
                    success:function(){
                        $.messager.show({
                            title:"信息",
                            msg:"添加成功"
                        });
                        $("#addXlsDialog").dialog("close");
                        $("#album").treegrid("load");
                    }
                });
            }

        })

    })
    function myOpt(value){
        if(value!=undefined){
            return "<audio class='audio1' src='${pageContext.request.contextPath}/audio"+value+"' controls=\"controls\">\n" +
                "</audio>";
        }

    }
    function myOpt2(value,row){
        if(row.url!=undefined) {
            return "<a id='downUrl' href='"+"${pageContext.request.contextPath}/chapter/download?name="+row.url+"&title="+row.title+"'>" + row.url + "</a>";
        }
    }

</script>









<table id="album">

</table>

<div id="albumDetail"></div>
<div id="addAlbumDialog"></div>
<div id="addChapterDialog"></div>


<div id="addXlsDialog" class="easyui-dialog" title="导入表格" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

    <form id="addXlsForm" method="post" enctype="multipart/form-data">

        <div>
            请选择:<input class="easyui-filebox" name="file"/>
        </div>
        <a id="submitXls"></a>

    </form>

</div>
