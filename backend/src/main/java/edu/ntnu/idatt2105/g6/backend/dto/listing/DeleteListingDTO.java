package edu.ntnu.idatt2105.g6.backend.dto.listing;

import lombok.NonNull;

public record DeleteListingDTO(@NonNull String username, @NonNull Long itemId) {
}
