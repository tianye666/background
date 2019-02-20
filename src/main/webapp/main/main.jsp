<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
        .audio1{
            width: 186px;
        }
    </style>
<title>青年阅读平台后台管理系统</title>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="../themes/icon.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
    <script type="text/javascript" src="../js/china.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
<script type="text/javascript">
    var goEasy = new GoEasy({
        appkey: "BC-66a9ac718fc24524b917d7c9270dce56"
    });

	<!--菜单处理-->
    $(function(){
        $.ajax({
            type : 'POST',
            dataType : "json",
            url : '${pageContext.request.contextPath}/menu/queryAllParentMenu',
            success : function(data) {
                $.each(data, function(i,n) {//加载父类节点即一级菜单
                    $('#aa').accordion('add', {
                        title : n.title,
                        iconCls : n.iconCls,
                        selected : false,
                        content : '<div style="padding:10px"><ul id="'+n.id+'" name="'+n.title+'"></ul></div>'
                    });
                });
            }
        });
        $('#aa').accordion({
            onSelect : function(title) {
                $("ul[name='" + title + "']").tree({
                    url : '${pageContext.request.contextPath}/menu/queryMenuByParentId?id='+$("ul[name='" + title + "']").prop("id"),
                    onClick:function(node){
                        //if($("ul[name='" + title + "']").tree("isLeaf",node.target)){
                            if($("#tt").tabs("exists",node.title)){
                                $("#tt").tabs("select",node.title);
                            }else{
                                $("#tt").tabs("add",{
                                    title:node.title,
                                    closable:true,
                                    href:"${pageContext.request.contextPath}"+node.url,
                                    iconCls:node.iconCls
                                });
                            }
                        //}
                    }
                });
            }
        });
    });
</script>

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >青年阅读平台后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:<shiro:principal></shiro:principal> &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/logout" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true">
            <shiro:hasRole name="super">
                <div title="管理员模块" data-options="iconCls:'icon-man',selected:false" style="padding:10px;">
                content2
                </div>
            </shiro:hasRole>
        </div>

    </div>   
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">   
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>  
    </div>   
</body> 
</html>