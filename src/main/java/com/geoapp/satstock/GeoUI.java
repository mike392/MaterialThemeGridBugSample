package com.geoapp.satstock;

import com.geoapp.satstock.view.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.Locale;

@Theme("facebook")
@Title("WebSite")
@SpringUI
@Viewport("initial-scale=1, maximum-scale=1")
public class GeoUI extends UI {

    private static Locale locale = VaadinSession.getCurrent().getLocale();

    @Autowired
    private SpringViewProvider springViewProvider;

    public GeoUI() {

    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(springViewProvider);
        navigator.addView("main", new MainView());
        navigator.navigateTo("main");

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GeoUI.class, productionMode = false, closeIdleSessions = true)
    public static class MyUIServlet extends VaadinServlet implements SessionInitListener {
        @Override
        protected void servletInitialized() throws ServletException {
            super.servletInitialized();
            getService().addSessionInitListener(this);
        }
        @Override
        public void sessionInit(SessionInitEvent event) throws ServiceException {
            event.getSession().addBootstrapListener(new BootstrapListener() {

                @Override
                public void modifyBootstrapPage(BootstrapPageResponse response) {
                    response.getDocument().head()
                            .getElementsByAttributeValue("rel", "shortcut icon")
                            .attr("href", "./VAADIN/themes/facebook/custom.ico");
                    response.getDocument().head()
                            .getElementsByAttributeValue("rel", "icon")
                            .attr("href", "./VAADIN/themes/facebook/custom.ico");
                }

                @Override
                public void modifyBootstrapFragment(BootstrapFragmentResponse response) {
                }

            });
        }
    }
}
