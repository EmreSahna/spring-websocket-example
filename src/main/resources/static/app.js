let stompClient = null;

function connect() {
    let socket = new SockJS('/ws');
    var user = localStorage.getItem('username');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
        stompClient.subscribe('/topic/public', function (result) {
            showMessage(JSON.parse(result.body));
        });

        stompClient.subscribe("/user/" + user + "/queue/messages", function (result) {
            showMessage(JSON.parse(result.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

function sendMessage() {
    var name = document.getElementById('name').value;
    var message = document.getElementById('message').value;
    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify({ 'content': message, 'sender': name }));
}

function sendPrivateMessage() {
    var name = localStorage.getItem('username');
    var message = document.getElementById('message').value;
    var receiver = document.getElementById('receivers').value;

    var messageObject = {
        'content': message,
        'sender': name,
        'receiver': receiver
    }

    console.log(messageObject)

    showMessage(messageObject);
    stompClient.send("/app/chat.sendPrivateMessage", {}, JSON.stringify(messageObject));
}

function showMessage(message) {
    var messages = document.getElementById('messages');
    var messageItem = document.createElement('p');
    messageItem.innerHTML = message.receiver ? "[" + message.sender + "]" + ":" + message.content + " [to: " + message.receiver + "]" : "[" + message.sender + "]" + ":" + message.content + " [to: all]";
    messages.appendChild(messageItem);
}

var username = localStorage.getItem('username');
var websocketThingsDiv = document.getElementById('websocket-things');
var userThingsDiv = document.getElementById('user-things');
if(username) {
    websocketThingsDiv.style.display = 'block';
    userThingsDiv.style.display = 'none';
} else {
    websocketThingsDiv.style.display = 'none';
    userThingsDiv.style.display = 'block';
}

fetch('http://localhost:8080/users')
.then(response => response.json())
.then(data => data.forEach(user => {
    if(user.username != username) {
        var userItem = document.createElement('option');
        userItem.value = user.username;
        userItem.innerHTML = user.username;
        document.getElementById('receivers').appendChild(userItem);
    }
})).catch(error => console.error(error));

function logout() {
    localStorage.removeItem('username');
    window.location.reload();
}