package edu.ntnu.idatt2105.g6.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idatt2105.g6.backend.dto.listing.*;
import edu.ntnu.idatt2105.g6.backend.service.listing.CategoryService;
import edu.ntnu.idatt2105.g6.backend.service.listing.ItemService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/listing")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping(value="/user/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> create(@ParameterObject @RequestPart("listingDTO") String listingDTO,
                                         @ParameterObject @RequestPart("images") List<MultipartFile> images) throws JsonProcessingException {
        //TODO: add exception handling in this method, remove throws exception
        ObjectMapper objectMapper = new ObjectMapper();
        ListingDTO listing = objectMapper.readValue(listingDTO, ListingDTO.class);
//        listing.setKeyInfoList(keyInfoList); //TODO: in service convert back to actual keyInfo while saving to db, remember transactional
        List<byte[]> byteImages = images.stream().map(image -> {
            try {
                return image.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        listing.setPictures(byteImages);
        System.out.println(listing);
//        listing.getPictures().add(thumbnail.getBytes());
        logger.info("A new listing is being created by: " + listing.toString());
        itemService.addListing(listing);
        logger.info("New listing has been added!");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/delete")
//    @ExceptionHandler(UserNotFoundException.class)
    //TODO: make this take in token or something
    public ResponseEntity<Object> delete(@ParameterObject @RequestBody ListingDeletionDTO listing) {
        itemService.deleteListing(listing);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/update")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> update(@ParameterObject @RequestBody ListingUpdateDTO listing) {
        itemService.updateListing(listing);
        return ResponseEntity.ok().build();
    }

    //TODO: maybe just add user id to the path?
    @GetMapping("/user/load")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading items of a given user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListingLoadDTO.class)) })}
            )
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> loadAllByUser(@ParameterObject @RequestBody String username) {
        List<ListingLoadDTO> items = itemService.loadAllListingsByUsername(username);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/load")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading all items",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListingLoadDTO.class)) })}
    )
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> loadAllByUser() {
        itemService.loadAllListings();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{categoryId}/load")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading items of a given user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListingLoadDTO.class)) })}
    )
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<List<ListingLoadDTO>> loadAllItemsByCategoryId(@ParameterObject @PathVariable Long categoryId) {
        logger.info("Looking for items under category Id: " + categoryId);
        List<ListingLoadDTO> listings = itemService.loadAllListingsByCategoryId(categoryId);
        logger.info("Items found: " + listings);
        return ResponseEntity.ok(listings);
    }

}
