package com.touch.blankspot.begining.thread;

import io.micrometer.context.ThreadLocalAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

/**
 * {@link ThreadLocalAccessor} to expose a thread-bound RequestAttributes object to data
 * fetchers in Spring for GraphQL.
 */
@Component
public class RequestAttributesAccessor implements ThreadLocalAccessor<RequestAttributes> {

    private static final String KEY = RequestAttributesAccessor.class.getName();

    public void extractValues(Map<String, Object> container) {
        container.put(KEY, RequestContextHolder.getRequestAttributes());
    }

    public void restoreValues(Map<String, Object> values) {
        if (values.containsKey(KEY)) {
            RequestContextHolder.setRequestAttributes((RequestAttributes) values.get(KEY));
        }
    }

    @Override
    public Object key() {
        return RequestAttributesAccessor.class.getName();
    }

    @Override
    public RequestAttributes getValue() {
        return RequestContextHolder.getRequestAttributes();
    }

    @Override
    public void setValue(RequestAttributes value) {

    }

    @Override
    public void reset() {
        RequestContextHolder.resetRequestAttributes();
    }
}
