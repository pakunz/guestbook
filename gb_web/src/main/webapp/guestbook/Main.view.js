sap.ui.jsview("guestbook.Main", {

	getControllerName : function() {
		return "guestbook.Main";
	},

	createContent : function(oController) {
		return new sap.ui.commons.TextView({
			text : "MainView"
		});
	}

});
