var userMessageToken = null;

const firebaseConfig = {
    apiKey: "AIzaSyAcZqFrbjiHQTbGGFwDfQQyXs-fPVvar5g",
    authDomain: "v-here.firebaseapp.com",
    projectId: "v-here",
    storageBucket: "v-here.appspot.com",
    messagingSenderId: "326932770799",
    appId: "1:326932770799:web:7380545ca30e246583501a",
    measurementId: "G-3GKPNXGFEZ"
};

function reNewMessagingToken(token) {
    $.post(`/api/notification/messaging-token/renew`, {token: token})
        .done((data)=>{
            console.log(data);
        })
        .fail((e)=>{
            console.log(e)
        });
    userMessageToken = token;
}

function initFirebase() {
    firebase.initializeApp(firebaseConfig);

    const messaging = firebase.messaging();

    messaging.getToken({vapidKey: "BIa44dda0rGkuXJiGb4J2qSCuEKFafOpX0TFZzCO0MZD_hS0HdH_m0QTosKYIJcO4_OYlWyXYi31aFUzXZXFMKg"})
        .then((currentToken) => {
            if(currentToken) {
                console.log(`fcm token : ${currentToken}`);
                reNewMessagingToken(currentToken);
            }
        })
        .catch((e) => {
            alert(e);
            console.log(e);
        });

    messaging.onMessage((payload) => {
        if(payload.notification) {
            const notification = payload.notification;
            sendNotificationMessage(notification.title, notification.body);
        }
    });
}
initFirebase();