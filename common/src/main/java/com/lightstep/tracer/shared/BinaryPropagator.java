package com.lightstep.tracer.shared;

import java.nio.ByteBuffer;

class BinaryPropagator implements Propagator<ByteBuffer> {
    public void inject(SpanContext spanContext, ByteBuffer carrier, boolean useB3Headers) {
        // TODO: implement
    }

    public SpanContext extract(ByteBuffer carrier) {
        // TODO: implement
        return null;
    }
}