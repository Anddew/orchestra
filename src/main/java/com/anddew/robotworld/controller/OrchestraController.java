package com.anddew.robotworld.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anddew.robotworld.history.HistoryHolder;


/**
 * Main page controller.
 *
 * @author Anddew
 */
@Controller
@RequestMapping("/orchestra")
public class OrchestraController {

    @Autowired
    private HistoryHolder historyHolder;

    /**
     * Render main page.
     *
     * @return main page name
     */
    @GetMapping
    public String getPage() {
        return "orchestra";
    }

    /**
     * Updates main page data.
     *
     * @return history collection
     */
    @GetMapping("/update")
    public @ResponseBody Collection<String> getUpdates() {
        return historyHolder.getHistory();
    }

}