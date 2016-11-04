<div id="addProperty" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Add Property</h4>
			</div>
			<div class="modal-body">
				<form action="addProperty.htm?${_csrf.parameterName}=${_csrf.token}"
					method="post" enctype="multipart/form-data" id="addproperty">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label>Venture Name</label> <input type="text" required
									name="ventureName" class="form-control"
									placeholder="Venture Name">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Plot Number</label> <input type="text" name="plotNo"
									class="form-control" placeholder="Plot Number">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Survey Number</label> <input type="text" name="surveyNo"
									class="form-control" placeholder="Survey Number">
							</div>
						</div>

					</div>

					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label>Plot Area</label> <input type="text" name="plotArea"
									class="form-control" placeholder="Enter Plot Area">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Plot Facing</label> <input type="text" name="facing"
									class="form-control" placeholder="Enter Plot Facing">
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label>Audit Done</label> <input type="checkbox"
									data-size="small" name="audit" class="form-control">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>Landmark</label> <input type="text" name="landmark"
									class="form-control" placeholder="Enter Landmark here">
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label>Market Value</label> <input type="text"
									name="marketValue" id="marketValue" class="form-control"
									placeholder="Enter Market Value">
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label>Government Value</label> <input type="text"
									name="govtValue" class="form-control"
									placeholder="Enter Government Value">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>Transactions</label>
								<textarea placeholder="Enter Transactions Here.."
									name="transactions" rows="3" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>Developments</label>
								<textarea placeholder="Enter Any Recent Developments Here"
									name="developments" rows="3" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<!-- <div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>Upload</label> <input type="file" name="files" id="T7">
								<div id="T7-list" style="border: #999 solid 3px; padding: 10px;">
									This is div#T7-list - selected files will be populated here...
									<br> <br>
								</div>
							</div>
						</div>
					</div> -->
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>Property Images</label>
								<!-- <input type="file" name="files"
									multiple accept="image/*" /> -->
								<div class="row">
									<div class="col-md-3">
										<span class="btn btn-fill btn-file btn-sm"> Browse <input
											type="file" name="files" accept="gif|jpg|png|tiff"
											id="selectFile">
										</span>
									</div>
									<div class="col-md-8">
										<span class="" id="selectedFiles"></span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="customerID" id="customerid"
						value="${customerProperties[0].customerID}" />
					<button type="submit" class="btn btn-info btn-fill pull-right">Save</button>
					<button type="reset" class="btn btn-info btn-default pull-right">Reset</button>
					<div class="clearfix"></div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript">
	$('#addproperty').on('submit', function() {
		return validateForm();
	});
	function validateForm() {
		var marketVal = $("#marketValue").val();
		return validateRange(marketVal);
	}

	function validateRange(inputValue) {
		if (Number.isInteger(inputValue)) {
			return true;
		} else {
			var minMaxVal = inputValue.split("-");
			if (minMaxVal.length != 2) {
				alert("invalid Value (use - to give a range eg: minimum value - maximum value) :"
						+ inputValue);
				return false;
			} else {
				if (Number.isInteger(minMaxVal[0])
						&& Number.isInteger(minMaxVal[1])
						&& minMaxVal[0] < minMaxVal[1]) {
					return true;
				} else {
					alert("invalid Range (use - to give a range eg: minimum value - maximum value) :"
							+ inputValue);
					return false;
				}
			}
		}
	}
</script>
