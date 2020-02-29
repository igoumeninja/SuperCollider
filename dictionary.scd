// RECOVER APP

~startAppTask.start
// Particles

NetAddr("127.0.0.1", 9005).sendMsg("/particleView", 0.asInteger)
NetAddr("127.0.0.1", 9005).sendMsg("/particleView", 1.asInteger)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "bounce", 100,100,500,900)
NetAddr("127.0.0.1", 12345).sendMsg("/particle", "bounce", 100,100,800,900)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "particleNeighborhood", 4)
NetAddr("127.0.0.1", 12345).sendMsg("/particle", "particleNeighborhood", 20)


NetAddr("127.0.0.1", 12345).sendMsg("/particle", "conColor", 255.asInteger,255.asInteger,255.asInteger,255.asInteger)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "forceRadius", 35.asInteger)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "forceScale", 100.asInteger)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "pushParticles", 1.asInteger)
NetAddr("127.0.0.1", 12345).sendMsg("/particle", "pushParticles", 0.asInteger)


NetAddr("127.0.0.1", 12345).sendMsg("/particle", "push", 300.asInteger, 200.asInteger)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "lineOpacity", 2)
NetAddr("127.0.0.1", 12345).sendMsg("/particle", "push", 150,150)


// ArgyrakosSampler
KorgSamplerTrigger_200217.start