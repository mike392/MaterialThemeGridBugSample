package com.geoapp.satstock.view;

import com.geoapp.satstock.entity.DisplayObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 * The main view containing the menu and the content area where actual views are
 * shown.
 * <p>
 * Created as a single View class because the logic is so simple that using a
 * pattern like MVP would add much overhead for little gain. If more complexity
 * is added to the class, you should consider splitting out a presenter.
 */
@UIScope
@SpringView(name = "main")
public class MainView extends CustomComponent implements View {

    //create layouts - containers for elements
    @Autowired
    private VerticalLayout layout;
    @Autowired
    private HorizontalLayout definitionGroup;
    @Autowired
    private HorizontalLayout tableDisplayGroup;
    @Autowired
    private VerticalLayout filterGroup;
    @Autowired
    private HorizontalLayout topSelectionGroup;
    @Autowired
    private VerticalLayout middleSelectionGroup;
    @Autowired
    private HorizontalLayout middleLabelsGroup;
    @Autowired
    private HorizontalLayout middleNumericSelectionGroup;
    @Autowired
    private HorizontalLayout middleAlphaSelectionGroup;
    @Autowired
    private HorizontalLayout buttonGroup;

    //create map
    @Autowired
    private GoogleMap map;
    //create table for polygons display
    @Autowired
    private Grid<DisplayObject> grid;
    //create UI elements - comboboxes and buttons
    @Autowired
    @Qualifier("topSelector")
    private ComboBox<String> topSelector;
    @Autowired
    @Qualifier("Numeric")
    private ComboBox<Integer> numericSelector;
    @Autowired
    @Qualifier("Numeric")
    private ComboBox<Integer> otherNumericSelector;
    @Autowired
    @Qualifier("Alpha")
    private ComboBox<String> alphaSelector;
    @Autowired
    @Qualifier("Alpha")
    private ComboBox<String> otherAlphaSelector;
    @Autowired
    private Button clearFilterButton;
    @Autowired
    private Button showPopup;


    //utils
    @Autowired
    private MainViewUtils mainViewUtils;
    @Autowired
    private LabelBuilder labelBuilder;

    public MainView() {
    }

