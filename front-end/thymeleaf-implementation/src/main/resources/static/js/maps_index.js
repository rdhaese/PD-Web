function initializeMap() {
    //Create var for icon files locations
    var iconsSize = new google.maps.Size(32, 44);
    var icons = {
        companyDepot: {
            url: "../images/map/company-depot.png",
            size: iconsSize,
        },
        locationUpdate: {
            url :"../images/map/location-update.png",
            size: iconsSize,
        },
        currentPosition: {
            url: "../images/map/current-position.png",
            size: iconsSize,
        },
        packetAddress: {
            url: "../images/map/packet-address.png",
            size: iconsSize,
        },
    }
    //Create google maps LatLng object for company address
    var companyAddress = new google.maps.LatLng(APP_CONTEXT.companyAddress.latitude, APP_CONTEXT.companyAddress.longitude);
    //Create google maps LatLng object for packet address
    var packetAddress = new google.maps.LatLng(APP_CONTEXT.packetAddress.latitude, APP_CONTEXT.packetAddress.longitude);
    //Create google maps LatLng object for last location update
    var locationUpdates = APP_CONTEXT.locationUpdates;
    var lastUpdateIndex = locationUpdates.length - 1;
    var lastLocationUpdate = new google.maps.LatLng(locationUpdates[lastUpdateIndex].latitude, locationUpdates[lastUpdateIndex].longitude);

    //create properties for map
    var mapProp = {
        //center the map to the address off CVO Leerstad (project client is fictional)
        center: lastLocationUpdate,
        //give it a reasonable zoom
        //(I would say 10 to 14 would be do-able, 13 is the last setting that shows a reasonable big city nearby)
        zoom: 13,
        //make the map show as a road map
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    //create map
    var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

   //Create a marker with infowindow for company address
    createMarker(map, companyAddress, APP_CONTEXT.msgs.companyDepot, icons.companyDepot);

    //For each location update (except the last one), create a marker
    createLocationUpdateMarkers(locationUpdates, map, icons.locationUpdate);

    //Create a marker with infowindow for last location update
    //create infowindow
    createMarker(map, lastLocationUpdate, APP_CONTEXT.msgs.lastKnownLocation, icons.currentPosition);

    //Create a marker with infowindow for packet address
    //create infowindow
    createMarker(map, packetAddress, APP_CONTEXT.msgs.packetDestination, icons.packetAddress);

    //Show the route from the last location update to the packet address
    showLastRoute(map, lastLocationUpdate, packetAddress);
}

function createMarker(map, latLng, tooltipText, icon) {
//create infowindow
    var infoWindow = new google.maps.InfoWindow({
        content: tooltipText
    });
    //create marker
    var marker = new google.maps.Marker({
        position: latLng,
        map: map,
        title: tooltipText,
        icon: icon
    });
    //bind infowindow to marker
    marker.addListener('click', function () {
        infoWindow.open(map, marker);
    });
}

function createLocationUpdateMarkers(locationUpdates, map, icon) {
    for (i = 0; i < locationUpdates.length - 1; i++) {
        var locationUpdateLatLng = new google.maps.LatLng(locationUpdates[i].latitude, locationUpdates[i].longitude);
        //Format time
        var timeCreated = new Date(locationUpdates[i].timeCreated);
        createMarker(map, locationUpdateLatLng, timeCreated.format(APP_CONTEXT.msgs.locationUpdateDateTimeFormat), icon);
    }
}

function showLastRoute(map, lastLocationUpdate, packetAddress) {
// Instantiate a directions service. Used to calculate routes that are shown between markers
    var directionsService = new google.maps.DirectionsService;
    // Create a renderer
    var lastRouteDisplay = new google.maps.DirectionsRenderer({
        map: map,
        polylineOptions: {
            strokeColor: "green"
        },
        suppressMarkers: true
    });
    // Display the route
    calculateAndDisplayRoute(
        lastRouteDisplay, directionsService, lastLocationUpdate, packetAddress);
}

function calculateAndDisplayRoute(directionsDisplay, directionsService, lastLocationUpdate, packetAddress) {
    // Retrieve the start and end locations and create a DirectionsRequest using
    // WALKING directions.
    directionsService.route({
        origin: lastLocationUpdate,
        destination: packetAddress,
        travelMode: google.maps.TravelMode.DRIVING
    }, function(response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        } else {
            window.alert(APP_CONTEXT.msgs.directionsError + 'INFO: ' + status);
        }
    });
}

//Load map when page loads
google.maps.event.addDomListener(window, 'load', initializeMap);