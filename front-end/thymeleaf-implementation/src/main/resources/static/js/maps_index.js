//TODO Find a way to pass arguments from html, coordinates should come from back end, that got them from a google maps search on the address of the packet
//http://stackoverflow.com/a/2190927/2323354
//probably do this for maps_contact to

function initializeMap() {
    //Create variable holding latitude and longitude of the address of CVO Leerstad (project client is fictional, this is a school project)
    latLng = new google.maps.LatLng(51.1115523, 3.9875753);
    //create properties for map
    var mapProp = {
        //center the map to the address off CVO Leerstad (project client is fictional)
        center: latLng,
        //give it a reasonable zoom
        //(I would say 10 to 14 would be do-able, 13 is the last setting that shows a reasonable big city nearby)
        zoom: 13,
        //make the map show as a road map
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    //create map
    var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

    //create marker at center
    var marker = new google.maps.Marker({
        position: latLng,
    });

    //set marker on map
    marker.setMap(map);
}

//Load map when page loads
google.maps.event.addDomListener(window, 'load', initializeMap);