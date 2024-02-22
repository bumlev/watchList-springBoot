package com.openclassrooms.watchlist.repository;

import com.openclassrooms.watchlist.domain.WatchListItem;
import com.openclassrooms.watchlist.service.MovieRatingService;

import java.util.ArrayList;
import java.util.List;

public class WatchListRepository {

    private final List<WatchListItem> watchListItems = new ArrayList<WatchListItem>();
    private final MovieRatingService movieRatingService = new MovieRatingService();
    private static int index= 1;

    public List<WatchListItem> getList() {

        for (WatchListItem watchListItem :watchListItems) {
            String rating = movieRatingService.getMovieRating(watchListItem.getTitle());

            if (rating != null)
                watchListItem.setRating(rating);
        }
        return watchListItems;
    }

    public void addItem(WatchListItem watchListItem) {
        watchListItem.setId(index++);
        watchListItems.add(watchListItem);
    }

    public WatchListItem findById(Integer id) {

        for (WatchListItem watchListItem : watchListItems) {
            if (watchListItem.getId().equals(id))
                return watchListItem;
        }
        return null;
    }

    public WatchListItem findByTitle(String title) {

        for (WatchListItem watchListItem : watchListItems) {
            if(watchListItem.getTitle().equals(title))
                return watchListItem;
        }
        return null;
    }
}
