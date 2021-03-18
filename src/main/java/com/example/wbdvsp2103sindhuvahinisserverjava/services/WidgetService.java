package com.example.wbdvsp2103sindhuvahinisserverjava.services;

import com.example.wbdvsp2103sindhuvahinisserverjava.models.Widget;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WidgetService {
    private List<Widget> widgets = new ArrayList<>();

    // Implement CRUD Operations
    public Widget createWidgetForTopic(Widget widget) {
        Long id = (new Date()).getTime();
        widget.setId(id);
        widgets.add(widget);
        return widget;
    }

    public List<Widget> findAllWidgets() {
        return widgets;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        List<Widget> ws = new ArrayList<>();
        for (Widget w : widgets) {
            if (w.getTopicId().equals(topicId)) {
                ws.add(w);
            }
        }
        return ws;
    }

    public Widget findWidgetById(Long id) {
        for (Widget w : widgets) {
            if (w.getId().equals(id)) {
                return w;
            }
        }
        return null;
    }

    public Integer updateWidget(Long id, Widget widget) {
        for (int i = 0; i < widgets.size(); i++) {
            if (widgets.get(i).getId().equals(id)) {
                widgets.set(i, widget);
                return 1;
            }
        }
        return -1;
    }

    public Integer deleteWidget(Long id) {
        int index = -1;
        for (int i = 0; i < widgets.size(); i++) {
            if (widgets.get(i).getId().equals(id)) {
                index = i;
                widgets.remove(index);
                return 1;
            }
        }
        return -1;
    }
}
