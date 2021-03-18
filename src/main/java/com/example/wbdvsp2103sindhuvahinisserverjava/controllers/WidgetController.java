package com.example.wbdvsp2103sindhuvahinisserverjava.controllers;

import com.example.wbdvsp2103sindhuvahinisserverjava.models.Widget;
import com.example.wbdvsp2103sindhuvahinisserverjava.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    // Autowired instantiate a service and
    @Autowired
    WidgetService service;

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(@PathVariable String tid) {
        return service.findWidgetsForTopic(tid);
    }

    @GetMapping("/api/widgets/{id}")
    public Widget findWidgetById(@PathVariable Long id) {
        return service.findWidgetById(id);
    }

    @PutMapping("/api/widgets/{id}")
    public Integer updateWidget(@PathVariable Long id, @RequestBody Widget widget) {
        return service.updateWidget(id, widget);
    }

    @PostMapping("/api/topics/{tid}/widgets")
    public Widget createWidgetForTopic(@PathVariable String tid, @RequestBody Widget widget) {
        widget.setTopicId(tid);
        return service.createWidgetForTopic(widget);
    }

    @DeleteMapping("/api/widgets/{wid}")
    public Integer deleteWidget(@PathVariable Long wid) {
        return service.deleteWidget(wid);
    }

}
