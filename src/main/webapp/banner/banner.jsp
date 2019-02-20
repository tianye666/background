<%@page pageEncoding="UTF-8" %>

<script>
    $(function () {
        $("#addBannerDialog").dialog({
            title:"请输入添加小组信息",
            width:500,
            height:500,
            closed:true,
            modal:true,
            cache:false,
            href:"${pageContext.request.contextPath}/banner/add.jsp"
        });
        var toolbar = [{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                $("#addBannerDialog").dialog("open");
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    console.info(index);
                    $("#dg").edatagrid("editRow", index);
                } else {
                    alert("请先选中行")
                }


            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    $.ajax({
                        type:"POST",
                        url:"${pageContext.request.contextPath}/banner/deleteBanner",
                        data:"id="+row.id+"&imgPath="+row.imgPath,
                        success:function () {
                            $("#dg").edatagrid("reload");
                        }
                    })
                } else {
                    alert("请先选中行");
                }
            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-save',
            handler: function () {
                $("#dg").edatagrid("saveRow")

            }
        }]
        $("#dg").edatagrid({
            method:"POST",
            updateUrl:"${pageContext.request.contextPath}/banner/updateBanner",
            url:"${pageContext.request.contextPath}/banner/queryBannerByPage",
            fitColumns: true,
            fit: true,
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            columns:[[
                {field:'title',title:'名称',width:80},
                {field:'status',title:'Status',width:60,editor:{
                        type:"text",
                        options:"required:true"
                    }
                }

            ]],
            toolbar:toolbar,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/image' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.des + '</p>' +
                    '<p>日期: ' + rowData.pubDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });
    })

</script>


<table id="dg">

</table>
<div id="addBannerDialog" align="center"></div>








