package md.electron.electronbackend.persistence.listeners;

import md.electron.electronbackend.persistence.model.Item;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class ItemEntityListener {

    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof Item) {
            Item item = (Item) entity;
            item.setCreationTime(LocalDateTime.now().toString());
        }
    }
}
