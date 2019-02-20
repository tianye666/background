<%@page pageEncoding="UTF-8" %>
<html>
<head>										
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
    var goEasy = new GoEasy({
        appkey: "BC-66a9ac718fc24524b917d7c9270dce56"
    });
    goEasy.subscribe({
        channel: "140",
        onMessage: function (message) {
            console.info(message.content);
            alert("Channel:" + message.channel + " content:" + message.content);
        }
    });
</script>
</body>
</html>
