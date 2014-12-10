$(document).ready(
		function() {

			$('#btnSignUpReset').click(
					function() {

						$(':input', '#signUpForm').not(
								':button, :submit, :reset, :hidden').val('');
						$('option:eq(0)').attr('selected', 'selected');

					});
			
			

		});
