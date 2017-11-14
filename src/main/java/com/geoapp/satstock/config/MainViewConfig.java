package com.geoapp.satstock.config;

import com.geoapp.satstock.entity.DisplayObject;
import com.github.appreciated.material.MaterialTheme;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;

@Configuration
public class MainViewConfig {
    @Value("${google.map.key}")
    private String mapKey;

    //generate layouts
    @Bean
    @Scope("prototype")
    public VerticalLayout getVerticalLayout() {
        return new VerticalLayout() {{
            //setStyleName(MaterialTheme.SPLITPANEL_LARGE);
            setSizeFull();
            setMargin(false);
            setSpacing(false);
            setResponsive(true);

        }};
    }

    @Bean
    @Scope("prototype")
    public HorizontalLayout getHorizontalLayout() {
        return new HorizontalLayout() {{
            //setStyleName(MaterialTheme.SPLITPANEL_LARGE);
            //addStyleName("layout");
            setSizeFull();
            setMargin(false);
            setSpacing(false);
            setResponsive(true);
        }};
    }

    //ui components (map)
    @Bean
    @Scope("session")
    public GoogleMap getMap() {
        GoogleMap map = new GoogleMap(mapKey, null, null);
        map.setStyleName("valo");
        map.setSizeFull();
        map.setResponsive(true);
        map.setCenter(new LatLon(0, 40));
        map.setMapType(GoogleMap.MapType.Hybrid);
        map.setZoom(3);
        return map;
    }

    //ui components (grid)
    @Bean
    @Scope("session")
    public Grid<DisplayObject> getGrid() {
        Grid<DisplayObject> grid = new Grid<>();
        grid.setSizeFull();
        grid.addColumn(DisplayObject::getSomecaption).setCaption("Some Caption");
        grid.addColumn(DisplayObject::getSometext).setCaption("Some Text");
        grid.addColumn(DisplayObject::getSomeOtherText).setCaption("Some Other Text");
        grid.addColumn(DisplayObject::getSomeotherlongtext).setCaption("Some Other Long text");
        grid.setStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
        return grid;
    }

    @Bean
    public Window getWindow(){
        return new Window("Some window");
    }


    //ui components (controls)
    @Bean
    @Scope("prototype")
    public Button getButton() {
        Button button = new Button();
        button.setResponsive(true);
        button.setStyleName(MaterialTheme.BUTTON_BORDERLESS);
        button.addStyleName("buttonstyle");
        return button;
    }

    @Bean
    @Qualifier("topSelector")
    @Scope("prototype")
    public ComboBox<String> getTopSelector() {
        ComboBox box = new ComboBox();
        box.setItems(Arrays.asList("Item1","Item 2", "Item 3"));
        box.setTextInputAllowed(false);
        //box.setResponsive(true);
        //box.setStyleName(ValoTheme.COMBOBOX_ALIGN_CENTER);
        box.addStyleName("bandboxstyle");
        box.setEmptySelectionAllowed(true);
        box.setPopupWidth("13vw");
        return box;
    }

    @Bean
    @Qualifier("Numeric")
    @Scope("prototype")
    public ComboBox<Integer> getNumericBox() {
        ComboBox box = new ComboBox();
        box.setItems(Arrays.asList(1,2,3));
        box.setTextInputAllowed(false);
        //box.setResponsive(true);
        //box.setStyleName(ValoTheme.COMBOBOX_ALIGN_CENTER);
        box.addStyleName("boxstyle");
        box.setEmptySelectionAllowed(true);
        box.setPopupWidth("10vw");
        return box;
    }

    @Bean
    @Qualifier("Alpha")
    @Scope("prototype")
    public ComboBox<String> getAlphaBox() {
        ComboBox box = new ComboBox();
        box.setItems(Arrays.asList("Item1","Item 2", "Item 3"));
        box.setTextInputAllowed(false);
        //box.setResponsive(true);
        //box.setStyleName(ValoTheme.COMBOBOX_ALIGN_CENTER);
        box.addStyleName("boxstyle");
        box.setEmptySelectionAllowed(true);
        box.setPopupWidth("10vw");
        return box;
    }

}
