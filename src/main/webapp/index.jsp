<html>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/he">Jersey resource</a>
    <p id="demo">Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
    
    <button type="button" onclick="loadDoc()">Change Content</button>
    
    <script>
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      var myObj =JSON.parse(this.responseText);
      document.getElementById("demo").innerHTML=myObj[0].author;
    }
  };
  xhttp.open("GET", "http://localhost:8080/MessengerService/webapi/messages/getallmessages", true);
  xhttp.send();
}
</script>
</body>
</html>
