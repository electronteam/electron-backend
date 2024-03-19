package md.electron.electronbackend.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.electron.electronbackend.persistence.listeners.ItemEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(ItemEntityListener.class)
//remember -  'Serializable' is mandatory; e.g. without it, hibernate won't autogenerate the ID
public class Item implements Serializable {
    @Column(name = "CREATION_TIME")
    private String creationTime;
}
