<%@page pageEncoding="UTF-8" %>

<script>
    $(function () {

        $("#search").linkbutton({
            text:"确认添加",
            onClick:function () {
                console.info($("#searchAlbum").prop("value"));
                var param = $("#searchAlbum").prop("value");
                $("#albumList").datagrid({
                    method:"get",
                    url:'${pageContext.request.contextPath}/album/search?param='+param,
                    pagination: true,
                    pageList: [1, 3, 5, 7, 9],
                    pageSize: 3,
                    columns:[[
                        {field:'title',title:'标题',width:40},
                        {field:'duration',title:'音频数量',width:40},
                        {field:'author',title:'作者',width:40},
                        {field:'broadcast',title:'播音员',width:80},
                        {field:'coverImg',title:'封面',width:80,formatter:MyOpt4},
                    ]],
                    fit:true,
                    fitColumns:true
                });
            }
        });
    })
    function MyOpt4(value,row) {
        return "<img src='http://192.168.79.138"+value+"'>"
    }
</script>







<input id="searchAlbum" />
<a id="search"></a>

<table id="albumList">


</table>



<div id="addXlsDialog" class="easyui-dialog" title="导入表格" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

    <form id="addXlsForm" method="post" enctype="multipart/form-data">




    </form>

</div>
