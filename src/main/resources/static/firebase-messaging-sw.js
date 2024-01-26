importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-messaging.js');

const firebaseConfig = {
    apiKey: "AIzaSyAcZqFrbjiHQTbGGFwDfQQyXs-fPVvar5g",
    authDomain: "v-here.firebaseapp.com",
    projectId: "v-here",
    storageBucket: "v-here.appspot.com",
    messagingSenderId: "326932770799",
    appId: "1:326932770799:web:7380545ca30e246583501a",
    measurementId: "G-3GKPNXGFEZ"
};

firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();
messaging.onBackgroundMessage((payload) => {
    console.log(
        '[firebase-messaging-sw.js] Received background message ',
        payload
    );
});