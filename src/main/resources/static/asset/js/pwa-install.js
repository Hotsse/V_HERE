let deferredPrompt;

window.addEventListener('beforeinstallprompt', (event) => {
    event.preventDefault();
    deferredPrompt = event;
});

window.addEventListener('appinstalled', () => {
    $("#btn-install-pwa").hide();
    deferredPrompt = null;
});

function installPWA() {
    if (deferredPrompt) {
        // Show the install prompt
        deferredPrompt.prompt();
        // Wait for the user to respond to the prompt
        deferredPrompt.userChoice.then((choiceResult) => {
            if (choiceResult.outcome === 'accepted') {
                console.log('User accepted the install prompt');
            } else {
                console.log('User dismissed the install prompt');
            }
            // Reset the deferredPrompt variable
            deferredPrompt = null;
        });
    }
}

function isPWA() {
    return window.matchMedia('(display-mode: standalone)').matches ||
        window.navigator.standalone ||
        document.referrer.includes('android-app://') ||
        document.referrer.includes('ios-app://');
}
$(document).ready(()=>{
    if(!isPWA()) {
        $("#btn-install-pwa").show();
    }
});