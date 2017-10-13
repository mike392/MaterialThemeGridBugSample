package com.geoapp.satstock.view;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.ui.Label;

public class LabelBuilder {

    public Label buildLabel(String value) {
        return new Label() {{
            setValue(value);
            setSizeFull();
            setResponsive(true);
            setStyleName(MaterialTheme.LABEL_BORDERLESS);
            addStyleName("labelstyle");
        }};
    }

    public Label buildLabel(String value, String width) {
        return new Label() {{
            setValue(value);
            setWidth(width);
            setResponsive(true);
            addStyleName(MaterialTheme.LABEL_BORDERLESS);
            addStyleName("labelstyle");
        }};
    }
}
