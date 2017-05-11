var inputServletHttp = "http://localhost:8080/users/input";
var usesTableHttp = "http://localhost:8080/users/userstable.html";
var alertMessage = "Liczba dodanych użytkowników: ";

var _validFileExtensions = [ ".xml" ];

function onUpload(e) {
	var file = e.target.files[0];

	if (file == null) {
		return;
	}
	var result = validate(file);
	if (result) {
		readFile(file);
	}
}

function validate(file) {
	var sFileName = file.name;
	if (sFileName.length > 0) {
		var blnValid = false;
		for (var j = 0; j < _validFileExtensions.length; j++) {
			var sCurExtension = _validFileExtensions[j];
			if (sFileName.substr(sFileName.length - sCurExtension.length,
					sCurExtension.length).toLowerCase() == sCurExtension
					.toLowerCase()) {
				blnValid = true;
				break;
			}
		}

		if (!blnValid) {
			alert("Sorry, " + sFileName
					+ " is invalid, allowed extensions are: "
					+ _validFileExtensions.join(", "));
			return false;
		}
	}

	return true;
}

function readFile(file) {
	var reader = new FileReader();
	reader.onload = function() {
		var xmlFile = new DOMParser().parseFromString(this.result, "text/xml");
		onRequest(xmlFile);
	};
	reader.readAsText(file);
}

function onRequest(xmlFile) {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", inputServletHttp, true);
	xhr.send(xmlFile);
	xhr.onload = function(e) {
		if (this.status == 200) {
			window.console.log(e);
			var returnMessage = e.target.responseText;
			alert(alertMessage + returnMessage);
			redirectToUsersTableHTTP();
		}
	};
}

function redirectToUsersTableHTTP() {
	window.location.href = usesTableHttp;
}

document.getElementById("uploadFile").addEventListener("change", onUpload,
		false);