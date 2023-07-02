let stompClient = null;

function connect() {
    let socket = new SockJS('/ws');
    var user = document.getElementById('name').value;
    stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
        stompClient.subscribe('/topic/cursor', function (result) {
            showCursorPoint(JSON.parse(result.body));
        });

        stompClient.subscribe('/topic/public', function (result) {
            showMessage(JSON.parse(result.body));
        });

        stompClient.subscribe("/user/" + user + "/queue/messages", function (result) {
            showMessage(JSON.parse(result.body));
        });
    });
}

function sendMessage() {
    var name = document.getElementById('name').value;
    var message = document.getElementById('message').value;
    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify({ 'content': message, 'sender': name }));
}

function sendPrivateMessage() {
    var name = document.getElementById('name').value;
    var message = document.getElementById('message').value;
    var receiver = document.getElementById('receiver').value;

    var messageObject = {
        'content': message,
        'sender': name,
        'receiver': receiver
    }

    showMessage(messageObject);
    stompClient.send("/app/chat.sendPrivateMessage", {}, JSON.stringify(messageObject));
}

function showMessage(message) {
    var messages = document.getElementById('messages');
    var messageItem = document.createElement('p');
    messageItem.innerHTML = message.receiver ? "[" + message.sender + "]" + ":" + message.content + " [to: " + message.receiver + "]" : "[" + message.sender + "]" + ":" + message.content + " [to: all]";
    messages.appendChild(messageItem);
}

function sendCursor(e) {
    let x = e.clientX;
    let y = e.clientY;
    stompClient.send("/app/cursor", {}, JSON.stringify({ 'x': x, 'y': y }));
}

function showCursorPoint(message) {
    let response = document.getElementById('point');
    response.innerHTML = "x: [" + message.x + "]" + " y: [" + message.y + "]";
}

var elementP = document.getElementById('table-p')
elementP.addEventListener('mousemove', function (event){
    sendCursor(event);
});