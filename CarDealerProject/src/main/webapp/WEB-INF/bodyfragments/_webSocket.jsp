<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div onload="disconnect()" class="row" style="margin-left: 36%; align-items: center; width: 400px">
  <div style=" margin-top: 20px"  >
    <div>
      <input style="width: 300px" type="text" id="from" placeholder="Choose a nickname"/>
    </div>
    <br />
    <div>
      <button style=" width: 300px; margin-bottom: 7px" id="connect" class="btn btn-warning" onclick="connect();">Connect</button>
      <button id="disconnect" style=" width: 300px" class="btn btn-danger" disabled="disabled" onclick="disconnect();">
        Disconnect
      </button>
    </div>
  </div>
  <br />
  <div id="conversationDiv" style="align-content: center; margin-top: 30px;">
    <input style=" height: 30px; margin-top: 7px; margin-left: 15px" type="text;"  id="text" placeholder="Write a message..."/>
    <button id="sendMessage" style="margin-left: 10px" class="btn btn-info" onclick="sendMessage();">Send</button>
    <div style="border: black 1px solid; width: 300px; border-radius: 10px; margin-top: 20px; margin-bottom: 20px; padding: 15px; background: #141414">
      <p style="color: forestgreen" id="response"></p>
    </div>
  </div>
</div>

<script type="text/javascript" src="https://unpkg.com/kd-shim-sockjs-client@0.3.4/sockjs-0.3.4.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js" integrity="sha512-tL4PIUsPy+Rks1go4kQG8M8/ItpRMvKnbBjQm4d2DQnFwgcBYRRN00QdyQnWSCwNMsoY/MfJY8nHp2CzlNdtZA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
  var stompClient = null;

  function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility
            = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
  }

  function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
      setConnected(true);
      console.log('Connected: ' + frame);
      stompClient.subscribe('/topic/messages', function(messageOutput) {
        showMessageOutput(JSON.parse(messageOutput.body));
      });
    });
  }

  function disconnect() {
    if(stompClient != null) {
      stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
  }

  function sendMessage() {
    var from = document.getElementById('from').value;
    var text = document.getElementById('text').value;
    stompClient.send('/app/chat', {},
            JSON.stringify({'from':from, 'text':text}));
  }

  function showMessageOutput(messageOutput) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(messageOutput.from + ": "
            + messageOutput.text + " (" + messageOutput.time + ")"));
    response.appendChild(p);
  }
</script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
