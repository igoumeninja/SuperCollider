(
~screenWidth = 1900;
~screenHeight = 1080;

~imageTask = Task({
	inf.do({
		NetAddr("127.0.0.1", 9005).sendMsg(
			"/drawImage", rrand(0,50).asInteger,
			100*(rrand(0,20)).asInteger,
			100*(rrand(0,10)).asInteger,
			100.asInteger,
			100.asInteger);
		0.01.wait;
	})
});
)
~imageTask.start;
~imageTask.stop;
NetAddr("127.0.0.1", 9005).sendMsg(
	"/drawImage", rrand(0,50).asInteger,
	rrand(0,~screenWidth).asInteger,
	rrand(0,~screenHeight).asInteger,
	rrand(0,~screenWidth).asInteger,
	rrand(0,~screenHeight).asInteger);

	rrand(0,50).asInteger