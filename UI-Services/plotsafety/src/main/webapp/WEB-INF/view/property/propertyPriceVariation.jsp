<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
<div id="priceVariation" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Price Variation</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-6">
						<canvas id="canvas" class="well"></canvas>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Year/Month</th>
									<th>Price( &#8377; )</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">2014/08</th>
									<td>2012893293</td>
									<td><a href="">Edit</a>|<a href="">Delete</a></td>
								</tr>
								<tr>
									<th scope="row">2014/08</th>
									<td>2012893293</td>
									<td><a href="">Edit</a>|<a href="">Delete</a></td>
								</tr>
								<tr>
									<th scope="row">2014/08</th>
									<td>2012893293</td>
									<td><a href="">Edit</a>|<a href="">Delete</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript">
	$('#priceVariation').on('shown.bs.modal', function(event) {
		waitingDialog.show('Please wait while we load your data', {
			dialogSize : 'm',
			progressType : 'warning'
		});
		getJSON();
		waitingDialog.hide();
	});
	function getJSON() {
		var data = {
			labels : [ "January", "February", "March", "April", "May", "June",
					"July", "August", "September", "october", "november",
					"december" ],
			datasets : [ {
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				data : [ 28, 48, 40, 19, 96, 27, 100, 28, 48, 40, 19, 96, 27 ]
			}, {
				fillColor : "rgba(240,73,73,0.5)",
				strokeColor : "rgba(240,73,73,0.8)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#CCC",
				data : [ 18, 98, 40, 9, 6, 127, 200, 98, 40, 9, 6, 127, 200 ]
			} ]
		}

		var options = {
			animation : true,
			responsive : true
		};

		var ctx = document.getElementById("canvas").getContext("2d");
		new Chart(ctx).Bar(data, options);
	}
</script>