<div id="modal-from-dom-addSide" class="modal hide fade">
	<div class="modal-header">
		<a href="#" class="close">&times;</a>
		<h3>Add Side Effect</h3>
	</div>

	<form method="post" action="addSide.do">
		<div class="modal-body">

			<fieldset>
				<div class="clearfix">
					<label for="name">Name</label>
					<div class="input">
						<input class="large" id="name" name="name" type="text" value="${addsideform.name}" />
						<span class="help-block">e.g., Dizziness, Energy</span>
					</div>
				</div>
			</fieldset>

		</div>

		<div class="modal-footer">
			<input type="submit" class="btn primary" name="button" value="Add side effect" />
		</div>
	</form>
</div>