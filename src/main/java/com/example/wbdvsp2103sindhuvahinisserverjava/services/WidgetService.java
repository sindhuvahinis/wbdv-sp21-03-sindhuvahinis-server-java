package com.example.wbdvsp2103sindhuvahinisserverjava.services;

import com.example.wbdvsp2103sindhuvahinisserverjava.models.Widget;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WidgetService {
    private List<Widget> widgets = new ArrayList<Widget>();

    {
        Widget w1 = new Widget(123l, "HEADING", 1, "Welcome to Widgets", "604991a69fcc120017b7dd6c");
        Widget w2 = new Widget(124l, "PARAGRAPH", 1, "This is a paragraph", "604991a69fcc120017b7dd6c");
        Widget w3 = new Widget(125l, "HEADING", 1, "Welcome to WebDev", "604991a69fcc120017b7dd6c");
        Widget w4 = new Widget(126l, "PARAGRAPH", 1, "Lorem ipsum", "ABC126");
        widgets.add(w1);
        widgets.add(w2);
        widgets.add(w3);
        widgets.add(w4);
    }

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
        List<Widget> ws = new ArrayList<Widget>();
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
