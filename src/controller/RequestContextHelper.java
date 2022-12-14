package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestContextHelper {
    private final HttpServletRequest request;

    public RequestContextHelper(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public RequestContext createContext() {
        Enumeration<String> requestParameterNames = request.getParameterNames();
        Enumeration<String> requestAttributesNames = request.getAttributeNames();
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributesNames = session.getAttributeNames();
        Map<String, String> requestParameters = createRequestParametersMap(requestParameterNames);
        Map<String, Object> requestAttributes = createRequestAttributesMap(requestAttributesNames);
        Map<String, Object> sessionAttributes = createSessionAttributesMap(sessionAttributesNames);
        return new RequestContext(requestParameters, requestAttributes, sessionAttributes);
    }

    private Map<String, String> createRequestParametersMap(Enumeration<String> names){
        Map<String, String> result = new HashMap<>();
        while(names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getParameter(name);
            result.put(name, value);
        }
        return result;
    }

    private Map<String, Object> createRequestAttributesMap(Enumeration<String> names){
        Map<String, Object> result = new HashMap<>();
        while(names.hasMoreElements()) {
            String name = names.nextElement();
            String value = (String)request.getAttribute(name);
            result.put(name, value);
        }
        return result;
    }

    private Map<String, Object> createSessionAttributesMap(Enumeration<String> names){
        Map<String, Object> result = new HashMap<>();
        while(names.hasMoreElements()) {
            String name = names.nextElement();
            String value = (String)request.getAttribute(name);
            result.put(name, value);
        }
        return result;
    }

    public void updateRequest(RequestContext requestContext){
        Map<String, Object> requestAttributes = requestContext.getAllRequestAttributes();
        Map<String, Object> sessionAttributes = requestContext.getAllSessionAttributes();
        HttpSession session = request.getSession();
        fillRequestAttributes(requestAttributes);
        fillSessionAttributes(session, sessionAttributes);
    }

    private void fillRequestAttributes(Map<String, Object> attributes) {
        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            request.setAttribute(attribute.getKey(), attribute.getValue());
        }
    }

    private void fillSessionAttributes(HttpSession session, Map<String, Object> attributes) {
        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            session.setAttribute(attribute.getKey(), attribute.getValue());
        }
    }
}
