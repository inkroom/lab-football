package cn.nsu.edu.web.four.handler;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

public class WebServletContext implements ServletContext {
    private ServletContext context;

    public WebServletContext(ServletContext context) {
        this.context = context;
    }

    @Override
    public String getContextPath() {
        String path = getInitParameter("contextPath");
        if (path == null)
            return context.getContextPath();
        else
            return path;
    }

    @Override
    public ServletContext getContext(String s) {
        return context;
    }

    @Override
    public int getMajorVersion() {
        return context.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return context.getMinorVersion();
    }

    @Override
    public int getEffectiveMajorVersion() {
        return context.getEffectiveMajorVersion();
    }

    @Override
    public int getEffectiveMinorVersion() {
        return context.getEffectiveMinorVersion();
    }

    @Override
    public String getMimeType(String s) {
        return context.getMimeType(s);
    }

    @Override
    public Set<String> getResourcePaths(String s) {
        return context.getResourcePaths(s);
    }

    @Override
    public URL getResource(String s) throws MalformedURLException {
        return context.getResource(s);
    }

    @Override
    public InputStream getResourceAsStream(String s) {
        return context.getResourceAsStream(s);
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return context.getRequestDispatcher(s);
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String s) {
        return context.getNamedDispatcher(s);
    }

    @Override
    public Servlet getServlet(String s) throws ServletException {
        return context.getServlet(s);
    }

    @Override
    public Enumeration<Servlet> getServlets() {
        return context.getServlets();
    }

    @Override
    public Enumeration<String> getServletNames() {
        return context.getAttributeNames();
    }

    @Override
    public void log(String s) {
        context.log(s);
    }

    @Override
    public void log(Exception e, String s) {
        context.log(e, s);
    }

    @Override
    public void log(String s, Throwable throwable) {
        context.log(s, throwable);
    }

    @Override
    public String getRealPath(String s) {
        return context.getRealPath(s);
    }

    @Override
    public String getServerInfo() {
        return context.getServerInfo();
    }

    @Override
    public String getInitParameter(String s) {
        return context.getInitParameter(s);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return context.getInitParameterNames();
    }

    @Override
    public boolean setInitParameter(String s, String s1) {
        return context.setInitParameter(s, s1);
    }

    @Override
    public Object getAttribute(String s) {
        return context.getAttribute(s);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return context.getAttributeNames();
    }

    @Override
    public void setAttribute(String s, Object o) {
        context.setAttribute(s, o);
    }

    @Override
    public void removeAttribute(String s) {
        context.removeAttribute(s);
    }

    @Override
    public String getServletContextName() {
        return context.getServletContextName();
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String s, String s1) {
        return context.addServlet(s, s1);
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String s, Servlet servlet) {
        return context.addServlet(s, servlet);
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String s, Class<? extends Servlet> aClass) {
        return context.addServlet(s, aClass);
    }

    @Override
    public <T extends Servlet> T createServlet(Class<T> aClass) throws ServletException {
        return context.createServlet(aClass);
    }

    @Override
    public ServletRegistration getServletRegistration(String s) {
        return context.getServletRegistration(s);
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        return context.getServletRegistrations();
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String s, String s1) {
        return context.addFilter(s, s1);
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String s, Filter filter) {
        return context.addFilter(s, filter);
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String s, Class<? extends Filter> aClass) {
        return context.addFilter(s, aClass);
    }

    @Override
    public <T extends Filter> T createFilter(Class<T> aClass) throws ServletException {
        return context.createFilter(aClass);
    }

    @Override
    public FilterRegistration getFilterRegistration(String s) {
        return context.getFilterRegistration(s);
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        return context.getFilterRegistrations();
    }

    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        return context.getSessionCookieConfig();
    }

    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> set) {
        context.setSessionTrackingModes(set);
    }

    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        return context.getDefaultSessionTrackingModes();
    }

    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return context.getDefaultSessionTrackingModes();
    }

    @Override
    public void addListener(String s) {
        context.addListener(s);
    }

    @Override
    public <T extends EventListener> void addListener(T t) {
        context.addListener(t);
    }

    @Override
    public void addListener(Class<? extends EventListener> aClass) {
        context.addListener(aClass);
    }

    @Override
    public <T extends EventListener> T createListener(Class<T> aClass) throws ServletException {
        return context.createListener(aClass);
    }

    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        return context.getJspConfigDescriptor();
    }

    @Override
    public ClassLoader getClassLoader() {
        return context.getClassLoader();
    }

    @Override
    public void declareRoles(String... strings) {
        context.declareRoles(strings);
    }

    @Override
    public String getVirtualServerName() {
        return context.getVirtualServerName();
    }
}
