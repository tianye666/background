<%@page pageEncoding="UTF-8" %>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data:['近期活跃度']
        },
        xAxis: {
            data: ["近一周","近两周","近三周","近一月"]
        },
        yAxis: {}
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath}/user/queryActivityByGoeasy",

    });

    goEasy.subscribe({
        channel:"activity",
        onMessage:function (message) {
            myChart.setOption({
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: eval(message.content)
                }]
            });
        }
    });
</script>
