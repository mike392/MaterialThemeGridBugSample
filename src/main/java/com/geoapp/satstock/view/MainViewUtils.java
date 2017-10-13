package com.geoapp.satstock.view;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class MainViewUtils {

    public HorizontalLayout setExpandRatiosHL(HorizontalLayout layout, List<Float> ratios) {
        for (int i = 0; i < layout.getComponentCount(); i++) {
            layout.setExpandRatio(layout.getComponent(i), ratios.get(i));
        }
        return layout;
    }

    public VerticalLayout setExpandRatiosVL(VerticalLayout layout, List<Float> ratios) {
        for (int i = 0; i < layout.getComponentCount(); i++) {
            layout.setExpandRatio(layout.getComponent(i), ratios.get(i));
        }
        return layout;
    }

    public <T> ComboBox<T> clearSelector(ComboBox<T> item) {
        item.clear();
        item.setValue(null);
        return item;
    }
}
