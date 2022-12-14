package controller;

import java.util.HashMap;
import java.util.Map;

public class RequestContext {
    private final Map<String, String> requestParameters;
    private final Map<String, Object> requestAttributes;
    private final Map<String, Object> sessionAttributes;

    RequestContext(Map<String, String> requestParameters, Map<String, Object> requestAttributes, Map<String, Object> sessionAttributes){
        this.requestAttributes = requestAttributes;
        this.requestParameters = requestParameters;
        this.sessionAttributes = sessionAttributes;
    }

    public Map<String, Object> getAllRequestAttributes(){
        return new HashMap<>(requestAttributes);
    }

    public Map<String, Object> getAllSessionAttributes(){
        return new HashMap<>(sessionAttributes);
    }

    public String getRequestParameter(String key) {
        return requestParameters.get(key);
    }

    public void addReuestParameter(String key, String value) {
        requestParameters.put(key, value);
    }


    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    public void addSessionAttribute(String key, Object value) {
        sessionAttributes.put(key, value);
    }

    public Object getRequestAttribute(String key) {
        return requestAttributes.get(key);
    }

    public void removeRequestParameter(String key) {
        requestParameters.remove(key);
    }

    public void addRequestAttributes(String key, Object value) {
        requestAttributes.put(key, value);
    }

    public void removeSessionAttributes(String key) {
        sessionAttributes.remove(key);
    }
}
