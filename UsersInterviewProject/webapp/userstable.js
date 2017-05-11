var outputHttp = "output";
var usersData = [];

function getData() {
	return usersData;
}

function downloadUsersFromServer() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", outputHttp, true);
	xhr.send(null);
	xhr.onload = function(e) {
		if (this.status == 200) {
			var jSONString = e.target.responseText;
			var jSONObject = JSON.parse(jSONString);
			convertJSONToArray(jSONObject.users);
		}
	};
}

function convertJSONToArray(jSONArray) {
	jSONArray.forEach(function(user) {
		usersData.push([ user["name"], user["surname"], user["login"] ]);
	});

	window.console.log(usersData);
	$(document).ready(function() {
		$('#users').DataTable({
			data : getData(),
			columns : [ {
				title : "Name"
			}, {
				title : "Surname"
			}, {
				title : "Login"
			} ]
		});
	});
}

window.onload = function() {
	downloadUsersFromServer();
}