let stompClient = null;

let socket = new SockJS('/ws');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
    console.log(frame);
    stompClient.subscribe('/topic/cursor', function (result) {
        showCursorPoint(JSON.parse(result.body));
    });

    stompClient.subscribe('/topic/public', function (result) {
        showMessage(JSON.parse(result.body));
    });
});

function sendMessage() {
    var name = document.getElementById('name').value;
    var message = document.getElementById('message').value;
    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify({ 'content': message, 'sender': name }));
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

function showMessage(message) {
    var messages = document.getElementById('messages');
    var messageItem = document.createElement('p');
    messageItem.innerHTML = "[" + message.sender + "]" + ":" + message.content;
    messages.appendChild(messageItem);
}

var elementP = document.getElementById('table-p')
elementP.addEventListener('mousemove', function (event){
    sendCursor(event);
});