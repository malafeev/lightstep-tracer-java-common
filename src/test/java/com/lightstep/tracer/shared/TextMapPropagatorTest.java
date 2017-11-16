package com.lightstep.tracer.shared;

import io.opentracing.propagation.TextMap;
import io.opentracing.propagation.TextMapExtractAdapter;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.lightstep.tracer.shared.TextMapPropagator.FIELD_NAME_X_B3_SPAN_ID;
import static com.lightstep.tracer.shared.TextMapPropagator.FIELD_NAME_X_B3_TRACE_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TextMapPropagatorTest {

    @Test
    public void testExtract_mixedCaseIsLowered() {
        Map<String, String> mixedCaseHeaders = new HashMap<>();

        mixedCaseHeaders.put("Ot-tracer-spanid", Long.toHexString(1));
        mixedCaseHeaders.put("Ot-tracer-traceId", Long.toHexString(2));
        mixedCaseHeaders.put("ot-Tracer-sampled", "true");

        TextMapPropagator subject = new TextMapPropagator();

        SpanContext span = subject.extract(new TextMapExtractAdapter(mixedCaseHeaders));

        assertNotNull(span);
        assertEquals(span.getSpanId(), 1);
        assertEquals(span.getTraceId(), 2);
    }

    @Test
    public void testExtract_xB3Headers() {
        Map<String, String> headers = new HashMap<>();

        headers.put(FIELD_NAME_X_B3_TRACE_ID, Long.toHexString(1));
        headers.put(FIELD_NAME_X_B3_SPAN_ID, Long.toHexString(2));

        TextMapPropagator subject = new TextMapPropagator();

        SpanContext spanContext = subject.extract(new TextMapExtractAdapter(headers));

        assertNotNull(spanContext);
        assertEquals(spanContext.getTraceId(), 1);
        assertEquals(spanContext.getSpanId(), 2);
    }

    @Test
    public void testInjectAndExtractIds() {
        TextMapPropagator undertest = new TextMapPropagator();
        TextMap carrier = new TextMap() {
            final Map<String, String> textMap = new HashMap<>();

            public void put(String key, String value) {
                textMap.put(key, value);
            }

            public Iterator<Map.Entry<String, String>> iterator() {
                return textMap.entrySet().iterator();
            }
        };
        SpanContext spanContext = new SpanContext();
        undertest.inject(spanContext, carrier);

        SpanContext result = undertest.extract(carrier);

        assertEquals(spanContext.getTraceId(), result.getTraceId());
        assertEquals(spanContext.getSpanId(), result.getSpanId());
    }
}
