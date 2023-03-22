package edu.ntnu.idatt2105.g6.backend.model.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "key_infos")
public class KeyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_info_id")
    private Long keyInfoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    @NonNull
    @ToString.Exclude
    private Item item;

    @Column(name = "description", nullable = false)
    @NonNull
    private String description;


}
