"cd ~/Code/oF_apps/recover/drawingApp && make".unixCmd
"cd ~/Code/oF_apps/recover/drawingApp && make run".unixCmd
"cd ~/Code/oF_apps/recover/drawingApp && make && make run".unixCmd

"cd ~/Code/oF_apps/recover/controlApp && make".unixCmd
"cd ~/Code/oF_apps/recover/controlApp && make run".unixCmd






NetAddr.new("127.0.0.1", 12345).sendMsg("/writeString", "ένα", 200.asInteger,400.asInteger,255.asInteger,255.asInteger,255.asInteger,255.asInteger);

(
~letterTask = Task({
	inf.do({
		NetAddr("127.0.0.1", 12345).sendMsg(
			"/writeString",
			["ς","ε","ρ","τ","υ","θ","ι","ο","π","α","σ","δ","φ","γ","η","ξ","κ","λ","ζ","χ","ψ","ω","β","ν","μ"].at(rrand(0,24).asInteger).asString,
			200.asInteger,400.asInteger,
			255.asInteger,255.asInteger,255.asInteger,255.asInteger);
		0.1.wait;
	})
});
)
~letterTask.start;
~letterTask.stop;

["ς","ε","ρ","τ","υ","θ","ι","ο","π","α","σ","δ","φ","γ","η","ξ","κ","λ","ζ","χ","ψ","ω","β","ν","μ"].at(rrand(0,24).asInteger).asString

"ασδασδξλακν".ascii
"ασδασδξλακν".asSymbol.postln
"sdfasdfsdgashadhfdafhfhdafhahadfhafdhdafhsdhfdfah".at(rrand(0,30).asInteger).asString



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
