importScripts('https://storage.googleapis.com/workbox-cdn/releases/6.5.4/workbox-sw.js');


// When widget is installed/pinned, push initial state.
self.addEventListener('widgetinstall', (event) => {
    event.waitUntil(updateWidget(event));
});

// When widget is shown, update content to ensure it is up-to-date.
self.addEventListener('widgetresume', (event) => {
    event.waitUntil(updateWidget(event));
});

// When the user clicks an element with an associated Action.Execute,
// handle according to the 'verb' in event.action.
self.addEventListener('widgetclick', (event) => {
    if (event.action == "updateName") {
        event.waitUntil(updateName(event));
    }
});

// When the widget is uninstalled/unpinned, clean up any unnecessary
// periodic sync or widget-related state.
self.addEventListener('widgetuninstall', (event) => {});

// push 이벤트 정의
/*
self.addEventListener('push', function(event) {

    // 알림 데이터 받기
    const notificationData = event.data;

    // 알림 열기
    event.waitUntil(self.registration.showNotification("ㅁㄴㅇㄹ1" + notificationData.title, {
        body: "ㅁㄴㅇㄹ2" + notificationData.message,
        icon: notificationData.icon
    }));
});
 */

const updateWidget = async (event) => {
// The widget definition represents the fields specified in the manifest.
    const widgetDefinition = event.widget.definition;

    // Fetch the template and data defined in the manifest to generate the payload.
    const payload = {
        template: JSON.stringify(await (await fetch(widgetDefinition.msAcTemplate)).json()),
        data: JSON.stringify(await (await fetch(widgetDefinition.data)).json()),
    };

    // Push payload to widget.
    await self.widgets.updateByInstanceId(event.instanceId, payload);
}

const updateName = async (event) => {
    const name = event.data.json().name;

    // The widget definition represents the fields specified in the manifest.
    const widgetDefinition = event.widget.definition;

    // Fetch the template and data defined in the manifest to generate the payload.
    const payload = {
        template: JSON.stringify(await (await fetch(widgetDefinition.msAcTemplate)).json()),
        data: JSON.stringify({name}),
    };

    // Push payload to widget.
    await self.widgets.updateByInstanceId(event.instanceId, payload);
}

// 메시지 리스너 추가
self.addEventListener('message', event => {
    const payload = event.data;
    showNotification(payload.title, payload.message);
});

const showNotification = (title, message) => {
    // 알림 열기
    self.registration.showNotification(title, {
        body: message,
        icon: null
    });
}

workbox.precaching.precacheAndRoute(self.__WB_MANIFEST || []);