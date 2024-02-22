package com.openclassrooms.watchlist.service;

import com.openclassrooms.watchlist.domain.WatchListItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.repository.WatchListRepository;

import java.util.List;

public class WatchListService {

    WatchListRepository watchListRepository = new WatchListRepository();

    public List<WatchListItem> getWatchListItems() {
        return watchListRepository.getList();
    }

    public int getWatchListItemSize() {
        return watchListRepository.getList().size();
    }

    public WatchListItem findWatchListItemById(Integer id) {
        return watchListRepository.findById(id);
    }

    public void addOrUpdateWatchListItem(WatchListItem watchListItem) throws
                                                    DuplicateTitleException {

        WatchListItem existingItem = findWatchListItemById(watchListItem.getId());

        if (existingItem == null) {
            if (watchListRepository.findByTitle(watchListItem.getTitle()) != null){
                throw new DuplicateTitleException();
            }
            watchListRepository.addItem(watchListItem);
        }else {
            existingItem.setComment(watchListItem.getComment());
            existingItem.setPriority(watchListItem.getPriority());
            existingItem.setRating(watchListItem.getRating());
            existingItem.setTitle(watchListItem.getTitle());
        }

    }
}
