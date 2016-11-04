function initialize() {

	var latLonPoints = $("#hidden_text").text();

	var latLonArray = latLonPoints.split(";");
	if (latLonArray.length > 0) {
		var latlonStartingPoint = latLonArray[0].split(",");
		var startingPoint = new google.maps.LatLng(latlonStartingPoint[0],
				latlonStartingPoint[1]);

		var mapOptions = {
			zoom : 10,
			center : startingPoint
		};
		var map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
		var markers = [];

		// alert("2-->" + latLonArray);
		// Create the polyline's points
		var polylineStrokes = [];

		for (var i = 0; i < latLonArray.length; i++) {

			var latlon = latLonArray[i].split(",");
			var currentPoint = new google.maps.LatLng(latlon[0], latlon[1]);
			markers
					.push(new google.maps.Marker(
							{
								position : currentPoint,
								map : map,
								title : 'Point ' + (i + 1),
								icon : 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='
										+ (i + 1) + '|FE6256|000000'
							}));
			polylineStrokes.push(currentPoint);

		}

		var flightPath = new google.maps.Polyline({
			path : polylineStrokes,
			geodesic : true,
			strokeColor : '#660099',
			strokeOpacity : 0.7,
			strokeWeight : 4
		});
		flightPath.setMap(map);

		google.maps.event.addListener(flightPath, 'mouseover', function(event) {
			this.setOptions({
				strokeOpacity : 1
			});
		});

		google.maps.event.addListener(flightPath, 'mouseout', function(event) {
			this.setOptions({
				strokeOpacity : 0.7
			});
		});

	} else {
		$("#error").text("No Results to display");
	}
	$("#map-canvas").height($("#sidebar").height()-60);
}