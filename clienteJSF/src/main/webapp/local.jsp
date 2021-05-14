<%-- 
    Document   : local
    Created on : 08/12/2016, 17:59:40
    Author     : jose2
--%>

<%@page import="javax.faces.context.FacesContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>local</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
    </head>
    <body>
        <script src="resources/js/jquery-2.1.4.min.js"></script>

        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/map2.js"> </script>

        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBab8JBkgtgI3IPJLQiCol30M8nEvE2ER4&libraries=places,geometry&callback=initMap"
        async defer></script>


        <div class="form-group">
            <h4 class="info text-center center-block text-success">para começar infome um local</h4>

            <input id="pac-input" type="text" class=" navbar-brand2 navbar-collapse  text-center "  placeholder="Digite uma localidade aqui" style="left: 40%;position: relative">

        </div>
        <div id="map" style="width: 100%; height: 500px;">
        </div>
        <div class="container">


            <div class="container-fluid btn-group " role="group">
                <button onclick="enableAddMarker()" type="button" class="btn btn-default" aria-label="Enable Marker">
                    <span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> Adicionar
                </button>
                
                <button type="button" onclick="closeMarker()" class="btn btn-default" aria-label="Left Align">
                    <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span> Remover
                </button>
                <form action="/falai/local1" id="formlocal" method="post">


                    <input type="text" id="local" name="local" class="form-control" style="display: none;">
                    <button name="savar" type="submit" id="save"  class="btn btn-default" aria-label="Left Align" style="position: relative;left: 100%;top: -34px">savar
                        <span class="glyphicon glyphicon-align-left glyphicon-saved " aria-hidden="true"></span>

                    </button>

                </form>
            </div>
        </div>            



        <script>
            var map;

            var markers = [];

            $(document).ready(function () {

            });


            function initMap() {
                map = new google.maps.Map(document.getElementById('map'), {
                    center: {lat: -34.397, lng: 150.644},
                    zoom: 15
                });

                var infoWindow = new google.maps.InfoWindow({map: map});

                // Try HTML5 geolocation.
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        var pos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        };

                        var marker2 = new google.maps.Marker({
                            position: pos,
                            map: map,
                            title: 'Você está aqui.'
                        });
                        map.setCenter(pos);
                        $('#local').val(pos.lat + ' ' + pos.lng);
                    }, function () {
                        handleLocationError(true, infoWindow, map.getCenter());
                    });
                } else {
                    // Browser doesn't support Geolocation
                    handleLocationError(false, infoWindow, map.getCenter());
                }

                //RELATIVO AO BUSCADOR

                // Create the search box and link it to the UI element.
                var input = document.getElementById('pac-input');
                var searchBox = new google.maps.places.SearchBox(input);

                // Bias the SearchBox results towards current map's viewport.
                map.addListener('bounds_changed', function () {
                    searchBox.setBounds(map.getBounds());
                });

                // Listen for the event fired when the user selects a prediction and retrieve
                // more details for that place.
                searchBox.addListener('places_changed', function () {
                    var places = searchBox.getPlaces();

                    if (places.length === 0) {
                        return;
                    }

                    // Clear out the old markers.
                    markers.forEach(function (marker) {
                        marker.setMap(null);
                    });
                    markers = [];

                    // For each place, get the icon, name and location.
                    var bounds = new google.maps.LatLngBounds();
                    places.forEach(function (place) {
                        var icon = {
                            url: place.icon,
                            size: new google.maps.Size(71, 71),
                            origin: new google.maps.Point(0, 0),
                            anchor: new google.maps.Point(17, 34),
                            scaledSize: new google.maps.Size(25, 25)
                        };

                        // Create a marker for each place.
                        markers.push(new google.maps.Marker({
                            map: map,
                            icon: icon,
                            title: place.name,
                            position: place.geometry.location
                        }));

                        if (place.geometry.viewport) {
                            // Only geocodes have viewport.
                            bounds.union(place.geometry.viewport);
                            var posBusca = place.geometry.location; 
                            $("#local").val(posBusca.lat()+" "+posBusca.lng());
                        } else {
                            bounds.extend(place.geometry.location);
                        }
                          
                    });
                    map.fitBounds(bounds);
                });
            }

            function handleLocationError(browserHasGeolocation, infoWindow, pos) {
                infoWindow.setPosition(pos);
                infoWindow.setContent(browserHasGeolocation ?
                        'Error: The Geolocation service failed.' :
                        'Error: Your browser doesn\'t support geolocation.');
            }

            //This function enable adding markers to map
            function enableAddMarker() {
                google.maps.event.addListenerOnce(map, 'click', function (event) {
                    addMarker(event.latLng);
                });
            }

            function disableAddMarker() {
                //map.clearListeners(map,'click');
                addListener.remove();
            }

            // Adds a marker to the map and push to the array.
            function addMarker(location) {
                var marker = new google.maps.Marker({
                    position: location,
                    map: map
                });

                $('#local').val(marker.position.lat() + ' ' + marker.position.lng());

                var infowindow = new google.maps.InfoWindow({
                    content: '<div><button type="button" onclick="closeThisMarker()" class="btn btn-default" aria-label="Left Align">' +
                            '<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span> Remover ' +
                            '</button></div>',
                    maxWidth: 200
                });

                marker.addListener('click', function () {
                    infowindow.open(map, marker);
                    infowindow.marker = marker;
                    $('#local').val(marker.position.lat() + ' ' + marker.position.lng());
                });

                markers.push(marker);
            }

            function closeMarker() {
                for (var i = 0; i < markers.length; i++) {

                    google.maps.event.clearInstanceListeners(markers[i], 'click');

                    markers[i].addListener('click', function () {
                        this.setMap(null);
                        $('#local').val(" ");
                    });
                }
            }

            function closeThisMarker() {
                infoWindow.marker.setMap(null);
            }

        </script>


    </body>
</html>
