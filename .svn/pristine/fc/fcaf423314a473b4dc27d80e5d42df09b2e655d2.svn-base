<%@ include file="skeleton/tags.jsp"%>

<script type="text/javascript">
	var websocket = new WebSocket(
			"ws://localhost:8080/Concurrent/chat/chatroom?${userContext.email}");

	websocket.onmessage = function processMessage(message) {
		var jsonData = JSON.parse(message.data);
		if (jsonData.message != null)
			messagesTextArea.value += jsonData.message + "\n";
	}

	function sendMessage() {
		if (messageText.value != "") {
			websocket.send(messageText.value);
			messageText.value = "";
			document.getElementById("messageText").focus();
		}
	}
</script>

<div class="col-sm-5">
	<textarea class="form-control" rows="10" cols="45" readonly="readonly"
		id="messagesTextArea"></textarea>

	<input type="text" class="form-control" id="messageText"
		placeholder="Your message..."
		onkeydown="if (event.keyCode == 13) {document.getElementById('btn').click(); return false;}"></input>
	<s:submit id="btn" theme="bootstrap" cssClass="btn btn-primary"
		value="Send" onclick="sendMessage();" />
</div>
