<%@page pageEncoding="UTF-8" %>

<script>
    $(function () {
        var toolbar2 = [{
            iconCls: 'icon-man',
            text:'封禁用户',
            handler:function () {
                var row = $("#userDatagrid").datagrid("getSelected");
                if(row==null){
                    alert("请先选中用户");
                } else if(row.status==0){
                    alert("用户已被封禁");
                } else {
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/user/updateUserStatus",
                        data:"id="+row.id+"&status="+0,
                        success:function () {
                            alert("封禁成功");
                            $("#userDatagrid").datagrid("load");
                        }
                    })
                }
            }
        },{
            iconCls: 'icon-man',
            text:'解封用户',
            handler:function () {
                var row = $("#userDatagrid").datagrid("getSelected");
                if(row==null){
                    alert("请先选中用户");
                }else if(row.status==1){
                    alert("用户未被封禁,无需解封");
                } else {
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/user/updateUserStatus",
                        data:"id="+row.id+"&status="+1,
                        success:function () {
                            alert("解禁成功");
                            $("#userDatagrid").datagrid("load");
                        }

                    })
                }
            }
        }];
        $("#userDatagrid").datagrid({
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            fit:true,
            fitColumns:true,
            toolbar:toolbar2,
            singleSelect:true,
            url:"${pageContext.request.contextPath}/user/queryAllUser",
            columns:[[
                {field:'name',title:'名字',width:40},
                {field:'dharma',title:'上师名',width:80},
                {field:'sex',title:'性别',width:40,formatter:myOpt3},
                {field:'location',title:'位置',width:80,formatter:myOpt4},
                {field:'status',title:'用户状态',width:40,formatter:myOpt5},
                {field:'regDate',title:'注册时间',width:40}
            ]]

        });
        function myOpt3(value) {
            if(value==1){
                return "男";
            }else{
                return "女";
            }
        }
        function myOpt4(value,row){
            return row.province+row.city;
        }
        function myOpt5(value) {
            if(value==1){
                return "正常";
            }else{
                return "被封禁";
            }
        }
    })

</script>




<div id="userDatagrid"></div>