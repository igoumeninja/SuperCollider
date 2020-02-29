//:====== PROXY PATTERNS ================
(
~bufnumPPatt = PatternProxy(Pseq([~dum, ~te], inf));
~ampPPatt = PatternProxy(Pseq([1, 1], inf));
~durPPatt = PatternProxy(Pseq([1, 1]/6, inf));


~rythm = Pbind(
    \instrument,     \simplePlyBuf,
	\bufnum,         ~bufnumPPatt,
	\amp,            ~ampPPatt,
	\dur,            ~durPPatt,
);
)

~rythm.play;
~bufnumPPatt.source = Pseq([~te, ~dum], inf);
~ampPPatt.source = Pseq([1, 0.5], inf);
~durPPatt.source = Pseq([1, 1]/2, inf);
