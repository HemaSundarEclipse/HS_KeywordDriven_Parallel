<script>
	function toggle() {
		for (var i = 0; i < arguments.length; i++) {
			with (document.getElementById(arguments[i])) {
				if (className.indexOf('removed') > -1) {
					className = className.replace('removed');
				} else {
					className += ' removed';
				}
			}
		}
	}
</script>