package com.sdevolder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CodeStoryServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(CodeStoryServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("A request has arrived");
        @SuppressWarnings("unchecked")
        Map<String, Object> parameterMap = req.getParameterMap();
        log.info("It contains " + parameterMap.size() + " parameters");
        if (parameterMap.size() > 0) {
            log.info(buildParamList(parameterMap));
        }
        resp.getWriter().write("sebastien.devolder@gmail.com");
    }

    protected String buildParamList(Map<String, Object> parameterMap) {
        StringBuilder sb = new StringBuilder("Parameters list\n");
        Set<String> keySet = parameterMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            index++;
            String paramName = iterator.next();
            sb.append("Param " + index + ": [" + paramName + "]");
            Object value = parameterMap.get(paramName);
            String valueString;
            if (value instanceof String[]) {
                valueString = "L";// Pour marquer la valeur du paramètre comme étant une liste
                valueString += Arrays.toString(((String[]) value));
            } else {
                valueString = value.toString();
            }
            sb.append("[" + valueString + "]");
            sb.append("\n");
        }
        return sb.toString();
    }
}
