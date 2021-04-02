package com.example.wbdvsp2103sindhuvahinisserverjava.services;

import com.example.wbdvsp2103sindhuvahinisserverjava.models.Widget;
import com.example.wbdvsp2103sindhuvahinisserverjava.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WidgetService {

    @Autowired
    WidgetRepository repository;

    // Implement CRUD Operations
    public Widget createWidgetForTopic(Widget widget) {
        return repository.save(widget);
    }

    public List<Widget> findAllWidgets() {
        return (List<Widget>) repository.findAll();
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        return repository.findWidgetsForTopic(topicId);
    }

    @Nullable
    public Widget findWidgetById(Long id) {
        Optional<Widget> widgetByIdOptional = repository.findById(id);
        return widgetByIdOptional.orElse(null);
    }

    public Integer updateWidget(Long id, Widget widget) {
        Optional<Widget> widgetByIdOptional = repository.findById(id);
        if (widgetByIdOptional.isPresent()) {
            Widget originalWidget = widgetByIdOptional.get();

            //TODO: copy all other fields
            originalWidget.setWidgetOrder(widget.getWidgetOrder());
            originalWidget.setText(widget.getText());
            originalWidget.setSrc(widget.getSrc());
            originalWidget.setTopicId(widget.getTopicId());
            originalWidget.setType(widget.getType());
            originalWidget.setCssClass(widget.getCssClass());
            originalWidget.setHeight(widget.getHeight());
            originalWidget.setWidth(widget.getWidth());
            originalWidget.setStyle(widget.getStyle());
            originalWidget.setName(widget.getName());
            originalWidget.setSize(widget.getSize());
            originalWidget.setValue(widget.getValue());
            originalWidget.setOrdered(widget.getOrdered());

            repository.save(originalWidget);
            return 1;
        }
        return -1;
    }

    public Integer deleteWidget(Long id) {
        repository.deleteById(id);
        return 1;
    }
}