    @PostConstruct
    public void init(){
        //add listeners to map, buttons, comboboxes
        initMap();
        initButtons();
        gridInit();
        //add components to containers
        middleAlphaSelectionGroup.addComponents(labelBuilder.buildLabel("Alpha Values"), alphaSelector, otherAlphaSelector);
        middleNumericSelectionGroup.addComponents(labelBuilder.buildLabel("Numeric Values"), numericSelector, otherNumericSelector);
        middleLabelsGroup.addComponents(labelBuilder.buildLabel(""),labelBuilder.buildLabel("Left","80%"), labelBuilder.buildLabel("Right","80%"));
        middleSelectionGroup.addComponents(middleLabelsGroup, middleAlphaSelectionGroup, middleNumericSelectionGroup);
        topSelectionGroup.addComponents(labelBuilder.buildLabel("Top"), topSelector);
        buttonGroup.addComponents(showPopup);
        filterGroup.addComponents(topSelectionGroup, middleSelectionGroup,buttonGroup);
        definitionGroup.addComponents(filterGroup,  map);
        FormLayout lt = new FormLayout();
        lt.addComponents(clearFilterButton, grid);
        PopupView popupView = new PopupView("Cokoko", lt);
        showPopup.setCaption("Show popup");
        showPopup.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                layout.addComponent(popupView);
                popupView.addPopupVisibilityListener(new PopupView.PopupVisibilityListener() {
                    @Override
                    public void popupVisibilityChange(PopupView.PopupVisibilityEvent popupVisibilityEvent) {
                        if (!popupVisibilityEvent.isPopupVisible()){
                            layout.removeComponent(popupView);
                        }
                    }
                });
                popupView.setPopupVisible(true);
            }
        });
        layout.addComponents(definitionGroup);
        setCompositionRoot(layout);

        //shift uplink downlink labels to right
        middleLabelsGroup.setComponentAlignment(middleLabelsGroup.getComponent(0), Alignment.BOTTOM_RIGHT);
        middleLabelsGroup.setComponentAlignment(middleLabelsGroup.getComponent(1), Alignment.BOTTOM_RIGHT);

        //specifiy relative sizes of components in containers
        topSelectionGroup = mainViewUtils.setExpandRatiosHL(topSelectionGroup, Arrays.asList(1.0f, 1.0f));
        middleAlphaSelectionGroup = mainViewUtils.setExpandRatiosHL(middleAlphaSelectionGroup, Arrays.asList(0.6f, 1.0f, 1.0f));
        middleNumericSelectionGroup = mainViewUtils.setExpandRatiosHL(middleNumericSelectionGroup, Arrays.asList(0.6f, 1.0f, 1.0f));
        middleLabelsGroup = mainViewUtils.setExpandRatiosHL(middleLabelsGroup, Arrays.asList(1.2f, 1.0f, 1.0f));
        buttonGroup = mainViewUtils.setExpandRatiosHL(buttonGroup, Arrays.asList(1.0f, 1.0f, 1.0f));
        filterGroup = mainViewUtils.setExpandRatiosVL(filterGroup, Arrays.asList(1.0f, 3.0f, 1.0f));
        definitionGroup = mainViewUtils.setExpandRatiosHL(definitionGroup, Arrays.asList(1.0f, 2.3f));
        layout = mainViewUtils.setExpandRatiosVL(layout, Arrays.asList(2.0f, 1.0f));

        //set specific settings
        //bandWidthSelectionGroup.setSpacing(true);
        addStyleName("viewstyle");
        this.setSizeFull();
    }
    @Override
    public void enter(ViewChangeEvent event) {

    }

    private void gridInit() {
        List<DisplayObject> list = new LinkedList<DisplayObject>();
        list.add(new DisplayObject(){{
                setSomecaption("aaaaaaaaaaaaaaaaaaaa");
                setSomeotherlongtext("aaaaaaaaaaaaaaaaaaaa");
                setSometext("aaaaaaaaaaaaaaaaaaaa");
                setSomeOtherText("aaaaaaaaaaaaaaaaaaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaaaaaaaafffaaaaaaaaaaa");
            setSomeotherlongtext("aaaaaafffaaaaaaaaaaaaaa");
            setSometext("aaaaaaaaaaaaaafffaaaaaa");
            setSomeOtherText("aaaaaaaaafffaaaaaaaaaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaaaaaaaaaaaaaaafffaaaa");
            setSomeotherlongtext("aaaaaaaaaaaaafffaaaaaaa");
            setSometext("aaaaaaaaaaaaaaaaafffaaa");
            setSomeOtherText("aaaaaaaaaaaaaaaaafffaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaafffaaaaaaaaaaaaaaaaa");
            setSomeotherlongtext("aafffaaaaaaaaaaaaaaaaaa");
            setSometext("aaaafffaaaaaaaaaaaaaaaa");
            setSomeOtherText("aaffaaaaaaaaaaaaaaaaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaaaaaaaaaaaaaaaaaaa");
            setSomeotherlongtext("aaaaaaaaaaaaaaaaaaaa");
            setSometext("aaaaaaaaaaaaaaaaaaaa");
            setSomeOtherText("aaaaaaaaaaaaaaaaaaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaaaaaaaafffaaaaaaaaaaa");
            setSomeotherlongtext("aaaaaafffaaaaaaaaaaaaaa");
            setSometext("aaaaaaaaaaaaaafffaaaaaa");
            setSomeOtherText("aaaaaaaaafffaaaaaaaaaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaaaaaaaaaaaaaaafffaaaa");
            setSomeotherlongtext("aaaaaaaaaaaaafffaaaaaaa");
            setSometext("aaaaaaaaaaaaaaaaafffaaa");
            setSomeOtherText("aaaaaaaaaaaaaaaaafffaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaafffaaaaaaaaaaaaaaaaa");
            setSomeotherlongtext("aafffaaaaaaaaaaaaaaaaaa");
            setSometext("aaaafffaaaaaaaaaaaaaaaa");
            setSomeOtherText("aaffaaaaaaaaaaaaaaaaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaaaaaaaaaaaaaaaaaaa");
            setSomeotherlongtext("aaaaaaaaaaaaaaaaaaaa");
            setSometext("aaaaaaaaaaaaaaaaaaaa");
            setSomeOtherText("aaaaaaaaaaaaaaaaaaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaaaaaaaafffaaaaaaaaaaa");
            setSomeotherlongtext("aaaaaafffaaaaaaaaaaaaaa");
            setSometext("aaaaaaaaaaaaaafffaaaaaa");
            setSomeOtherText("aaaaaaaaafffaaaaaaaaaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaaaaaaaaaaaaaaafffaaaa");
            setSomeotherlongtext("aaaaaaaaaaaaafffaaaaaaa");
            setSometext("aaaaaaaaaaaaaaaaafffaaa");
            setSomeOtherText("aaaaaaaaaaaaaaaaafffaaa");}});
        list.add(new DisplayObject(){{
            setSomecaption("aaafffaaaaaaaaaaaaaaaaa");
            setSomeotherlongtext("aafffaaaaaaaaaaaaaaaaaa");
            setSometext("aaaafffaaaaaaaaaaaaaaaa");
            setSomeOtherText("aaffaaaaaaaaaaaaaaaaaa");}});
        grid.setItems(list);
        grid.getDataProvider().refreshAll();
    }

    private void initMap() {
        map.addMapClickListener(new MapClickListener() {
            @Override
            public void mapClicked(LatLon latLon) {
                Collection<GoogleMapMarker> markers = new LinkedList<>(map.getMarkers());
                markers.stream().forEach(e -> map.removeMarker(e));
                map.addMarker(String.format("Hey! You clicked latitude: %s and longitude: %s", latLon.getLat(), latLon.getLon()),
                        latLon, false, null);
            }
        });
        map.setResponsive(true);
    }

    private void initButtons() {
        clearFilterButton.setCaption("Clear Filters");
        clearFilterButton.addClickListener((Button.ClickListener) clickEvent -> {
           gridInit();
        });
    }

}
