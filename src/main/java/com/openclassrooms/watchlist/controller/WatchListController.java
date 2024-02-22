package com.openclassrooms.watchlist.controller;

import com.openclassrooms.watchlist.domain.WatchListItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.service.WatchListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;


@Controller
public class WatchListController {

    private final WatchListService watchListService = new WatchListService();
    @GetMapping("/watchListItemForm")
    public ModelAndView showWatchListItemForm(@RequestParam(required = false) Integer id) {

        String viewName = "watchListItemForm";
        Map<String , Object> model = new HashMap<String , Object>();
        WatchListItem watchListItem = watchListService.findWatchListItemById(id);
        model.put("watchListItem" , watchListItem == null ?
                new WatchListItem() : watchListItem);

        return new ModelAndView(viewName , model);
    }

    @PostMapping("/watchListItemForm")
    public ModelAndView submitWatchListItemForm(@Valid WatchListItem watchListItem
     , BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ModelAndView("watchListItemForm");

        try {
            watchListService.addOrUpdateWatchListItem(watchListItem);
        }catch (DuplicateTitleException e) {
           bindingResult.rejectValue("title" , ""
                   , "This title already exists on your watchList");
           return new ModelAndView("watchListItemForm");
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");
        return new ModelAndView(redirectView);
    }


    @GetMapping("/watchlist")
    public ModelAndView getWatchList() {

        String viewName = "watchlist";
        Map<String , Object> model = new HashMap<String , Object>();
        model.put("numberOfMovies" , watchListService.getWatchListItemSize());
        model.put("watchListItems" , watchListService.getWatchListItems());

        return new ModelAndView(viewName , model);
    }
}
