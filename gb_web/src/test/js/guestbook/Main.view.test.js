describe ("the view", function() {
	var sut = sap.ui.jsview("guestbook.Main"); 

	it("should be associated with a controller named 'guestbook.Main'", function() {
		expect(sut.getControllerName()).toBe("guestbook.Main");
	});
});
